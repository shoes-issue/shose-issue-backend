package com.issue.shoes.message.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.issue.shoes.message.service.MessageServiceImpl;
import com.issue.shoes.message.vo.Message;

@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "Content-Type", allowCredentials = "true")
@RestController
@RequestMapping(value = "message")
public class MessageControllerImpl implements MessageController {

	Logger log = LogManager.getLogger("case3");

	@Autowired
	private Gson gson;

	@Autowired
	private MessageServiceImpl service;

	// 쪽지 보내기(생성)
	@Override
	@PostMapping(produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> sendMessage(@RequestBody Message message) {
		log.debug("sendMessage 실행={}", message);

		UUID uuid = UUID.randomUUID();
		message.setMessageId(uuid.toString());

		int result = service.sendMessage(message);
		// status
		if (result == 1) {
			log.debug("쪽지 생성 성공");
		} else {
			log.debug("이상이 있어요");
		}
		log.debug("결과={}", result);

		String jsonResult = gson.toJson("쪽지 생성 완료");

		return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
	}

	// 쪽지 삭제
	@Override
	@DeleteMapping(value = "{messageId}")
	public ResponseEntity<String> deleteMessage(@PathVariable Message message) {
		log.debug("deleteMessage 실행={}", message);

		String jsonResult = gson.toJson("쪽지 삭제 완료");

		return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
	}

	// 받은 쪽지함`
	// messageReciever(받은 사람) == userId
	// URL : message/inbox
	// GET방식에서 param에 보내주는 방법하고, URL에 붙여주는 방법이 있늑ㄴ것 같아요
	// 이 둘중에 하나를 사용하시면 될 것 같아요
	@Override
	@GetMapping(value = "inbox/{messageReceiver}", produces = "application/json; charset= UTF-8") // produces 응답하는 데이터
																									// 형식? 일꺼에요
	@ResponseBody
	public List<Message> allReceivedMessage(@PathVariable("messageReceiver") String messageReceiver) {
		log.debug("messageReceiver 조회={}", messageReceiver);
		// message/inbox?messageReceiver=asdfjkl
		List<Message> result = service.findAllReceivedMessage(messageReceiver);

		return result;
	}

	// 보낸 쪽지함
	@Override
	@GetMapping(value = "sent/{messageSender}", produces = "application/json; charset= UTF-8")
	public List<Message> allSendMessage(@PathVariable("messageSender") String messageSender) {
		log.debug("messageSender 조회={}", messageSender);

		List<Message> result = service.findAllSendMessage(messageSender);

		return result;
	}

	// 받은 쪽지 상세 정보 조회
	// 메시지 한개의 세부정보를 확인하는거죠
	// 회원 아이디랑, messageID가 있으면 확인할 수 있어요
	@Override
	@GetMapping(value = "{messageId}", produces = "application/json; charset= UTF-8")
	public Message openReceiveMessageDetail(@PathVariable("messageId") String messageId) {
		// http://localhost:80/message/{messageId} 의 주소로 GET요청을 보내면 여기로 도착할 꺼에요
		System.out.println(messageId + "입니다");
		// 1. 일단 여기로 요청이 들어와야 해요
		log.debug("messageId 조회={}", messageId);

		Message result = service.findOneReceiveMessage(messageId);
		// 유저의 아이디하고 messageid가 같으면 이라는 조건을 DB에서 사용해서 메시지의 내용으 가져와요
		// Message result = service.searchMessageDetaio(message);
		// result == null 하면은 이 값을 처리해주고
		log.debug("예시 결과={}", result);

		/// 하나의 결과가 나오던가 결과가 null일 꺼에요
		// service에서 나온 결과를 어떻게 처리 해야해요

		// 그 결과를 return 해주면 됩니다.
		// vo를 내보내주면 spring이 알아서 json으로 바꿔서 받을 수 있게 보내줘요
		return result;
	}

	// 보낸 쪽지 상세 정보 조회
	// 메시지 한개의 세부정보를 확인하는거죠
	// 회원 아이디랑, messageID가 있으면 확인할 수 있어요
	@Override
	@GetMapping(value = "{messageId}/send", produces = "application/json; charset= UTF-8")
	public Message openSendMessageDetail(@PathVariable("messageId") String messageId) {
		// http://localhost:80/message/{messageId} 의 주소로 GET요청을 보내면 여기로 도착할 꺼에요
		System.out.println(messageId + "입니다");
		// 1. 일단 여기로 요청이 들어와야 해요
		log.debug("messageId 조회={}", messageId);

		Message result = service.findOneSendMessage(messageId);
		// 유저의 아이디하고 messageid가 같으면 이라는 조건을 DB에서 사용해서 메시지의 내용으 가져와요
		// Message result = service.searchMessageDetaio(message);
		// result == null 하면은 이 값을 처리해주고
		log.debug("예시 결과={}", result);

		/// 하나의 결과가 나오던가 결과가 null일 꺼에요
		// service에서 나온 결과를 어떻게 처리 해야해요

		// 그 결과를 return 해주면 됩니다.
		// vo를 내보내주면 spring이 알아서 json으로 바꿔서 받을 수 있게 보내줘요
		return result;
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
