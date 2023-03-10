package com.issue.shoes.message.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "message")
public class MessageControllerImpl implements MessageController {

	@Override
	@PostMapping
	public String sendMessage() {
		// 蹂대궡�뒗 �궗�엺, �궡�슜, 蹂대궡�뒗 �떆媛�, 諛쏅뒗 �궗�엺
		
		return null;
	}

	@Override
	@DeleteMapping(value = "{messageId}")
	public String deleteMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping(value = "{messageId}", produces = "text/plain; charset= UTF-8")
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
	public String alertKakaoTok() {
		// TODO Auto-generated method stub
		return null;
	}

}
