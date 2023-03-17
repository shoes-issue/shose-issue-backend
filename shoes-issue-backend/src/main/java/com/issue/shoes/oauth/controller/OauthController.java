package com.issue.shoes.oauth.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.issue.shoes.user.vo.User;

public interface OauthController {

	//로그인
	User getUser(String userId, HttpEntity entity) throws Exception;
	//문자로 인증번호 전송
	ResponseEntity<Object> loginUserToken(User user);
	//문자 인증번호 검증
//	Object logoutUser(HttpEntity entity);

	Object update(User user);

	User loginUser(User user);
	//카카오로그인
//	String home();

//	ResponseEntity<Object> kakaoCallback(String code) throws Exception;
	
//	void mainredirect() throws Exception;
}