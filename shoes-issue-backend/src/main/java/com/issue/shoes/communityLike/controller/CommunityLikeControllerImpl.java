package com.issue.shoes.communityLike.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@Override
	@DeleteMapping
	public String deleteCommunityBoardLike(@RequestBody CommunityLike communityLike) {
		int result = service.deleteCommunityLike(communityLike);
		return null;
	}

}
