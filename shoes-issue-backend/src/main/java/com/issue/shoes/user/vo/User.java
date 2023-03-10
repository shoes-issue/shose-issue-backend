package com.issue.shoes.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
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