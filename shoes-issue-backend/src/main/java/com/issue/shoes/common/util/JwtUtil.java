package com.issue.shoes.common.util;

import com.google.gson.Gson;
import com.issue.shoes.oauth.service.OauthService;
import com.issue.shoes.oauth.vo.OauthJwt;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


@Configuration
@PropertySource("classpath:config/application.properties")
public class JwtUtil {


    private Logger log = LogManager.getLogger("case3");

    @Value("${secretKey}")
    private String SECRETKEY;

    private final static long ACCESS_TOKEN_EXPTIME = 1000*30*1;

    private final static long REFRESH_TOKEN_EXPTIME = 1000*60*60*24*14;

    @Autowired
    private Gson gson;

    @Autowired
    private OauthService service;

    public String createAccessJwt(String subject) {
        if(ACCESS_TOKEN_EXPTIME <= 0) {
            throw new RuntimeException("만료 시간이 0보다 커야 한다");
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRETKEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPTIME))
                .signWith(signingKey)
                .compact();

    }

    public String createRefreshJwt(String subject) {
        if(REFRESH_TOKEN_EXPTIME <= 0) {
            throw new RuntimeException("만료 시간이 0보다 커야 한다");
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRETKEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        String refreshToken = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPTIME))
                .signWith(signingKey)
                .compact();

        // subject 를 기반으로 userJwt 가 존재하는지 조회
        OauthJwt userJwt = service.getUserJwtBySubject(OauthJwt.builder()
                .subject(subject).build());
        // 혹시라도 userJwt 에 userNo 이 존재한다면 업데이트를 해준다.
        if(userJwt != null) {
            userJwt = service.editUserJwt(OauthJwt.builder()
                    .userJwtIdx(userJwt.getUserJwtIdx())
                    .refreshToken(refreshToken)
                    .build());
            log.debug("db 수정");
        } else {
            userJwt = service.createUserJwt(OauthJwt.builder()
                    .subject(subject)
                    .refreshToken(refreshToken)
                    .build());
            log.debug("db 생성");
        }
        // db에 존재하지 않는다면 userJwt 테이블에 refresh token 생성
        return userJwt.getUserJwtIdx();
    }

    // 로그아웃을 위해 access token 만료 및 db 에 저장된 refresh 삭제
    public boolean removeRefreshJwt(String jwt, OauthJwt userJwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                    .parseClaimsJws(jwt)
                    .getBody();
            claims.setExpiration(new Date(System.currentTimeMillis()));
            log.debug("access token의 유효기간 만료 변경");
            if (service.removeUserJwt(userJwt) == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.debug("access token 기간 만료");
            return false;
        }
    }

    // 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpHeaders headers, String accessJwt) {
        headers.set("Authorization", "bearer "+ accessJwt);
    }

    // DB 리프레시 토큰의 idx 값 헤더 설정
    public void setHeaderRefreshToken(HttpHeaders headers, String userJwtIdx) {
        headers.set("RefreshTokenIdx", userJwtIdx);
    }

    // Request의 Header에서 AccessToken 값을 가져옵니다. "Authorization" : "token'
    public String resolveAccessToken(HttpEntity entity) {
        if(entity.getHeaders().get("Authorization") != null ) {
            log.debug(entity.getHeaders().get("Authorization").toString().replaceAll("\\[", "").replaceAll("\\]", "").substring(7));
            return entity.getHeaders().get("Authorization").toString().replaceAll("\\[", "").replaceAll("\\]", "").substring(7);
        }
        return null;
    }

    // Request의 Header에서 RefreshToken의 idx 값을 가져옵니다. "RefreshToken" : "db의 idx 값'
    public String resolveRefreshToken(HttpEntity entity) {
        if (entity.getHeaders().get("RefreshTokenIdx") != null) {
            log.debug(entity.getHeaders().get("RefreshTokenIdx").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
            return entity.getHeaders().get("RefreshTokenIdx").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        } return null;
    }

    // payload 에 저장된 id 조회
    public String getSubject(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }


    // 토큰 유효성 검사
    public boolean validateToken(String jwt, OauthJwt userJwt) {
        try {
            // access token 유효성 검사
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                    .parseClaimsJws(jwt)
                    .getBody();
            log.debug("유효한 access token 입니다.");
            return !claims.getExpiration().before(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            log.debug("만료된 access token 입니다.");
            return validateRefreshToken(jwt, userJwt);
        }
    }

    public boolean validateRefreshToken(String jwt, OauthJwt userJwt) {
        // refresh token 유효성 검사
        // idx 을 가지고 db에 저장된 refresh token 을 가져온 뒤
        String refreshToken = service.getUserJwt(userJwt).getRefreshToken();
        // 가져온 refresh token 을 다시 검증
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                .parseClaimsJws(refreshToken)
                .getBody();
        try {
            log.debug("유효한 refresh token 입니다.");
            return !claims.getExpiration().before(new Date(System.currentTimeMillis()));
        } catch (Exception e2) {
            log.debug("만료된 refresh token 입니다.");
            return false;
        }
    }
}
