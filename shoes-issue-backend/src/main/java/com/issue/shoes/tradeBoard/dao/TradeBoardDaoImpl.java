package com.issue.shoes.tradeBoard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.tradeBoard.vo.TradeBoard;

@Repository
public class TradeBoardDaoImpl implements TradeBoardDao {
	
	Logger logger = LogManager.getLogger("case3");
	
	@Autowired
	private SqlSession session;

	@Override
	public List<TradeBoard> searchAllTradeBoard() {
		
		List<TradeBoard> list = session.selectList("tradeBoard.searchAllTradeBoard");
		
		return list;
	}

	@Override
	public List<TradeBoard> selectTradeTitle(String keyword) {
		
		List<TradeBoard> list = session.selectList("tradeBoard.selectTradeTitle", keyword);
		
		return list;
	}
	
	
}
