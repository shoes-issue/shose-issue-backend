package com.issue.shoes.oauth.controller;

public interface OauthController {
	
	//로그인
	String loginUser();
	//문자로 인증번호 전송
	String sendTokenToSMS();
	//문자 인증번호 검증
	String checkSMSTokenValid();
}
