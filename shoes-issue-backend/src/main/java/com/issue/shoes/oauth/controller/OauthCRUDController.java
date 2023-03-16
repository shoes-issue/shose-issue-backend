package com.issue.shoes.oauth.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;
import com.issue.shoes.common.util.JwtUtil;
import com.issue.shoes.oauth.service.OauthService;
import com.issue.shoes.oauth.vo.OauthJwt;
import com.issue.shoes.user.vo.User;

@Controller
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(value = "/oauth", produces = "application/json; charset=utf-8")
public class OauthCRUDController implements OauthController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private OauthService service;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private Gson gson;


	@Override
	@GetMapping(value = "/{userNo}")
	public User getUser(@PathVariable String userId, HttpEntity entity){
		log.debug("유저 조회");

		// access token 의 유효성 검사
		String token = jwtUtil.resolveAccessToken(entity);

		if(jwtUtil.validateToken(token, OauthJwt.builder()
				.userJwtIdx(jwtUtil.resolveRefreshToken(entity))
				.build()
				)){
			// 유저 정보 조회
			User user = service.getUser(OauthJwt.builder().userId(userId).build());
			return user;
		}
		else {
			return null;
		}
	}

	@Override
	@PostMapping(value = "/login")
	public ResponseEntity<Object> loginUserToken(@RequestBody User user) {
		Boolean isLoginValidation = service.loginUser(user);
		Map<String, Object> map = new HashMap<>();
		HttpHeaders headers = new HttpHeaders();
		if(isLoginValidation) {
			String accessToken = jwtUtil.createAccessJwt(user.getUserId());
			String userJwtIdx = jwtUtil.createRefreshJwt(user.getUserId());
			jwtUtil.setHeaderAccessToken(headers, accessToken);
			jwtUtil.setHeaderRefreshToken(headers, userJwtIdx);
			map.put("success", user.getUserId());
			map.put("Authorization", accessToken);
			map.put("RefreshTokenIdx", userJwtIdx);
		} else {
			map.put("success", false);
		}
		String json = gson.toJson(map);
		return ResponseEntity.ok()
				.headers(headers)
				.body(json);
	}

	@Override
	@GetMapping("/kakaologin")
	@ResponseBody
	public String home() {
		String url = "https://kauth.kakao.com/oauth/authorize?client_id=1c5c44bb7c9666ba81f5ffd3a2b86d76&redirect_uri=http://localhost:80/oauth/kakaologincallback&response_type=code";
		System.out.println("login 컨트롤러 접근");
		return url;
	}
	
	@Override
	@GetMapping(value="/kakaologincallback")
	public ResponseEntity<Object> kakaoCallback(@RequestParam(value = "code", required = false) String code) throws Exception{
	    System.out.println("#########" + code);
	    String access_Token = service.getAccessToken(code);
	    HashMap<String, Object> userInfo = service.getUserInfo(access_Token);
	    
	    System.out.println("###access_Token#### : " + access_Token);
	    System.out.println("###userInfo#### : " + userInfo.get("email"));
	    System.out.println("###nickname#### : " + userInfo.get("nickname"));
	    System.out.println("###profile_image#### : " + userInfo.get("profile_image"));
	    String email = (String) userInfo.get("email");
	    String nickname = (String) userInfo.get("nickname");
	    String profile_image = (String) userInfo.get("profile_image");

	    // 인증 토큰 생성
	    String accessToken = jwtUtil.createAccessJwt(email);
	    HttpHeaders headers = new HttpHeaders();
	    jwtUtil.setHeaderAccessToken(headers, accessToken);

	    // 응답 데이터 생성
	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("access_token", accessToken);
	    responseData.put("email", email);
	    responseData.put("nickname", nickname);
	    responseData.put("profile_image", profile_image);

	    return ResponseEntity.ok().headers(headers).body(responseData);
	}

	@Override
	@PostMapping(value = "/logout")
	public ResponseEntity<Object> logoutUser(HttpEntity entity) {
		log.debug("로그 아웃");

		// access token header 에서 추출
		String token = jwtUtil.resolveAccessToken(entity);
		String subject = jwtUtil.getSubject(token);
		String userJwtIdx = jwtUtil.resolveRefreshToken(entity);

		// access token 이 만료되었다면?
		// subject 를 가져올 수 없게 된다.
		// 그러면 그냥 idx를 기반으로 조회해서 삭제해준다.
		OauthJwt oauthJwt = OauthJwt.builder()
				.userJwtIdx(userJwtIdx)
				.subject(subject)
				.build();
		Map<String, Object> map = new HashMap<>();
		if(jwtUtil.validateToken(token, oauthJwt)) {
			if(jwtUtil.removeRefreshJwt(token, oauthJwt)) {
				map.put("success", true);
			} else {
				map.put("success", false);
			}
		} else {
			map.put("validate", false);
		}
		return ResponseEntity.ok().body(map);
	}

	@Override
	public Object update(User user) {
		return null;
	}

	@Override
	public User loginUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}