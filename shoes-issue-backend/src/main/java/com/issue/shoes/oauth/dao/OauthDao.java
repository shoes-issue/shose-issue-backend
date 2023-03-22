package com.issue.shoes.oauth.dao;

import com.issue.shoes.oauth.vo.OauthJwt;

public interface OauthDao {

    OauthJwt selectUserJwt(OauthJwt oauthJwt);

    int insertJwtWithIdx(OauthJwt oauthJwt);

    int updateUserJwt(OauthJwt oauthJwt);

    OauthJwt selectUserJwtBySubject(OauthJwt oauthJwt);

    int deleteUserJwt(OauthJwt oauthJwt);

}