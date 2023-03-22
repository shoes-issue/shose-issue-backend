package com.issue.shoes.tradeBoard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.PageNation;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;
import com.issue.shoes.tradeBoard.vo.TradeBoardLike;
import com.issue.shoes.tradeBoard.vo.UpdateContent;

public interface TradeBoardService {
	
	List<Object> searchAllTradeBoard(PageNation page);

	List<Object> selectTradeTitle(PageNation page);

	List<Object> insertTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile);

	TradeBoardDetail selectTradeBoardDetail(String tradeId, String userId);

	UpdateContent selectUpdateContent(String tradeId);

	List<Object> updateTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile);

	List<Object> deleteTradeBoard(String tradeId, String tradeImage);

	int clickLike(TradeBoardLike like);

	String updateStatus(String tradeId, String tradeStatus);

	String updateStatusCancel(String tradeId);

	String updateStatusComplete(String tradeId);


}
