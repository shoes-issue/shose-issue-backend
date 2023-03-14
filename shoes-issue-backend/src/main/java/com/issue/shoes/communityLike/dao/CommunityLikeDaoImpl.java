package com.issue.shoes.communityLike.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.issue.shoes.communityLike.vo.CommunityLike;

@Repository
public class CommunityLikeDaoImpl {

	@Autowired
	private SqlSession session;

	/**
	 * @apiNote 좋아요 생성
	 * @param communityLike
	 * @return int
	 * @throws Exception
	 */
	public int insert(CommunityLike communityLike) throws Exception {
		return session.insert("communityLike.insert", communityLike);
	}

	/**
	 * @apiNote 좋아요 삭제
	 * @param communityLike
	 * @return int
	 * @throws Exception
	 */
	public int delete(CommunityLike communityLike) throws Exception {
		return session.delete("communityLike.delete", communityLike);
	}
}
