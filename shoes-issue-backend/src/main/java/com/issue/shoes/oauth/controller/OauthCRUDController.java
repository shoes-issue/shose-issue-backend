package com.issue.shoes.oauth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.issue.shoes.common.util.JwtUtil;
import com.issue.shoes.oauth.service.OauthService;
import com.issue.shoes.oauth.vo.OauthJwt;
import com.issue.shoes.user.vo.User;

@Controller
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(value = "/api", produces = "application/json; charset=utf-8")
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
	    } else {
	        map.put("success", false);
	    }
	    String json = gson.toJson(map);
	    return ResponseEntity.ok()
	            .headers(headers)
	            .body(json);
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
