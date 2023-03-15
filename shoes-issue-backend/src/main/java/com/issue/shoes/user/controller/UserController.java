package com.issue.shoes.user.controller;

import org.springframework.http.ResponseEntity;

import com.issue.shoes.user.vo.User;

public interface UserController {
	
	//유저 한명 검색
	String searchUser();
	//유저 생성
	ResponseEntity<?> createUser(User user);
	//유저 수정
	String updateUser();
	//유저 탈퇴
	String deleteUser();

}
