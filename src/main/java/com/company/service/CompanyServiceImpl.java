package com.company.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.CompanyDAO;
import com.tring.domain.ApprovalVO;
import com.tring.domain.CompanyVO;
import com.tring.domain.DeptVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;


@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Inject
	private CompanyDAO dao;
	
	//회사
	@Override
	public CompanyVO companyDetail(String company_code) throws Exception{
		return dao.companyDetail(company_code);
	}
	@Override
	public int companyUpdate(ResultVO rvo) throws Exception{
		return dao.companyUpdate(rvo);
	}
	@Override
	public int companyLogoUpdate(ResultVO rvo) throws Exception{
		return dao.companyLogoUpdate(rvo);
	}
	
	//부서
	@Override
	public List<DeptVO> deptList(String company_code) throws Exception{
		return dao.deptList(company_code);
	}
	@Override
	public DeptVO deptDetail(String dept_uid) throws Exception{
		return dao.deptDetail(dept_uid);
	}
	@Override
	public int deptInsert(ResultVO rvo) throws Exception{
		return dao.deptInsert(rvo);
	}
	@Override
	public int deptUpdate(ResultVO rvo) throws Exception{
		return dao.deptUpdate(rvo);
	}
	@Override
	public int deptDelete(String dept_uid) throws Exception{
		return dao.deptDelete(dept_uid);
	}
	
	@Override
	public List<MemberVO> deptMemberList(String dept_uid) throws Exception{
		return dao.deptMemberList(dept_uid);
	}
	
	//사원 승인
	@Override
	public List<ApprovalVO> approvalList(String company_code) throws Exception{
		return dao.approvalList(company_code);
	}
	@Override
	public ResultVO approvalUser(String user_uid) throws Exception{
		return dao.approvalUser(user_uid);
	}
	@Override
	public int approvalInsert(ResultVO rvo) throws Exception{
		return dao.approvalInsert(rvo);
	}
	@Override
	public int approvalProcess(ResultVO rvo) throws Exception{
		return dao.approvalProcess(rvo);
	}
	
	//권한 등록
	@Override
	public List<String> employeeAuthorityList(String user_uid) throws Exception{
		return dao.employeeAuthorityList(user_uid);
	}
	@Override
	public int employeeAuthorityInsert(ResultVO rvo) throws Exception{
		return dao.employeeAuthorityInsert(rvo);
	}
	@Override
	public int employeeAuthorityDelete(ResultVO rvo) throws Exception{
		return dao.employeeAuthorityDelete(rvo);
	}
	@Override
	public int employeeUpdate(ResultVO rvo) throws Exception{
		return dao.employeeUpdate(rvo);
	}
	@Override
	public int employeeDelete(ResultVO rvo) throws Exception{
		return dao.employeeDelete(rvo);
	}
}
