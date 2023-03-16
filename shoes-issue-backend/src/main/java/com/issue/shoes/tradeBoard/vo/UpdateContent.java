package com.issue.shoes.tradeBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContent {
	
	private String tradeId;
	private String goodsCategory;
	private int tradePrice;
	private String tradeTitle;
	private String tradeImage;
	private String tradeContents;
	//업데이트 날짜
	private String updateDate;
}
