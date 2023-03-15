package com.issue.shoes.tradeBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.issue.shoes.tradeBoard.service.TradeBoardService;
import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;

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
	@PostMapping(value="/trade-board")
	public String createTradeBoard(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile) {
		
		List<TradeBoard> list = service.insertTradeBoard(tradeBoard, uploadFile);
		
		String tradeBoardList = gson.toJson(list);
		
		return tradeBoardList;
	}
	
	@Override
	@GetMapping(value="/trade-board/{tradeId}/{userId}")
	public String searchTradeBoard(@PathVariable String tradeId, @PathVariable String userId) {
		
		TradeBoardDetail boardDetail = service.selectTradeBoardDetail(tradeId, userId);
		
		String tradeBoardDetail = gson.toJson(boardDetail);
		
		return tradeBoardDetail;
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
