package com.issue.shoes._transactionSample;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public void testInsert(String sampleWord) throws Exception {
		
		int result = 0;
		
		result = session.insert("user.test", sampleWord);
		
		if (result != 1) {
			throw new Exception();
		}
	}

}
