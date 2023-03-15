package com.issue.shoes.communityComment.Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.issue.shoes.communityComment.dao.CommunityCommentDaoImpl;
import com.issue.shoes.communityComment.vo.CommunityComment;

@Service
public class CommunityCommentServiceImpl implements CommunityCommentService {

	Logger log = LogManager.getLogger("case3");

	@Autowired
	CommunityCommentDaoImpl dao;

	private final PlatformTransactionManager transactionManager;

	// 생성자 주입 방식
	public CommunityCommentServiceImpl(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	// 게시글의 모든 댓글 조회
	public List<CommunityComment> searchAllComment(String boardId) {
		List<CommunityComment> result = null;

		try {
			result = dao.selectList(boardId);
			if (result == null) {
				throw new Exception("searchAllCommentService 오류!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("searchAllCommentService 오류 ={}", e);
		}

		return result;
	}

	// 댓글 생성
	public int createComment(CommunityComment communityComment) {
		int result = 0;

		try {
			result = dao.insert(communityComment);
			if (result != 1) {
				throw new Exception("createComment 오류!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("createCommentService 오류 ={}", e);
		}
		return result;
	}

	public int updateComment(CommunityComment communityComment) {
		int result = 0;

		try {
			result = dao.update(communityComment);
			if (result != 1) {
				throw new Exception("updateComment 오류!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("updateCommentService 오류={}", e);
		}
		return result;
	}

	// 댓글 삭제
	public int deleteComment(CommunityComment communityComment) {
		int result = 0;

		try {
			result = dao.delete(communityComment);
			if (result != 1) {
				throw new Exception("deleteComment 오류!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("deleteCommenttService 오류={}", e);
		}
		return result;
	}
}
