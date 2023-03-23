package com.issue.shoes.game.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.user.vo.User;

@Repository
public class GameDao {

	@Autowired
	private SqlSession session;
	
	public User selectOneUser(String userId) throws Exception {
		return session.selectOne("User.selectOnePoint",userId);
	}

	public int updateAddPoint(String userId) throws Exception {
		return session.update("User.addPoint",userId);
	}

	public int updateMinusPoint(String userId) throws Exception {
		return session.update("User.minusPoint",userId);
	}


}
