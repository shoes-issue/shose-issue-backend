package com.issue.shoes.tradeBoard.vo;

import java.util.ArrayList;

public class PageNation {
	
	private int page; //현재 클릭한 페이지 --> 처음 띄울 때는 무조건 default 생성자를 통해 1로 초기화 되도록 함
	private int recordSize; //한 페이지에서 출력할 데이터의 갯수 --> 이 역시 처음 띄울 때는 무조건 default 생성자를 통해 10로 초기화 되도록 함
	private int startPage;
	ArrayList<Integer> buttonCount;
	private int remainBoardCount;
	
	private boolean next;
	private boolean prev;
	
	//검색해서 페이지 이동할 경우
	private String keyword; 
	private String category;
	
	
	public PageNation() {
		this.page = 1;
		this.recordSize = 10;
		this.next = true;
		this.prev = true;
	}
	
	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int page) {
		this.startPage = (page -1) * recordSize;
	}

	
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setButtonCount(ArrayList<Integer> buttonCount) {
		this.buttonCount = buttonCount;
	}

	public int getRemainBoardCount() {
		return remainBoardCount;
	}

	public void setRemainBoardCount(int remainBoardCount) {
		this.remainBoardCount = remainBoardCount;
	}
	
	
}
