package com.issue.shoes.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.user.vo.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public User selectLoginUser(User user) {
		User result = null;
	    
	    result = session.selectOne("User.selectLoginUser", user);
	    System.out.println(result);
	    
	    return result;
	}
	
	@Override
	public User selectUserById(String userId) {
	    User result = null;
	    
	    result = session.selectOne("User.insertUser", userId);
	    
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
	    
	    result = session.delete("User.deleteUser", userId);
	   
	    if (result != 1) {
	    	throw new Exception();
	    }
	}

}

	