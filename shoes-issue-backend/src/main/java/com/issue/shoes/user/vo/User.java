package com.issue.shoes.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	
	public User(String userName, String nickName, String userId, String userPw, String phone) {
		this.userName = userName;
		this.nickName = nickName;
		this.userId = userId;
		this.userPw = userPw;
		this.phone = phone;
	}
	
	public User(String userId) {
		this.userId = userId;
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
	
}