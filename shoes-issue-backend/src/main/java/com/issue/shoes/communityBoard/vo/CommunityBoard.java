package com.issue.shoes.communityBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityBoard {
	private String boardId;
	private String boardTitle;
	private String boardContents;
	private int boardLike;
	private String boardDate;
	private String boardUpdatedate;
	private String boardDeletedate;
	private String userId;
}
