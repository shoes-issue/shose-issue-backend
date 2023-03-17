package com.issue.shoes.tradeBoard.dao;

import java.util.HashMap;
import java.util.List;

import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;
import com.issue.shoes.tradeBoard.vo.TradeBoardLike;
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

	int deleteDateUpdate(HashMap<String, String> map) throws Exception;

	String selectClickUser(TradeBoardLike like);

	void insertLike(TradeBoardLike like) throws Exception;

	void deletLike(TradeBoardLike like)throws Exception;

	int updateStatus(HashMap<String, String> map) throws Exception;
}
