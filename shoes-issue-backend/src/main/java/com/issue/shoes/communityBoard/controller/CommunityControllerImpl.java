package com.issue.shoes.communityBoard.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.issue.shoes.communityBoard.service.CommunityBoardServiceImpl;
import com.issue.shoes.communityBoard.vo.CommunityBoard;

@RequestMapping(value = "/board")
@RestController // data형태로 결과를 반환하기 위한 controller
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "Content-Type", allowCredentials = "true")
// 자동으로 설정된 헤더들 말고, 내가 추가로 설정한 헤더 설정이 있으면 이 옵션을 넣어줘야해
public class CommunityControllerImpl implements CommunityBoardController {

	// javadoc
	// sagger
	
	// 로그 추가
	Logger log = LogManager.getLogger("case3");

	// gson 추가
	@Autowired
	private Gson gson;

	// service 주입
	@Autowired
	private CommunityBoardServiceImpl service;

	// 게시글 상세 조회
	@Override
	@GetMapping(value = "/{boardId}", produces = "application/json; charset=UTF-8")
	public String searchCommunityBoard(@PathVariable("boardId") String boardId) {
		// 게시물 상세보기
		log.debug("boardId 조회={}", boardId);

		// service 실행하기
		// 게시물 아이디를 가진 boardVO로 Data 전달하기
		CommunityBoard result = service.findOneCommunityBoard(boardId);
		log.debug("예시 결과={}", result);

		// 실행한 결과 json으로 변환하기
		String jsonResult = gson.toJson(result);

		return jsonResult;
	}

	// 게시글 검색
	@Override
	@GetMapping(value = "/123")
	public String searchUserCommunityBoard() {
		log.debug("123");
		return null;
	}

	@Override
	@GetMapping(produces = "application/json; charset=UTF-8")
	public List<CommunityBoard> searchAllCommunityBoard() {
		log.debug("searchAllCommunityBoard 실행");

		List<CommunityBoard> result = service.findAllCommunityBoard();

//		String jsonResult = gson.toJson(result);
//		System.out.println("서비스 실행후");
		return result;
	}

	@Override
	public String searchCommunityBoardByTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PutMapping(produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> createCommunityBoard(@RequestBody CommunityBoard communityBoard) {

		log.debug("createCommunityBoard 실행={}", communityBoard);
		
		// UUID로 boardId 생성
		UUID uuid = UUID.randomUUID();
		communityBoard.setBoardId(uuid.toString());
		
		int result = service.createCommunityBoard(communityBoard);
		if (result == 1) {
			log.debug("잘 생성되었어요");
		} else {
			log.debug("이상이 있어요");
//			throw new Exception();
		}
		log.debug("결과={}", result);

		String jsonResult = gson.toJson("게시물 생성 완료");

		// 처리 결과와 함께 200 상태 코드로 응답
		return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
	}

	// 게시글 수정
	@Override
	@PostMapping("/{boardId}")
	public String updateCommunityBoard(
			@PathVariable("boardId") String boardId,
			@RequestBody CommunityBoard communityBoard) {
		log.debug("updateBoard실행");
		communityBoard.setBoardId(boardId);
		log.debug("communityBoard={}", communityBoard);
		int result = service.updateCommunityBoard(communityBoard);
		return null;
	}

	@Override
	@DeleteMapping("/{boardId}")
	public String deleteCommunityBoard(
			@PathVariable("boardId") String boardId,
			@RequestBody CommunityBoard communityBoard) { // 안쓰게 되면 @modelAttribute
		log.debug("delete 들어오는지 확인");
		communityBoard.setBoardId(boardId);
		log.debug("communityBoard={}", communityBoard);
		int result = service.deleteCommunityBoard(communityBoard);
		return null;
	}

}
