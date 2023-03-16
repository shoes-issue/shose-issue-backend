package com.issue.shoes.tradeBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeBoardDetail {
	
	private String nickName;
	private int point;
	
	private String tradeId;
	private String tradeImage;
	private String goodsCategory;
	private String tradeTitle;
	private String tradeContents;
	private String tradeDate;
	private int tradePrice;
	private int tradeLike;
	private String tradeStatus;
	
}
