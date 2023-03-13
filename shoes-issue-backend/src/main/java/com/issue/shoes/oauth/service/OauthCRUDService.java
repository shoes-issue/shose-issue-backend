package com.issue.shoes.oauth.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.issue.shoes.oauth.dao.OauthDao;
import com.issue.shoes.oauth.vo.OauthJwt;
import com.issue.shoes.user.dao.UserDao;
import com.issue.shoes.user.vo.User;

@Service
public class OauthCRUDService implements OauthService {

    private Logger log = LogManager.getLogger("case3");

	@Autowired
	private OauthDao oauthdao;
	
	@Autowired
	private UserDao userdao;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${secretKey}")
    private String key;
    
    @Override
    public Boolean loginUser(User user) {
        // login 시 사용하는 id 만 가지고 일단 db 를 불러온 뒤
    	User loginUser = userdao.selectLoginUser(user);

        // 만약 유저 아이디가 일치 하지 않으면 db 에 조회가 안될 것이고,
        if(loginUser == null) {
            log.debug("해당 아이디의 유저가 존재하지 않습니다.");
            return false;
        }

        // 만약 비밀번호가 일치하지 않는다면
        if(!passwordEncoder.matches(user.getUserPw(), loginUser.getUserPw())) {
            log.debug(passwordEncoder.encode(user.getUserPw()));
            log.debug(user.getUserPw());
            log.debug(loginUser.getUserPw());
            log.debug("비밀번호가 일치하지 안습니다.");
            return false;
        }
        log.debug("로그인에 성공했습니다.");
        return true;
    }
    
    @Override
    @Transactional
    public OauthJwt createUserJwt(OauthJwt oauthJwt) {
        oauthJwt.setUserJwtIdx(passwordEncoder.encode(oauthJwt.getSubject()+key));
        oauthdao.insertJwtWithIdx(oauthJwt);
        return oauthdao.selectUserJwt(oauthJwt);
    }

    @Override
    public OauthJwt getUserJwt(OauthJwt oauthJwt) {
        return oauthdao.selectUserJwt(oauthJwt);
    }

    @Override
    public OauthJwt getUserJwtBySubject(OauthJwt oauthJwt) {
        return oauthdao.selectUserJwtBySubject(oauthJwt);
    }

    @Override
    @Transactional
    public OauthJwt editUserJwt(OauthJwt oauthJwt) {
    	oauthdao.updateUserJwt(oauthJwt);
        return oauthdao.selectUserJwt(oauthJwt);
    }

    @Override
    public int removeUserJwt(OauthJwt oauthJwt) {
        return oauthdao.deleteUserJwt(oauthJwt);
    }

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(OauthJwt oauthJwt) {
		// TODO Auto-generated method stub
		return null;
	}

}