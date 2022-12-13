package com.user.dao;

import com.tring.domain.ResultVO;

public interface AccountDAO {

	public ResultVO companyLogin(ResultVO rvo) throws Exception;
	public int userRecordLog(String user_email) throws Exception;
	public int userPwdUpdate(ResultVO rvo) throws Exception;
}
