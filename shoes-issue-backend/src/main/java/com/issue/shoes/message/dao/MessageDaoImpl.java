package com.issue.shoes.message.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.message.vo.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	private SqlSession session;

	public String createMessage() {
		
		return null;
	}

	public int create(Message message) throws Exception {
		return session.insert("myRasdfho.sadfho",message);
	}

	// 쪽지 상세 조회
	public Message selectOne(String messageId)throws Exception {
		return session.selectOne("Message.selectOne", messageId);
	}

	// 보낸 쪽지 전체 목록 조회
	public List<Message> selectReceivedList(String messageReceiver)throws Exception {
		System.out.println(session);
		return session.selectList("message.selectAllReceived", messageReceiver);
	}
	
	
}
