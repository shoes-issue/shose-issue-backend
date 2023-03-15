package com.issue.shoes.communityComment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.communityComment.vo.CommunityComment;

@Repository
public class CommunityCommentDaoImpl {

	@Autowired
	private SqlSession session;

	/**
	 * @param boardId 
	 * @apiNote 게시글의 모든 댓글 조회
	 * @return List
	 * @throws Exception
	 */
	public List<CommunityComment> selectList(String boardId) throws Exception {
		return session.selectList("communityComment.selectAll", boardId);
	}

	/**
	 * @apiNote 댓글 생성
	 * @param communityComment
	 * @return int
	 */
	public int insert(CommunityComment communityComment) throws Exception {
		return session.insert("communityComment.insert", communityComment);
	}

	/**
	 * @apiNote 댓글 수정
	 * @param communityComment
	 * @return int
	 */
	public int update(CommunityComment communityComment) throws Exception {
		return session.update("communityComment.update", communityComment);
	}

	/**
	 * @apiNote 댓글 삭제
	 * @param communityComment
	 * @return int
	 */
	public int delete(CommunityComment communityComment) throws Exception {
		return session.delete("communityComment.delete", communityComment);
	}
	
}
