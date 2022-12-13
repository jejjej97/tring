package com.user.dao;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;


@Repository
public class AccountDAOImpl implements AccountDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.user.mappers";

	@Override
	public ResultVO companyLogin(ResultVO rvo) throws Exception{
		return sql.selectOne(namespace + ".companyLogin", rvo);
	}
	@Override
	public int userRecordLog(String user_email) throws Exception{
		return sql.update(namespace + ".userRecordLog", user_email);
	}
	@Override
	public int userPwdUpdate(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".userPwdUpdate", rvo);
	}
}
