package com.issue.shoes.communityBoard.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.issue.shoes.communityBoard.service.CommunityBoardServiceImpl;
import com.issue.shoes.communityBoard.vo.CommunityBoard;

@RequestMapping(value="/board")
@RestController // data형태로 결과를 반환하기 위한 controller
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "Content-Type", allowCredentials = "true")
// 자동으로 설정된 헤더들 말고, 내가 추가로 설정한 헤더 설정이 있으면 이 옵션을 넣어줘야해
public class CommunityControllerImpl implements CommunityBoardController {

	// 로그 추가
	Logger log = LogManager.getLogger("case3");

	// gson 추가
//	@Autowired
//	private Gson gson;
	
	// service 주입
	@Autowired
	private CommunityBoardServiceImpl service; 
	
	@Override
	@GetMapping(produces = "application/json; charset=UTF-8")
	public String searchCommunityBoard(CommunityBoard communityBoard) {
		
		
		// 게시물 상세보기
		// 게시물 id를 받아서
		// service에 보내고
		// 결과를 json으로 만들어서
		// return
		String result = "";
		return result;
	}

	@Override
	public String searchUserCommunityBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchAllCommunityBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchCommunityBoardByTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createCommunityBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCommunityBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCommunityBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
