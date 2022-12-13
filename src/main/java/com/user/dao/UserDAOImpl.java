package com.user.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tring.domain.AdressMemberVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.user.mappers";

	@Override
	public int userInsert(ResultVO rvo) throws Exception{
		return sql.insert(namespace + ".userInsert", rvo);
	}
	@Override
	public MemberVO userInfo(String user_uid) throws Exception{
		return sql.selectOne(namespace + ".userInfo", user_uid);
	}
	@Override
	public List<String> userAccess_deptUid(String user_uid) throws Exception{
		return sql.selectList(namespace + ".userAccess_deptUid", user_uid);
	}
	@Override
	public int userInfoUpdate(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".userInfoUpdate", rvo);
	}
	@Override
	public int userIconUpdate(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".userIconUpdate", rvo);
	}
	@Override
	public int userInfoDelete(String user_uid) throws Exception{
		return sql.delete(namespace + ".userInfoDelete", user_uid);
	}
	
	@Override
	public List<MemberVO> userSearch(ResultVO rvo) throws Exception{
		return sql.selectList(namespace + ".userSearch", rvo);
	}
	@Override
	public List<AdressMemberVO> adressUserSearch(ResultVO rvo) throws Exception{
		return sql.selectList(namespace + ".adressUserSearch", rvo);
	}
	
	@Override
	public List<AdressMemberVO> adressUserList(String user_uid) throws Exception{
		return sql.selectList(namespace + ".adressUserList", user_uid);
	}
	@Override
	public int adressUserInsert(ResultVO rvo) throws Exception{
		return sql.insert(namespace + ".adressUserInsert", rvo);
	}
	@Override
	public int adressUserUpdate(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".adressUserUpdate", rvo);
	}
	@Override
	public int adressUserDelete(ResultVO rvo) throws Exception{
		return sql.delete(namespace + ".adressUserDelete", rvo);
	}

	@Override
	public int userDeptUpdate(String department_code, String user_uid) throws Exception{
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("department_code", department_code);
		data.put("user_uid", user_uid);
		return sql.update(namespace + ".userDeptUpdate", data);
	}
}
