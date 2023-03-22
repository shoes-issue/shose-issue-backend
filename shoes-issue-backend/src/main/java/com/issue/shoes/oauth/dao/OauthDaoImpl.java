package com.issue.shoes.oauth.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.oauth.vo.OauthJwt;

@Repository
public class OauthDaoImpl implements OauthDao {
	
    private Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private SqlSession session;

	
    @Override
    public OauthJwt selectUserJwt(OauthJwt oauthJwt) {
        return session.selectOne("userJwtMapper.selectUserJwt", oauthJwt);
    }

    @Override
    public OauthJwt selectUserJwtBySubject(OauthJwt oauthJwt) {
        return session.selectOne("userJwtMapper.selectUserJwtBySubject", oauthJwt);
    }
    
    @Override
    public int insertJwtWithIdx(OauthJwt oauthJwt) {
        return session.insert("userJwtMapper.insertJwtWithIdx", oauthJwt);
    }

    @Override
    public int updateUserJwt(OauthJwt oauthJwt) {
        return session.update("userJwtMapper.updateUserJwtWithIdx", oauthJwt);
    }

    @Override
    public int deleteUserJwt(OauthJwt oauthJwt) {
        return session.delete("userJwtMapper.deleteUserJwtWithUserJwtIdx", oauthJwt);
    }

}