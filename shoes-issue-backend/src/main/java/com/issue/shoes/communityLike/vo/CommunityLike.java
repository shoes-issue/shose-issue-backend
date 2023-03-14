package com.issue.shoes.communityLike.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityLike {
	private int boardLike;
	private String boardId;
	private String userId;
}
