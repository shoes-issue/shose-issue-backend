package com.issue.shoes.communityComment.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityComment {
	private String commentId;
	private String commentContents;
	private int commentLike;
	private String commentDate;
	private String commentUpdatedate;
	private String commentDeletedate;
	private String userId;
	private String boardId;
}
