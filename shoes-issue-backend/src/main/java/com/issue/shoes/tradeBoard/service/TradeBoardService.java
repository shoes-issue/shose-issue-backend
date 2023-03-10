package com.issue.shoes.tradeBoard.service;

import java.util.List;

import com.issue.shoes.tradeBoard.vo.TradeBoard;

public interface TradeBoardService {
	
	List<TradeBoard> searchAllTradeBoard();

	List<TradeBoard> selectTradeTitle(String keyword);

}
