package com.issue.shoes.communityBoard.controller;

import org.springframework.http.ResponseEntity;

import com.issue.shoes.communityBoard.vo.CommunityBoard;

public interface CommunityBoardController {
	
	/**
	 * 게시물 상세보기
	 * @return
	 */
	String searchCommunityBoard(String boardId);

	/**
	 * 유저 게시물 검색
	 * @return
	 */
	String searchUserCommunityBoard();
	
	/**
	 * 게시물 모두 검색
	 * @return
	 */
	String searchAllCommunityBoard();

	/**
	 * 게시물 제목 검색
	 * @return
	 */
	String searchCommunityBoardByTitle();
	
	/**
	 * 게시물 생성
	 * @return
	 */
	ResponseEntity<String> createCommunityBoard(CommunityBoard communityBoard);
	
	/**
	 * 게시물 수정
	 * @return
	 */
	String updateCommunityBoard();
	
	/**
	 * 게시물 삭제
	 * @return
	 */
	String deleteCommunityBoard();

}
