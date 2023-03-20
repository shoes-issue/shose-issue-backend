package com.issue.shoes.tradeBoard.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.PageNation;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;
import com.issue.shoes.tradeBoard.vo.TradeBoardLike;
import com.issue.shoes.tradeBoard.vo.UpdateContent;

@Repository
public class TradeBoardDaoImpl implements TradeBoardDao {
	
	Logger logger = LogManager.getLogger("case3");
	
	@Autowired
	private SqlSession session;

	@Override
	public List<TradeBoard> searchAllTradeBoard(PageNation page) throws Exception {
		
		List<TradeBoard> list = session.selectList("tradeBoard.searchAllTradeBoard", page);
		
		return list;
	}
	
	@Override
	public int countTradeBoard() throws Exception {
		
		int count = session.selectOne("tradeBoard.countTradeBoard");
		
		return count;
	}

	@Override
	public List<TradeBoard> selectTradeTitle(HashMap<String, String> map) throws Exception {
		
		List<TradeBoard> list = session.selectList("tradeBoard.selectTradeTitle", map);
		
		return list;
	}

	@Override
	public int insertTradeBoard(InsertTradeBoard tradeBoard) throws Exception {
		
		int result = 0;
		
		result = session.insert("tradeBoard.insertTradeBoard", tradeBoard);
		
		if (result != 1) {
			throw new Exception();
		}
		
		return result;
	}

	@Override
	public TradeBoardDetail selectTradeBoardDetail(String tradeId) throws Exception{
		
		TradeBoardDetail boardDetail = session.selectOne("tradeBoard.boardDetail", tradeId);
		
		return boardDetail;
	}

	@Override
	public TradeBoardDetail selecTradeBoardUser(String userId) throws Exception{
		
		TradeBoardDetail user = session.selectOne("tradeBoard.user", userId);
		
		return user;
	}

	@Override
	public UpdateContent selectUpdateContent(String tradeId) {
		
		UpdateContent contents = session.selectOne("tradeBoard.contents", tradeId);
		
		return contents;
	}

	@Override
	public void updateTradeBoard(InsertTradeBoard tradeBoard) throws Exception{
		
		int result = 0;
		
		result = session.update("tradeBoard.excludeUpdate", tradeBoard);
		
		if (result != 1) {
			throw new Exception();
		}
		
	}

	@Override
	public void updateTradeBoardIncludeImg(InsertTradeBoard tradeBoard) throws Exception {
		
		int result = 0;
		
		result = session.update("tradeBoard.includeUpdate", tradeBoard);
		
		if (result != 1) {
			throw new Exception();
		}
	}

	@Override
	public int deleteDateUpdate(HashMap<String,String> map) throws Exception {
		
		int result = 0;
		
		result = session.update("tradeBoard.updateDeleteDate", map);
		
		if (result != 1) {
			throw new Exception();
		}
		
		return result;
	}

	@Override
	public String selectClickUser(TradeBoardLike like) {
		
		String clickUser = session.selectOne("tradeBoard.clickLikeUser", like);
		
		return clickUser;
	}

	@Override
	public void insertLike(TradeBoardLike like) throws Exception{
		
		int result = 0;
		
		result = session.insert("tradeBoard.insertLike", like);
		
		if (result != 1) {
			throw new Exception();
		}
	}

	@Override
	public void deletLike(TradeBoardLike like) throws Exception {

		int result = 0;
		
		result = session.delete("tradeBoard.deleteLike", like);
		
		if (result != 1) {
			throw new Exception();
		}
		
	}

	@Override
	public int updateStatus(HashMap<String, String> map) throws Exception{
		
		int result = 0;
		
		result = session.update("tradeBoard.updateStatus", map);
		
		if (result != 1) {
			throw new Exception();
		}
		
		return result;
	}

	
	
	
	
}
