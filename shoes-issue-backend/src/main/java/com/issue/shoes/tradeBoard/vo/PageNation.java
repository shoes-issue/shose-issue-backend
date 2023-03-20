package com.issue.shoes.tradeBoard.vo;

import lombok.Data;

@Data
public class PageNation {
	
	private int page; //현재 클릭한 페이지 --> 처음 띄울 때는 무조건 default 생성자를 통해 1로 초기화 되도록 함
	private int recordSize; //한 페이지에서 출력할 데이터의 갯수 --> 이 역시 처음 띄울 때는 무조건 default 생성자를 통해 10로 초기화 되도록 함
	private int startPage;
	
	//검색해서 페이지 이동할 경우
	private String keyword; 
	private String category;
	
	public PageNation() {
		this.page = 1;
		this.recordSize = 10;
	}
	
}
