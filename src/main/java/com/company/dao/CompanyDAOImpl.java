package com.company.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tring.domain.ApprovalVO;
import com.tring.domain.CompanyVO;
import com.tring.domain.DeptVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.company.mappers";

	//회사
	@Override
	public CompanyVO companyDetail(String company_code) throws Exception{
		return sql.selectOne(namespace + ".companyDetail", company_code);
	}
	@Override
	public int companyUpdate(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".companyUpdate", rvo);
	}
	@Override
	public int companyLogoUpdate(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".companyLogoUpdate", rvo);
	}
	
	//부서
	@Override
	public List<DeptVO> deptList(String company_code) throws Exception{
		return sql.selectList(namespace + ".deptList", company_code);
	}
	@Override
	public DeptVO deptDetail(String dept_uid) throws Exception{
		return sql.selectOne(namespace + ".deptDetail", dept_uid);
	}
	@Override
	public int deptInsert(ResultVO rvo) throws Exception{
		return sql.insert(namespace + ".deptInsert", rvo);
	}
	@Override
	public int deptUpdate(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".deptUpdate", rvo);
	}
	@Override
	public int deptDelete(String dept_uid) throws Exception{
		return sql.delete(namespace + ".deptDelete", dept_uid);
	}
	
	@Override
	public List<MemberVO> deptMemberList(String dept_uid) throws Exception{
		return sql.selectList(namespace + ".deptMemberList", dept_uid);
	}

	//사원 승인
	@Override
	public List<ApprovalVO> approvalList(String company_code) throws Exception{
		return sql.selectList(namespace + ".approvalList", company_code);
	}
	@Override
	public ResultVO approvalUser(String user_uid) throws Exception{
		return sql.selectOne(namespace + ".approvalUser", user_uid);
	}
	@Override
	public int approvalInsert(ResultVO rvo) throws Exception{
		return sql.insert(namespace + ".approvalInsert", rvo);
	}
	@Override
	public int approvalProcess(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".approvalProcess", rvo);
	}
	
	//권한 등록
	@Override
	public List<String> employeeAuthorityList(String user_uid) throws Exception{
		return sql.selectList(namespace + ".employeeAuthorityList", user_uid);
	}
	@Override
	public int employeeAuthorityInsert(ResultVO rvo) throws Exception{
		return sql.insert(namespace + ".employeeAuthorityInsert", rvo);
	}
	@Override
	public int employeeAuthorityDelete(ResultVO rvo) throws Exception{
		return sql.delete(namespace + ".employeeAuthorityDelete", rvo);
	}
	@Override
	public int employeeUpdate(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".employeeUpdate", rvo);
	}
	@Override
	public int employeeDelete(ResultVO rvo) throws Exception{
		return sql.update(namespace + ".employeeDelete", rvo);
	}
	
}
