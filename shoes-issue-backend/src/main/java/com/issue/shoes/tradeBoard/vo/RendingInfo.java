package com.issue.shoes.tradeBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendingInfo {

	private String tradeId;
	private String tradeImage;
	private String tradeTitle;
	private String tradeContents;
	private String userId;
	private String nickName;
}
