package com.issue.shoes.communityLike.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.issue.shoes.communityLike.service.CommunityLikeServiceImpl;
import com.issue.shoes.communityLike.vo.CommunityLike;

@RestController
@RequestMapping("/board/like")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "Content-Type", allowCredentials = "true")
public class CommunityLikeControllerImpl implements communityLikeController {

	Logger log = LogManager.getLogger("case3");

	@Autowired
	CommunityLikeServiceImpl service;

	@Override
	@PutMapping
	public String createCommunityBoardLike(@RequestBody CommunityLike communityLike) {
		log.debug("createCommunityBoardLike={}", communityLike);
		int result = service.createCommunityLike(communityLike);

		return null;
	}

	// 좋아요 삭제
	@Override
	@DeleteMapping
	public ResponseEntity<String> deleteCommunityBoardLike(CommunityLike communityLike) {
		log.debug("deleteCommunityBoardLike Controller Value={}",communityLike);
		int result = service.deleteCommunityLike(communityLike);
		if (result == 1) {
	        return ResponseEntity.ok("Success");
	    } else {
	        return ResponseEntity.badRequest().body("Error: Failed to delete like.");
	    }
	}

	@Override
	@GetMapping
	public ResponseEntity<Object> searchCommunityBoardLike(@RequestParam String userId, @RequestParam String boardId) {
//	    log.debug("GET: communitylike 동작확인={}", communityLike);
	    log.debug("라이크 동작 확인");
	    CommunityLike communityLike = new CommunityLike();
	    communityLike.setBoardId(boardId);
	    communityLike.setUserId(userId);
	    CommunityLike result = service.searchCommunityLIke(communityLike);
	    if (result == null) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
