package com.issue.shoes.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.issue.shoes.tradeBoard.vo.PageNation;
import com.issue.shoes.user.service.UserService;
import com.issue.shoes.user.vo.User;

@RestController
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8")
public class UserCRUDController implements UserController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private Gson gson;

	@Autowired
	private UserService userService;

	public UserCRUDController(UserService userService) {
		this.userService = userService;
	}

	@Override
	@GetMapping(value="/user/tradeBoardWrite")
	public String tradeBoardWrite(PageNation page) {
		
		List<Object> list = userService.tradeBoardWrite(page);

		String tradeBoardMap = gson.toJson(list);		
		
		return tradeBoardMap;
	}
	
	@Override
	@GetMapping(value="/user/tradeBoardLike")
	public String tradeBoardLike(PageNation page) {
		
		List<Object> list = userService.tradeBoardLike(page);

		String tradeBoardMap = gson.toJson(list);		
		
		return tradeBoardMap;
	}
	
	@Override
	@PostMapping("/user/usercheck")
	public ResponseEntity<Map<String, Boolean>> searchUser(@RequestBody Map<String, String> requestBody) {
		String userId = requestBody.get("userId");
		boolean isDuplicate = userService.idDuplicate(userId);

		Map<String, Boolean> response = new HashMap<>();
		response.put("isDuplicate", isDuplicate);
		return ResponseEntity.ok(response);
	}


	@Override
	@PostMapping("/user/{userId}")
	public ResponseEntity<?> selectUserById(@PathVariable String userId) throws Exception{
		User user = userService.selectUserById(userId);

		Map<String, Object> map = new HashMap<>();
		if (user != null) {
			map.put("userId", user.getUserId());
			map.put("userName", user.getUserName());
			map.put("nickName", user.getNickName());
			map.put("signupDate", user.getSignupDate());
			map.put("updateDate", user.getUpdateDate());
			map.put("deleteDate", user.getDeleteDate());
			map.put("adminStatus", user.getAdminStatus());
			map.put("point", user.getPoint());
			map.put("profileImage", user.getProfileImage());
			map.put("phone", user.getPhone());
			map.put("reportCount", user.getReportCount());
			map.put("reportDate", user.getReportDate());

			map.put("success", true);
		} else {
			map.put("success", false);
		}

		String json = gson.toJson(map);
		return ResponseEntity.ok()
				.body(json);
	}


	@Override
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {

			// PasswordEncoder를 사용하여 userPw를 암호화합니다.
			String encodedPassword = passwordEncoder.encode(user.getUserPw());

			// 전달받은 정보를 이용하여 User 객체를 생성합니다.
			User newUser = new User(
					user.getUserName(),
					user.getNickName(),
					user.getUserId(),
					encodedPassword,
					user.getPhone(),
					user.getPreference1(),
					user.getPreference2(),
					user.getPreference3()
					);
			// UserService를 이용하여 새로운 사용자를 생성합니다.
			log.debug(user.getUserId().getClass());
			log.debug(user.getUserName());
			log.debug("New User: {}", newUser);

			userService.createUser(newUser);
			userService.createUseroauth(newUser);
			userService.createPreference(newUser);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@Override
	@PostMapping("/userProfile/{userId}")
	public ResponseEntity<?> updateUser(MultipartFile imageUrl, User user) {
		try {
			UUID imgUuid = UUID.randomUUID();

			String profileImage = imageUrl.getOriginalFilename();
			profileImage = profileImage.substring(profileImage.lastIndexOf("\\") + 1);
			profileImage = imgUuid.toString() + "_" + profileImage;

			String encodedPassword = passwordEncoder.encode(user.getUserPw());
			// 전달받은 정보를 이용하여 User 객체를 생성합니다.

			log.debug("sdf");
			User newUser = new User(
					user.getUserName(),
					user.getNickName(),
					user.getUserId(),
					encodedPassword,
					user.getPhone(),
					profileImage,
					imageUrl
					);
			// UserService를 이용하여 새로운 사용자를 생성합니다.
			log.debug("New User: {}", newUser);

			userService.updateUser(newUser);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		try {
			userService.deleteUser(userId);
			userService.deleteUseroauth(userId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	@PostMapping("/user/tradeBoardAll")
	public ResponseEntity<?> searchAllTradeBoard(@RequestBody String userId) throws Exception {

		try {	    
			userService.selectcommunityAll(userId);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@Override
	@PostMapping("/user/communityAll")
	public ResponseEntity<?> searchAllCommunityBoard(@RequestBody String userId) throws Exception {

		try {	    
			userService.selectcommunityAll(userId);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
