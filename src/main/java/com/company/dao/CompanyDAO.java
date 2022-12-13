package com.company.dao;

import java.util.List;

import com.tring.domain.ApprovalVO;
import com.tring.domain.CompanyVO;
import com.tring.domain.DeptVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;

public interface CompanyDAO {

	//회사
	public CompanyVO companyDetail(String company_code) throws Exception;
	public int companyUpdate(ResultVO rvo) throws Exception;
	public int companyLogoUpdate(ResultVO rvo) throws Exception;
		
	//부서
	public List<DeptVO> deptList(String company_code) throws Exception;
	public DeptVO deptDetail(String dept_uid) throws Exception;
	public int deptInsert(ResultVO rvo) throws Exception;
	public int deptUpdate(ResultVO rvo) throws Exception;
	public int deptDelete(String dept_uid) throws Exception;
	
	public List<MemberVO> deptMemberList(String dept_uid) throws Exception;
	
	//사원 승인
	public List<ApprovalVO> approvalList(String company_code) throws Exception;
	public ResultVO approvalUser(String user_uid) throws Exception;
	public int approvalInsert(ResultVO rvo) throws Exception;
	public int approvalProcess(ResultVO rvo) throws Exception;
	
	//권한 등록
	public List<String> employeeAuthorityList(String user_uid) throws Exception;
	public int employeeAuthorityInsert(ResultVO rvo) throws Exception;
	public int employeeAuthorityDelete(ResultVO rvo) throws Exception;
	public int employeeUpdate(ResultVO rvo) throws Exception;
	public int employeeDelete(ResultVO rvo) throws Exception;
}
