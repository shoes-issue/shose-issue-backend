package com.issue.shoes.oauth.service;

import java.util.List;

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
}