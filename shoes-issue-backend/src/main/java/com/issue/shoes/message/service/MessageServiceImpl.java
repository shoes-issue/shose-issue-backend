package com.issue.shoes.message.service;

import java.util.List;

import org.apache.ibatis.transaction.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.issue.shoes.message.dao.MessageDaoImpl;
import com.issue.shoes.message.vo.Message;

@Service
public class MessageServiceImpl implements MessageService {

	// 로그 추가
	Logger log = LogManager.getLogger("case3");

	@Autowired
	MessageDaoImpl dao;

	private final PlatformTransactionManager transactionManager;

	// 쪽지 보내기
	public MessageServiceImpl(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public int sendMessage(Message message) {
		int result = 0;
			TransactionStatus txStatus =
					transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			result = dao.create(message);
			if (result == 1) {
				transactionManager.commit(txStatus);
				log.debug("service => 잘실행되었어요");
			} else {
				throw new Exception("error");
			}
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 뭔가 이상해요 사유={}", e);
			throw new RuntimeException("쪽지 생성 중 오류가 발생하였습니다.", e);
		}
		return result;
	}

	// 보낸 쪽지 전체 목록
	public List<Message> findAllReceivedMessage(String messageReceiver) {
		List<Message> result = null;

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			result = dao.selectReceivedList(messageReceiver);
			transactionManager.commit(txStatus);
			log.debug("service => 잘실행되었어요");
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 이상해요 사유={}", e);
			throw new RuntimeException("보낸 쪽지 목록 조회 중 오류가 발생했습니다", e);
		}

		return result;
	}

	// 받은 쪽지 전체 목록
	public List<Message> findAllSendMessage(String messageSender) {
		List<Message> result = null;

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			result = dao.selectSendList(messageSender);
			transactionManager.commit(txStatus);
			log.debug("service => 잘실행되었어요");
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 이상해요 사유={}", e);
			throw new RuntimeException("받은 쪽지 목록 조회 중 오류가 발생했습니다", e);
		}

		return result;
	}

	// 받은 쪽지 상세 정보 조회
	public Message findOneReceiveMessage(String messageId) {
		Message result = null;

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		log.debug("service 소환");
		try {
			result = dao.selectReceiveOne(messageId);
			transactionManager.commit(txStatus);
			log.debug("service => 잘실행되었어요");
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			log.debug("service => 뭔가 이상해요 사유={}", e);
		}

		return result;
	}

	
	// 받은 쪽지 상세 정보 조회
		public Message findOneSendMessage(String messageId) {
			Message result = null;

			TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

			log.debug("service 소환");
			try {
				result = dao.selectSendOne(messageId);
				transactionManager.commit(txStatus);
				log.debug("service => 잘실행되었어요");
			} catch (Exception e) {
				transactionManager.rollback(txStatus);
				log.debug("service => 뭔가 이상해요 사유={}", e);
			}

			return result;
		}

}
