package com.issue.shoes.tradeBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeBoard {
	
	private String tradeId;
	private String goodsCategory;
	private String tradeImage;
	private String tradeTitle;
	private String userId;
	private String tradeLike;
	private String tradeDate;
	private String tradeUpdateDate;

}
