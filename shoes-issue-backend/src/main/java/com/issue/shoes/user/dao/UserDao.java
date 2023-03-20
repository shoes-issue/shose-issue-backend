package com.issue.shoes.user.dao;

import com.issue.shoes.user.vo.User;

public interface UserDao {

	User selectLoginUser(User user);
	
	User selectByUserId(String userId);

	void insertUser(User user) throws Exception;

	void updateUser(User user) throws Exception;

	void deleteUser(String userId) throws Exception;

	void insertUseroauth(User user) throws Exception;
	
	void insertPreference(User user) throws Exception;

	void deleteUseroauth(String userId) throws Exception;
	


}
