package com.issue.shoes.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.user.vo.User;

public interface UserController {
	
	//유저 ID 중복검색
	ResponseEntity<Map<String, Boolean>> searchUser(Map<String, String> requestBody);
	//유저 생성
	ResponseEntity<?> createUser(User user);
	//유저 수정
	ResponseEntity<?> updateUser(MultipartFile profileImage, User user);
	//유저 탈퇴
	ResponseEntity<?> deleteUser(String userId);
	
	ResponseEntity<?> selectUserById(String userId) throws Exception;
	
//	ResponseEntity<Map<String, Boolean>> searchUser(String userId);

	
}
