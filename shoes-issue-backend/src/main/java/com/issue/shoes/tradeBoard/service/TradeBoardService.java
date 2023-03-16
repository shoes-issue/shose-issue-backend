package com.issue.shoes.tradeBoard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;
import com.issue.shoes.tradeBoard.vo.UpdateContent;

public interface TradeBoardService {
	
	List<TradeBoard> searchAllTradeBoard();

	List<TradeBoard> selectTradeTitle(String keyword);

	List<TradeBoard> insertTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile);

	TradeBoardDetail selectTradeBoardDetail(String tradeId, String userId);

	UpdateContent selectUpdateContent(String tradeId);

	List<TradeBoard> updateTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile);

}
