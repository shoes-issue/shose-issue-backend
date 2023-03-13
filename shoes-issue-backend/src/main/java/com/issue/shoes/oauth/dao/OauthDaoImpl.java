package com.issue.shoes.oauth.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.oauth.vo.OauthJwt;

@Repository
public class OauthDaoImpl implements OauthDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public OauthJwt selectUserJwt(OauthJwt oauthJwt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertJwtWithIdx(OauthJwt oauthJwt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUserJwt(OauthJwt oauthJwt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OauthJwt selectUserJwtBySubject(OauthJwt oauthJwt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUserJwt(OauthJwt oauthJwt) {
		// TODO Auto-generated method stub
		return 0;
	}

}