package com.issue.shoes.communityBoard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

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
	List<CommunityBoard> searchAllCommunityBoard();

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
	String updateCommunityBoard(String boardId, CommunityBoard communityBoard);
	
	/**
	 * 게시물 삭제
	 * @return
	 */
	String deleteCommunityBoard(@PathVariable String userId, CommunityBoard communityBoard);

}
