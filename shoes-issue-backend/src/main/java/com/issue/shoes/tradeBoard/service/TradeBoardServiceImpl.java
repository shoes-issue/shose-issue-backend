package com.issue.shoes.tradeBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.issue.shoes.tradeBoard.dao.TradeBoardDao;
import com.issue.shoes.tradeBoard.vo.TradeBoard;

@Service
public class TradeBoardServiceImpl implements TradeBoardService{

	@Autowired
	private TradeBoardDao dao;
	
	@Override
	public List<TradeBoard> searchAllTradeBoard() {
		
		List<TradeBoard> list = dao.searchAllTradeBoard();
		
		return list;
	}

	@Override
	public List<TradeBoard> selectTradeTitle(String keyword) {
		
		List<TradeBoard> list = dao.selectTradeTitle(keyword);
		
		return list;
	}
	
}
