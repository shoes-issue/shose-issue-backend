package com.issue.shoes.user.controller;

import org.springframework.http.ResponseEntity;

import com.issue.shoes.user.vo.User;

public interface UserController {
	
	//유저 한명 검색
	String searchUser();
	//유저 생성
	ResponseEntity<?> createUser(User user);
	//유저 수정
	ResponseEntity<?> updateUser(User user);
	//유저 탈퇴
	ResponseEntity<?> deleteUser(String userId);
	
	ResponseEntity<?> selectUserById(String userId) throws Exception;


}
