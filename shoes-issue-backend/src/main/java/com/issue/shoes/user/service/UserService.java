package com.issue.shoes.user.service;

import com.issue.shoes.user.vo.User;

public interface UserService {
	
	//유저 한명 검색
	String searchUser(String userId);
	//유저 생성
	User createUser(User user) throws Exception;
	//유저 수정
	User updateUser(User user) throws Exception;
	//유저 탈퇴
	String deleteUser(String userId) throws Exception;
}
