package com.issue.shoes.message.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.issue.shoes.message.dao.MessageDaoImpl;
import com.issue.shoes.message.vo.Message;

@Service
public class MessageServiceImpl implements MessageService {

	// 로그 추가
	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	MessageDaoImpl dao;
	
	private final PlatformTransactionManager transactionManager;
	
	public MessageServiceImpl (PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public int sendMessage(Message message) {
		int result = 0;
		try {
			result = dao.create(message);
			if (result == 1) {
				// commit
			} else {
				// rollback
			}
		} catch (Exception e) {
			// 로그 실행
		}
		return result;
	}

	public Message findOneReceivedMessage(String messageId) {
		// TODO Auto-generated method stub
		return null;
	}

}
