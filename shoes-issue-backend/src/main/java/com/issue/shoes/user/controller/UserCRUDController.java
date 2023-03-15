package com.issue.shoes.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.issue.shoes.user.service.UserService;
import com.issue.shoes.user.vo.User;

@Controller
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8")
public class UserCRUDController implements UserController {

	private Logger log = LogManager.getLogger("case3");


	@Autowired
	private UserService userService;

	public UserCRUDController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user/{userId}")
	public String searchUser() {
		// userService를 사용하여 검색 기능 구현
		return "searchUser";
	}

	@Override
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			// 전달받은 정보를 이용하여 User 객체를 생성합니다.
			User newUser = new User(
					user.getUserName(),
					user.getNickName(),
					user.getUserId(),
					user.getUserPw(),
					user.getPhone()
					);
			// UserService를 이용하여 새로운 사용자를 생성합니다.
			log.debug(user.getUserId().getClass());
			log.debug(user.getUserName());
			log.debug("New User: {}", newUser);

			userService.createUser(newUser);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@PutMapping("/user/{userId}")
	public String updateUser() {
		// userService를 사용하여 수정 기능 구현
		return "updateUser";
	}

	@DeleteMapping("/user/{userId}")
	public String deleteUser() {
		// userService를 사용하여 삭제 기능 구현
		return "deleteUser";
	}
}

