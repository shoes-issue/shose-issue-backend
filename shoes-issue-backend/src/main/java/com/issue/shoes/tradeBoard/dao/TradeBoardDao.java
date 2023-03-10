package com.issue.shoes.tradeBoard.dao;

import java.util.List;

import com.issue.shoes.tradeBoard.vo.TradeBoard;

public interface TradeBoardDao {

	List<TradeBoard> searchAllTradeBoard();

	List<TradeBoard> selectTradeTitle(String keyword);

}
