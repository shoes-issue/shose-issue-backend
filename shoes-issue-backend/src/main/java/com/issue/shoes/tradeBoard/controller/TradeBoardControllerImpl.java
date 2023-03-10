package com.issue.shoes.tradeBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.issue.shoes.tradeBoard.service.TradeBoardService;
import com.issue.shoes.tradeBoard.vo.TradeBoard;

@RestController
@CrossOrigin(origins="http://localhost:8080", allowCredentials = "true")
public class TradeBoardControllerImpl implements TradeBoardController{

	@Autowired
	private TradeBoardService service;
	@Autowired
	private Gson gson;
	
	@Override
	@GetMapping(value="/trade-board/all")
	public String searchAllTradeBoard() {
		
		List<TradeBoard> list = service.searchAllTradeBoard();
		
		String tradeBoardList = gson.toJson(list);
		
		return tradeBoardList;
	}
	
	@Override
	@GetMapping(value="/trade-board/{title}")
	public String selectTradeTitle(@PathVariable("title")String keyword) {
		
		List<TradeBoard> list = service.selectTradeTitle(keyword);
		
		String tradeBoardList = gson.toJson(list);
		
		return tradeBoardList;
	}
	
	@Override
	public String searchTradeBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createTradeBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateTradeBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteTradeBoard() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String selectTradeCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeStatusReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeStatusComplete() {
		// TODO Auto-generated method stub
		return null;
	}

}
