package com.issue.shoes.message.controller;

public interface MessageController {
	
	//쪽지 전송
	String sendMessage();
	//쪽지 삭제
	String deleteMessage();
	//쪽지 열람
	String openMessageDetail();
	//쪽지 열람여부 확인
	String openMessageStatus();
	//카톡으로 알림 보내기
	String alertKakaoTok();

}
