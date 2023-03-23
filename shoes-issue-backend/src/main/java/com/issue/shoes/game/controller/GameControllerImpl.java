package com.issue.shoes.game.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.issue.shoes.game.service.GameService;
import com.issue.shoes.user.vo.User;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "Content-Type", allowCredentials = "true")
public class GameControllerImpl {
	
	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	GameService service;
	
	// 포인트 조회
	@GetMapping("/{userId}")
	public User searchPoint(@PathVariable String userId){
		log.debug("searchPoint={}", userId);
		User result = service.getUserPoint(userId);
		return result;
	}
	
	// 포인트 추가
	@PutMapping("/add/{userId}")
	public ResponseEntity<Object> addPoint(@PathVariable String userId){
		log.debug("searchPoint={}", userId);
		int result = service.updateAddPoint(userId);
	    if (result == 1) {
	        return ResponseEntity.ok("ok");
	    } else {
	        return ResponseEntity.badRequest().body("fail");
	    }
	}
	// 포인트 차감
	@PutMapping("/minus/{userId}")
	public ResponseEntity<Object> minusPoint(@PathVariable String userId){
		log.debug("searchPoint={}", userId);
		int result = service.updateMinusPoint(userId);
	    if (result == 1) {
	        return ResponseEntity.ok("ok");
	    } else {
	        return ResponseEntity.badRequest().body("fail");
	    }
	}
}
