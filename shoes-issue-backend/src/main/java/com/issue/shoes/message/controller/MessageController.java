package com.issue.shoes.message.controller;

import java.util.List;

import com.issue.shoes.message.vo.Message;

public interface MessageController {
	
	//쪽지 전송
	String sendMessage(Message message);
	//쪽지 삭제
	String deleteMessage();
	//받은 쪽지함
	List<Message> allReceivedMessage(String messageReceiver);
	//보낸 쪽지함
	String allSendMessage();
	//쪽지 열람
	Message openMessageDetail(String messageId);
	//쪽지 열람여부 확인
	String openMessageStatus();
	//카톡으로 알림 보내기
	String alertKakao();

}
