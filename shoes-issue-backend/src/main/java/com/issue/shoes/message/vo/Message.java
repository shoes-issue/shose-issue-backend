package com.issue.shoes.message.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Message {

	private String messageId;
	private String messageTitle;
	private String messageContents;
	private String messageSenddate;
	private Boolean messageOpenstatus;
	private String messageSender;
	private String messageReceiver;
}
