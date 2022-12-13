package com.user.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;
import com.user.dao.AccountDAO;




@Service
public class AccountServiceImpl implements AccountService{
	
	@Inject
	private AccountDAO dao;
	
	@Override
	public ResultVO companyLogin(ResultVO rvo) throws Exception{
		return dao.companyLogin(rvo);
	}
	@Override
	public int userRecordLog(String user_email) throws Exception{
		return dao.userRecordLog(user_email);
	}
	@Override
	public int userPwdUpdate(ResultVO rvo) throws Exception{
		return dao.userPwdUpdate(rvo);
	}
}
