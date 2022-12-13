package com.user.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tring.domain.AdressMemberVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;
import com.user.dao.UserDAO;



@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDAO dao;
	
	@Override
	public int userInsert(ResultVO rvo) throws Exception{
		return dao.userInsert(rvo);
	}
	@Override
	public MemberVO userInfo(String user_uid) throws Exception{
		return dao.userInfo(user_uid);
	}
	@Override
	public List<String> userAccess_deptUid(String user_uid) throws Exception{
		return dao.userAccess_deptUid(user_uid);
	}
	@Override
	public int userInfoUpdate(ResultVO rvo) throws Exception{
		return dao.userInfoUpdate(rvo);
	}
	@Override
	public int userIconUpdate(ResultVO rvo) throws Exception{
		return dao.userIconUpdate(rvo);
	}
	@Override
	public int userInfoDelete(String user_uid) throws Exception{
		return dao.userInfoDelete(user_uid);
	}
	
	@Override
	public List<MemberVO> userSearch(ResultVO rvo) throws Exception{
		return dao.userSearch(rvo);
	}
	@Override
	public List<AdressMemberVO> adressUserSearch(ResultVO rvo) throws Exception{
		return dao.adressUserSearch(rvo);
	}
	
	@Override
	public List<AdressMemberVO> adressUserList(String user_uid) throws Exception{
		return dao.adressUserList(user_uid);
	}
	@Override
	public int adressUserInsert(ResultVO rvo) throws Exception{
		return dao.adressUserInsert(rvo);
	}
	@Override
	public int adressUserUpdate(ResultVO rvo) throws Exception{
		return dao.adressUserUpdate(rvo);
	}
	@Override
	public int adressUserDelete(ResultVO rvo) throws Exception{
		return dao.adressUserDelete(rvo);
	}
	
	@Override
	public int userDeptUpdate(String department_code, String user_uid) throws Exception{
		return dao.userDeptUpdate(department_code, user_uid);
	}
}
