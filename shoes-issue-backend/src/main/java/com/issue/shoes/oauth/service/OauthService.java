package com.issue.shoes.oauth.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.issue.shoes.oauth.vo.OauthJwt;
import com.issue.shoes.user.vo.User;

public interface OauthService {

	List<User> getUsers();

	User getUser(OauthJwt oauthJwt);

	Boolean loginUser(User user);

	OauthJwt getUserJwt(OauthJwt oauthJwt);

	OauthJwt createUserJwt(OauthJwt oauthJwt);

	OauthJwt editUserJwt(OauthJwt build);

	OauthJwt getUserJwtBySubject(OauthJwt build);

	int removeUserJwt(OauthJwt oauthJwt);

	String getAccessToken(String authorize_code);

	HashMap<String, Object> getUserInfo(String access_Token);

}