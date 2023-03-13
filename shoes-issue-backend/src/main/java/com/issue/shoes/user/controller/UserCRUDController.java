package com.issue.shoes.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.issue.shoes.user.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class UserCRUDController implements UserController {
	
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
	
	@PostMapping("/user")
	public String createUser() {

		// userService를 사용하여 생성 기능 구현
		return "createUser";
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

