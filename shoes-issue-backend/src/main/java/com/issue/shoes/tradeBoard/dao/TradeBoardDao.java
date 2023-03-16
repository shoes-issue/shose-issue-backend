package com.issue.shoes.tradeBoard.dao;

import java.util.List;

import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;
import com.issue.shoes.tradeBoard.vo.UpdateContent;

public interface TradeBoardDao {

	List<TradeBoard> searchAllTradeBoard();

	List<TradeBoard> selectTradeTitle(String keyword);

	int insertTradeBoard(InsertTradeBoard tradeBoard) throws Exception;

	TradeBoardDetail selectTradeBoardDetail(String tradeId) throws Exception;

	TradeBoardDetail selecTradeBoardUser(String userId) throws Exception;

	UpdateContent selectUpdateContent(String tradeId);

	void updateTradeBoard(InsertTradeBoard tradeBoard) throws Exception;

	void updateTradeBoardIncludeImg(InsertTradeBoard tradeBoard) throws Exception;

}
