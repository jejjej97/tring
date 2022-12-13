package com.data.dao;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class MemoDAOImpl implements MemoDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.Memo.mappers";

}
