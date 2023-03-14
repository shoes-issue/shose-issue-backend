package com.issue.shoes.communityBoard.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.issue.shoes.communityBoard.dao.CommunityBoardDaoImpl;
import com.issue.shoes.communityBoard.vo.CommunityBoard;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {

	Logger log = LogManager.getLogger("case3");

	@Autowired
	CommunityBoardDaoImpl dao;
	
	private final PlatformTransactionManager transactionManager;
	
	// 생성자 주입 방식
	public CommunityBoardServiceImpl (PlatformTransactionManager transactionManager) {
	    this.transactionManager = transactionManager;
	}
	
	/**
	 * 
	 * @param boardId
	 * @return CommunityBoard
	 */
	public CommunityBoard findOneCommunityBoard(String boardId) {
		CommunityBoard result = null;
		
		TransactionStatus txStatus =
	            transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		log.debug("service 소환");
		try {
			result = dao.selectOne(boardId);
			transactionManager.commit(txStatus);
			log.debug("service => 잘 실행 되었어요");
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 뭔가 이상해요 사유={}", e);
		}
		
		return result;
	}

	/**
	 * 
	 * @return List
	 */
	public List<CommunityBoard> findAllCommunityBoard() {
		List<CommunityBoard> result = null;
		
		TransactionStatus txStatus =
	            transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			result = dao.selectList();
			transactionManager.commit(txStatus);
			log.debug("service => 잘 실행 되었어요");
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 뭔가 이상해요 사유={}", e);
			throw new RuntimeException("게시판 목록 조회 중 오류가 발생하였습니다.", e);
		}
		return result;
	}

	/**
	 * 
	 * @param communityBoard
	 * @return int
	 */
	public int createCommunityBoard(CommunityBoard communityBoard) {
	int result = 0;
		TransactionStatus txStatus =
	            transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			result = dao.insert(communityBoard);
			if(result == 1) {
				transactionManager.commit(txStatus);
				log.debug("service => 잘 실행 되었어요");	
			} else {
				throw new Exception("error");
			}
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 뭔가 이상해요 사유={}", e);
			throw new RuntimeException("게시판 목록 조회 중 오류가 발생하였습니다.", e);
		}
		return result;
	}

	/**
	 * 
	 * @param communityBoard
	 * @return int
	 */
	public int deleteCommunityBoard(CommunityBoard communityBoard) {
		int result = 0;
		TransactionStatus txStatus =
	            transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			result = dao.delete(communityBoard);
			log.debug("result={}",result);
			if(result == 1) {
				transactionManager.commit(txStatus);
				log.debug("service => 잘 실행 되었어요");	
			} else {
				throw new Exception("error");
			}
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 뭔가 이상해요 사유={}", e);
			throw new RuntimeException("게시판 목록 조회 중 오류가 발생하였습니다.", e);
		}
		return result;
	}

	/**
	 * 
	 * @param communityBoard
	 * @return int
	 */
	public int updateCommunityBoard(CommunityBoard communityBoard) {
		int result = 0;
		TransactionStatus txStatus =
	            transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			result = dao.update(communityBoard);
			log.debug("result={}",result);
			if(result == 1) {
				transactionManager.commit(txStatus);
				log.debug("service => 잘 실행 되었어요");	
			} else {
				throw new Exception("error");
			}
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 뭔가 이상해요 사유={}", e);
			throw new RuntimeException("게시판 목록 조회 중 오류가 발생하였습니다.", e);
		}
		return result;
	}

	
	
}
