package com.issue.shoes.communityBoard.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.issue.shoes.communityBoard.vo.CommunityBoard;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {

	Logger log = LogManager.getLogger("case3");
	
//	@Autowired
	
	@Bean
	public CommunityBoard findOneCommunityBoard(CommunityBoard communityBoard) {
		
		return null;
	}

	
	
}
