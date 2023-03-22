package com.issue.shoes.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.communityBoard.vo.CommunityBoard;
import com.issue.shoes.tradeBoard.vo.PageNation;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.user.vo.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession session;

	@Override
	public List<TradeBoard> searchMypageAllTradeBoard(PageNation page) throws Exception {
		
		List<TradeBoard> list = session.selectList("User.searchAllTradeBoard", page);
		
		return list;
	}
	
	@Override
	public List<TradeBoard> searchMypagelikeTradeBoard(PageNation page) throws Exception {
		
		List<TradeBoard> list = session.selectList("User.searchMypagelikeTradeBoard", page);
		
		return list;
	}
	
	@Override
	public int countTradeBoard() throws Exception {
		
		int count = session.selectOne("User.countTradeBoard");
		
		return count;
	}

	
	@Override
	public User selectLoginUser(User user) {
		User result = null;

		result = session.selectOne("User.selectLoginUser", user);

		return result;
	}

	@Override
	public User selectByUserId(String userId) {
		User result = null;

		result = session.selectOne("User.selectUserById", userId);

		return result;
	}

	@Override
	public void insertUser(User user) throws Exception {
		int result = 0;

		result = session.insert("User.insertUser", user);

		if (result != 1) {
			throw new Exception();
		}
	}

	@Override
	public void insertUseroauth(User user) throws Exception {
		int result = 0;

		result = session.insert("User.insertUseroauth", user);

		if (result != 1) {
			throw new Exception();
		}
	}
	
	@Override
	public void insertPreference(User user) throws Exception {
		int result = 0;

		result = session.insert("User.insertPreference", user);

		if (result != 1) {
			throw new Exception();
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		int result = 0;

		result = session.update("User.updateUser", user);

		if (result != 1) {
			throw new Exception();
		}
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		int result = 0;

		result = session.update("User.deleteUser", userId);

		if (result != 1) {
			throw new Exception();
		}
	}

	@Override
	public void deleteUseroauth(String userId) throws Exception {
		int result = 0;

		result = session.update("User.deleteUseroauth", userId);

		if (result != 1) {
			throw new Exception();
		}

	}

	@Override
	public List<CommunityBoard> selectcommunityAll(String userId) {

		List<CommunityBoard> result = null;

		result = session.selectList("User.selectcommunityAll", userId);

		return result;
	}
}

