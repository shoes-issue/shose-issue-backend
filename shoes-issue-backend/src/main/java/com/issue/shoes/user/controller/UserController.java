package com.issue.shoes.user.controller;



public interface UserController {
	
	//유저 한명 검색
	String searchUser();
	//유저 생성
	String createUser();
	//유저 수정
	String updateUser();
	//유저 탈퇴
	String deleteUser();
	
}
