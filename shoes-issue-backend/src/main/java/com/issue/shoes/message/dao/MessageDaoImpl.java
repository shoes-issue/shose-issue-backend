package com.issue.shoes.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.issue.shoes.message.vo.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	private SqlSession session;

	public String createMessage() {
		
		return null;
	}

	public int create(Message message) throws Exception {
		return session.insert("myRasdfho.sadfho",message);
	}
	
	
}
