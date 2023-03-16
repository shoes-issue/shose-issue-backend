package com.issue.shoes.communityComment.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.issue.shoes.communityComment.Service.CommunityCommentServiceImpl;
import com.issue.shoes.communityComment.vo.CommunityComment;

@RestController
@RequestMapping("board/comment")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "Content-Type", allowCredentials = "true")
public class CommunityCommentControllerImpl implements CommunityCommentController {

	// 로그 추가
	Logger log = LogManager.getLogger("case3");

	@Autowired
	private CommunityCommentServiceImpl service;

	// 댓글 생성
	@Override
	@PutMapping
	public int createComment(@RequestBody CommunityComment communityComment) {
		communityComment.setCommentId(UUID.randomUUID().toString());
		log.debug("createComment를 위한 확인={}", communityComment);
		int result = service.createComment(communityComment);
		return result;
	}

	// 댓글 수정
	@Override
	@PostMapping
	public int updateComment(@RequestBody CommunityComment communityComment) {
		log.debug("updateComment={}", communityComment);
		int result = service.updateComment(communityComment);
		return result;
	}

	// 댓글 삭제
	@Override
	@DeleteMapping
	public int deleteComment(@RequestBody CommunityComment communityComment) {
		log.debug("deleteComment 확인하기={}", communityComment);
		int result = service.deleteComment(communityComment);
		return result;
	}

	// 게시글의 모든 댓글 조회
	@Override
	@GetMapping("/{boardId}")
	public List<CommunityComment> searchAllComment(@PathVariable String boardId) {
		log.debug("searchAllComment로 요청이 들어왔어요");
		List<CommunityComment> result = service.searchAllComment(boardId);
		log.debug("searchAllComment 결과 조회={}", result);
		return result;
	}

}
