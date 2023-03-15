package com.issue.shoes.tradeBoard.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertTradeBoard {
	private String tradeId;
	private String tradeTitle;
	private String tradeContents;
	private int tradeLike;
	private int tradePrice;
	private MultipartFile[] tradeImages;
}
