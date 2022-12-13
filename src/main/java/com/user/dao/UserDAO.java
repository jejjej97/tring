package com.user.dao;

import java.util.List;

import com.tring.domain.AdressMemberVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;

public interface UserDAO {

	public int userInsert(ResultVO rvo) throws Exception;
	public MemberVO userInfo(String user_uid) throws Exception;
	public List<String> userAccess_deptUid(String user_uid) throws Exception;
	public int userInfoUpdate(ResultVO rvo) throws Exception;
	public int userIconUpdate(ResultVO rvo) throws Exception;
	public int userInfoDelete(String user_uid) throws Exception;
	
	public List<MemberVO> userSearch(ResultVO rvo) throws Exception;
	public List<AdressMemberVO> adressUserSearch(ResultVO rvo) throws Exception;
	
	public List<AdressMemberVO> adressUserList(String user_uid) throws Exception;
	public int adressUserInsert(ResultVO rvo) throws Exception;
	public int adressUserUpdate(ResultVO rvo) throws Exception;
	public int adressUserDelete(ResultVO rvo) throws Exception;
	
	public int userDeptUpdate(String department_code, String user_uid) throws Exception;
}
