package com.issue.shoes.tradeBoard.controller;


import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.PageNation;
import com.issue.shoes.tradeBoard.vo.TradeBoardLike;

public interface TradeBoardController {
	
	//중고 게시물 카테고리 클릭
	String searchAllTradeBoard(PageNation page);
	//중고 게시물 등록
	String createTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile);
	//중고 게시물 제목 검색(카테고리 포함)
	String selectTradeTitle(PageNation page);
	//중고 게시물 상세보기
	String searchTradeBoard(String tradeId, String userId);
	//중고 게시물 수정페이지 이동
	String updateTradeBoard(String tradeId);
	//중고 게시물 수정완료
	String updateComplete(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile);	
	//중고 게시물 삭제
	String deleteTradeBoard(String tradeId, String tradeImage);
	//좋아요 클릭
	String clickLike(TradeBoardLike like);
	//게시물 상태 변경(거래예약)
	String changeStatusReservation(String tradeId, String writerId);
	//거래 취소
	String changeStatusCancel(String tradeId);
	//게시물 상태 변경(거래완료)
	String changeStatusComplete(String tradeId, String writerId);
	//랜딩페이지 이미지 및 기본정보 검색
	String selectRendingBody();
	//쪽지 보낸 이의 닉네임 검사
	String selectSenderNickName(String userId);

}
