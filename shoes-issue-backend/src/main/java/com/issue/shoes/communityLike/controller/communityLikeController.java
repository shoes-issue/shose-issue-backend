package com.issue.shoes.communityLike.controller;

import org.springframework.http.ResponseEntity;
import com.issue.shoes.communityLike.vo.CommunityLike;

public interface communityLikeController {
	
	//좋아요 추가
	String createCommunityBoardLike(CommunityLike communityLike);
	//좋아요 삭제
	ResponseEntity<String> deleteCommunityBoardLike(CommunityLike communityLike);
	
	ResponseEntity<Object> searchCommunityBoardLike(String userId,String boardId);

}
