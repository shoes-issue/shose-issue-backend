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
		return session.insert("message.insert", message);
	}

	// 받은 쪽지 상세 조회
	public Message selectReceiveOne(String messageId) throws Exception {
		return session.selectOne("message.selectReceiveOne", messageId);
	}

	// 보낸 쪽지 상세 조회
	public Message selectSendOne(String messageId) throws Exception {
		return session.selectOne("message.selectSendOne", messageId);
	}

	// 보낸 쪽지 전체 목록 조회
	public List<Message> selectReceivedList(String messageReceiver) throws Exception {
		System.out.println(session);
		return session.selectList("message.selectAllReceived", messageReceiver);
	}

	// 받은 쪽지 전체 목록 조회
	public List<Message> selectSendList(String messageSender) throws Exception {
		System.out.println(session);
		return session.selectList("message.selectAllSend", messageSender);
	}

}
