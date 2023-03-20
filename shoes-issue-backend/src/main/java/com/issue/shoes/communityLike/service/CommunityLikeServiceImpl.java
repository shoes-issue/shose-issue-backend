package com.issue.shoes.communityLike.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.issue.shoes.communityLike.dao.CommunityLikeDaoImpl;
import com.issue.shoes.communityLike.vo.CommunityLike;

@Service
public class CommunityLikeServiceImpl {

	// 로거 사용
	Logger log = LogManager.getLogger("case3");
	
	// dao 자동 주입
	@Autowired
	CommunityLikeDaoImpl dao;
	
	// transection code
	private final PlatformTransactionManager transactionManager;
	
	// 생성자 주입 방식
	public CommunityLikeServiceImpl (PlatformTransactionManager transactionManager) {
	    this.transactionManager = transactionManager;
	}
	
	/**
	 * @apiNote 좋아요 생성
	 * @param communityLike
	 * @return int
	 */
	public int createCommunityLike(CommunityLike communityLike) {
		// result 선언
		int result = 0;
		TransactionStatus txStatus =
	            transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			// 좋아요 실행 
			result = dao.insert(communityLike);

			// 좋아요 생성 메서드의 실행결과가 1이 아닐 때
			if(result!=1) {
				log.debug(new Exception("CommunityLikeServiceImpl 좋아요 생성 오류"));
				transactionManager.rollback(txStatus);
			}
			transactionManager.commit(txStatus);
			
			// 오류 발생시
		} catch (Exception e) {
			log.debug("CommunityLikeServiceImpl 좋아요 생성 오류={}",e);
			transactionManager.rollback(txStatus);
		}
		
		return result;
	}

	/**
	 * @apiNote 좋아요 삭제
	 * @param communityLike
	 * @return int
	 */
	public int deleteCommunityLike(CommunityLike communityLike) {
		// result 선언
		int result = 0;

		// 트렌젝션
		TransactionStatus txStatus =
	            transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			// 좋아요 삭제 실행
			result = dao.delete(communityLike);
		
			// 좋아요 삭제 메서드 실행 결과가 1이 아닐 때
			if(result!=1) {
				log.debug(new Exception("CommunityLikeServiceImpl 좋아요 삭제 오류"));
				transactionManager.rollback(txStatus);
			}
			// 오류 발생시
		} catch (Exception e) {
			log.debug("CommunityLikeServiceImpl 좋아요 삭제 오류={}",e);
			transactionManager.rollback(txStatus);
		}
		transactionManager.commit(txStatus);
		return result;
	}

	public CommunityLike searchCommunityLIke(CommunityLike communityLike) {
		CommunityLike result = null;

		try {
			result = dao.selectOne(communityLike);
			if(result == null) {
				log.debug(new Exception("searchCommunityLike 오류 발생"));
				
			}
		} catch (Exception e) {
			log.debug("searchCommunityLikeService 오류 발생={}",e);
		}
		return result;
	}

}
