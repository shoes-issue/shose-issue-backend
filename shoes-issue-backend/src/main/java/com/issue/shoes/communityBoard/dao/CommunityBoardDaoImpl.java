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

	/**
	 * @apiNote 게시글 상세 조회
	 * @param boardId
	 * @return
	 * @throws Exception
	 */
	public CommunityBoard selectOne(String boardId) throws Exception {
		return session.selectOne("CommunityBoard.selectOne", boardId);
	}

	/**
	 * @apiNote 게시글 모두 조회
	 * @return List<CommunityBoard>
	 * @throws Exception
	 * @return int
	 */
	public List<CommunityBoard> selectList() throws Exception {
		return session.selectList("CommunityBoard.selectAll");
	}

	/**
	 * @apiNote 게시글 생성
	 * @param communityBoard
	 * @throws Exception
	 * @return int
	 */
	public int insert(CommunityBoard communityBoard) throws Exception {
		return session.insert("CommunityBoard.insert", communityBoard);
	}
	
	/**
	 * @apiNote 게시글 삭제 
	 * @param communityBoard
	 * @throws Exception
	 * @return int
	 */
	public int delete(CommunityBoard communityBoard) throws Exception {
		return session.delete("CommunityBoard.delete", communityBoard);
	}

	/**
	 * @apiNote 게시물 수정
	 * @param communityBoard
	 * @throws Exception
	 * @return int
	 */
	public int update(CommunityBoard communityBoard) throws Exception {
		return session.update("CommunityBoard.update",communityBoard);
	}

}
