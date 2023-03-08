package com.issue.shoes.communityBoard.controller;

public interface CommunityController {
	
	//게시물 상세보기
	String searchCommunityBoard();
	//유저 게시물 검색
	String searchUserCommunityBoard();
	//게시물 모두 검색
	String searchAllCommunityBoard();
	//게시물 제목 검색
	String searchCommunityBoardByTitle();
	//게시물 생성
	String createCommunityBoard();
	//게시물 수정
	String updateCommunityBoard();
	//게시물 삭제
	String deleteCommunityBoard();

}
