package com.issue.shoes.user.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	
	public User(String userName, String nickName, String userId, String userPw, String phone, String profileImage, MultipartFile imageUrl) {
		this.userName = userName;
		this.nickName = nickName;
		this.userId = userId;
		this.userPw = userPw;
		this.phone = phone;
		this.profileImage = profileImage;
		this.imageUrl = imageUrl;
		
	}
	
	public User(String userId) {
		this.userId = userId;
	}

	public User(String userName, String nickName, String userId, String userPw, String phone, 
			String preference1, String preference2, String preference3) {
		this.userName = userName;
		this.nickName = nickName;
		this.userId = userId;
		this.userPw = userPw;
		this.phone = phone;
		this.preference1 = preference1;
		this.preference2 = preference2;
		this.preference3 = preference3;
	}

	private String userId;
	private String userPw;
	private String userName;
	private String nickName;
	private String signupDate;
	private String updateDate;
	private String deleteDate;
	private Boolean adminStatus;
	private int point;
	private String profileImage;
	private String phone;
	private int reportCount;
	private String reportDate;
	private MultipartFile imageUrl;
	private String preference1;
	private String preference2;
	private String preference3;
	
}