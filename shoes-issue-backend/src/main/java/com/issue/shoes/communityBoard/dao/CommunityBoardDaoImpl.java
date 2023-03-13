package com.issue.shoes.communityBoard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.communityBoard.vo.CommunityBoard;

@Repository
public class CommunityBoardDaoImpl implements CommunityBoardDao {

	@Autowired
	private SqlSession session;

	public CommunityBoard selectOne(String boardId) throws Exception {
		return session.selectOne("CommunityBoard.selectOne", boardId);
	}

	public List<CommunityBoard> selectList() throws Exception {
		return session.selectList("CommunityBoard.selectAll");
	}

	public int insert(CommunityBoard communityBoard) throws Exception {
		return session.insert("CommunityBoard.insert", communityBoard);
	}

}
