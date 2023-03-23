package com.issue.shoes.game.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.issue.shoes.game.dao.GameDao;
import com.issue.shoes.user.vo.User;

@Service
public class GameService {

	Logger log = LogManager.getLogger("case3");

	@Autowired
	GameDao dao;

	public User getUserPoint(String userId) {
		User result = null;

		try {
			result = dao.selectOneUser(userId);
		} catch (Exception e) {
			log.debug("GameService-GetUserPoint={}", e);
		}
		return result;
	}

	public int updateAddPoint(String userId) {
		int result = 0;
		try {
			result = dao.updateAddPoint(userId);
		} catch (Exception e) {
			log.debug("GameService-GetUserPoint={}", e);
		}
		return result;
	}

	public int updateMinusPoint(String userId) {
		int result = 0;
		try {
			result = dao.updateMinusPoint(userId);
		} catch (Exception e) {
			log.debug("GameService-GetUserPoint={}", e);
		}
		return result;
	}

}
