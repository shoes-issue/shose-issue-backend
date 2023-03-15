package com.issue.shoes.communityComment.controller;

import java.util.List;

import com.issue.shoes.communityComment.vo.CommunityComment;

public interface CommunityCommentController {
	
	/**
	 * @apiNote 댓글 등록
	 * @return int
	 */
	int createComment(CommunityComment communityComment);
	
	/**
	 * @apiNote 댓글 수정
	 * @return int
	 */
	int updateComment(CommunityComment communityComment);

	/**
	 * @apiNote 댓글 삭제
	 * @return int
	 */
	int deleteComment(CommunityComment communityComment);
	
	/**
	 * @apiNote 게시글의 모든 댓글 조회 
	 * @return CommunityComment
	 */
	List<CommunityComment> searchAllComment(String boardId);

}
