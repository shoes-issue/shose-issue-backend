package com.issue.shoes.user.service;

import java.util.List;

import com.issue.shoes.communityBoard.vo.CommunityBoard;
import com.issue.shoes.tradeBoard.vo.PageNation;
import com.issue.shoes.user.vo.User;

public interface UserService {
	
	//유저 한명 검색
	String searchUser(String userId);
	//유저 생성
	User createUser(User user) throws Exception;
	//유저 수정
	User updateUser(User newUser) throws Exception;
	//유저 탈퇴
	String deleteUser(String userId) throws Exception;
	//oauth vo 삽입
	User createUseroauth(User user) throws Exception;
	
	User createPreference(User user) throws Exception;
	
	String deleteUseroauth(String userId) throws Exception;
	
	User selectUserById(String userId) throws Exception;
	
	boolean idDuplicate(String userId);
	//Preference vo 삽입
	List<CommunityBoard> selectcommunityAll(String userId) throws Exception;
	
	List<Object> tradeBoardWrite(PageNation page);
	
	List<Object> tradeBoardLike(PageNation page);
	
}
