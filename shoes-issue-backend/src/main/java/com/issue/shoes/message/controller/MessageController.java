package com.issue.shoes.message.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.issue.shoes.message.vo.Message;

public interface MessageController {

	// 쪽지 전송
	ResponseEntity<String> sendMessage(Message message);

	// 쪽지 삭제
	ResponseEntity<String> deleteMessage(String messageId) throws Exception;

	// 받은 쪽지함
	List<Message> allReceivedMessage(String messageReceiver);

	// 보낸 쪽지함
	List<Message> allSendMessage(String messageReceiver);

	// 받은 쪽지 열람
	Message openReceiveMessageDetail(String messageId);

	// 보낸 쪽지 열람
	Message openSendMessageDetail(String messageId);

	// 쪽지 열람여부 확인
	String openMessageStatus();

	// 카톡으로 알림 보내기
	String alertKakao();

}
