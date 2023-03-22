package com.issue.shoes.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.tradeBoard.vo.PageNation;
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
	
//	List<CommunityBoard> searchAllCommunityBoard(Map<String, Object> requestBody) throws Exception;
	ResponseEntity<?> searchAllCommunityBoard(String requestBody) throws Exception;
	
	ResponseEntity<?> searchAllTradeBoard(String userId) throws Exception;
	
	String tradeBoardWrite(PageNation page);
	
	String tradeBoardLike(PageNation page);
	
//	ResponseEntity<Map<String, Boolean>> searchUser(String userId);

	
}
