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
	private String boardContent;
	private int boardLike;
	private String boardDate;
	private String boardUpdate;
	private String boardDeleted;
}
