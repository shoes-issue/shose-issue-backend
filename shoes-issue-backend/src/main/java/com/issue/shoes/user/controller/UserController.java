package com.issue.shoes.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.user.vo.User;

public interface UserController {
	
	//유저 한명 검색
	String searchUser();
	//유저 생성
	ResponseEntity<?> createUser(User user);
	//유저 수정
	ResponseEntity<?> updateUser(MultipartFile profileImage, User user);
	//유저 탈퇴
	ResponseEntity<?> deleteUser(String userId);
	
	ResponseEntity<?> selectUserById(String userId) throws Exception;


}
