package com.issue.shoes.tradeBoard.controller;


import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;

public interface TradeBoardController {
	
	//중고 게시물 카테고리 클릭
	String searchAllTradeBoard();
	//중고 게시물 등록
	String createTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile);
	//중고 게시물 제목 검색
	String selectTradeTitle(String keywordsearchTradeBoard);
	//중고 게시물 상세보기
	String searchTradeBoard(String tradeId, String userId);
	//중고 게시물 수정페이지 이동
	String updateTradeBoard(String tradeId);
	//중고 게시물 수정완료
	String updateComplete(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile);
	
	
	
	//중고 게시물 삭제
	String deleteTradeBoard();
	//중고 게시물 카테고리 검색
	String selectTradeCategory();
	//게시물 상태 변경(거래예약)
	String changeStatusReservation();
	//게시물 상태 변경(거래완료)
	String changeStatusComplete();
	

}
