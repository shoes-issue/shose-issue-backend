package com.issue.shoes.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.issue.shoes.message.service.MessageServiceImpl;
import com.issue.shoes.message.vo.Message;

@Controller
@RequestMapping(value = "message")
public class MessageControllerImpl implements MessageController {

	@Autowired
	MessageServiceImpl messageService;
	
	@Override
	@PostMapping
	public String sendMessage(Message message) {
		System.out.println("123");
		int result = messageService.sendMessage(message);
		// status 
		if(result == 1 ) {
			// 메시지가 정상 적으로 보내졌습니다.
			// status.200
		}
		if(result != 1 ) {
			// 메시지 전송에 실패햐였습니다.
			// status 실패
		}
		// status 답장으로 보내주기
		
		return null;
	}

	@Override
	@DeleteMapping(value = "{messageId}")
	public String deleteMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping(value = "{messageId}", produces = "application/json; charset= UTF-8")
	public String openMessageDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping(value = "status/{messageId}")
	public String openMessageStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping(value = "kakao")
	public String alertKakao() {
		// TODO Auto-generated method stub
		return null;
	}

}
