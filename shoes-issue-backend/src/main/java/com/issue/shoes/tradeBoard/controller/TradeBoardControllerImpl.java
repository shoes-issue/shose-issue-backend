package com.issue.shoes.tradeBoard.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.issue.shoes.tradeBoard.service.TradeBoardService;
import com.issue.shoes.tradeBoard.vo.InsertTradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoard;
import com.issue.shoes.tradeBoard.vo.TradeBoardDetail;
import com.issue.shoes.tradeBoard.vo.TradeBoardLike;
import com.issue.shoes.tradeBoard.vo.UpdateContent;

@RestController
@CrossOrigin(origins="http://localhost:8080", allowCredentials = "true")
public class TradeBoardControllerImpl implements TradeBoardController{

	@Autowired
	private TradeBoardService service;
	@Autowired
	private Gson gson;
	
	Logger log = LogManager.getLogger("case3");
	
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
	@GetMapping(value="/trade-board/", params="tradeId")
	public String updateTradeBoard(String tradeId) {
		
		UpdateContent contents = service.selectUpdateContent(tradeId);
		
		String sendContent = gson.toJson(contents);
		
		return sendContent;
	}

	@Override
	@PostMapping(value="/trade-board/board")
	public String updateComplete(InsertTradeBoard tradeBoard, MultipartFile[] uploadFile) {
		
		List<TradeBoard> list = service.updateTradeBoard(tradeBoard, uploadFile);
		
		String tradeBoardList = gson.toJson(list);
		
		return tradeBoardList;
	}
	
	@Override
	@DeleteMapping(value="/trade-board/")
	public String deleteTradeBoard(String tradeId, String tradeImage) {
		
		List<TradeBoard> list = service.deleteTradeBoard(tradeId, tradeImage);
		
		String tradeBoardList = gson.toJson(list);
		
		return tradeBoardList;
	}
	
	@Override
	@GetMapping(value="/trade-board/", params= {"tradeId", "userId"})
	public String clickLike(TradeBoardLike like) {
		
		int result = service.clickLike(like);
		
		String resultString = gson.toJson(result);
		
		return resultString;
	}

	@Override
	public String selectTradeCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PutMapping(value="/trade-board/status", params="tradeStatus=예약 가능")
	public String changeStatusReservation(String tradeId, String tradeStatus) {
		
		String statusString = service.updateStatus(tradeId, tradeStatus);
		
		return statusString;
	}

	@Override
	@PutMapping(value="/trade-board/trade-status")
	public String changeStatusCancel(String tradeId) {
		
		String statusString = service.updateStatusCancel(tradeId);
		
		return statusString;
	}
	
	@Override
	public String changeStatusComplete() {
		// TODO Auto-generated method stub
		return null;
	}


}
