package com.issue.shoes.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.issue.shoes.message.dao.MessageDaoImpl;
import com.issue.shoes.message.vo.Message;

@Service
public class MessageServiceImpl implements MessageService {

	// 로그 추가
//	Logger logger = 
	
	@Autowired
	MessageDaoImpl dao;
	
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

}
