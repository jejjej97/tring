package com.tring.domain;

import java.util.List;

public class DeptVO {

	private String department_code;
	private String company_code;
	private String department_name;
	private String department_mail;
	private String department_phone;
	private List<MemberVO> department_member;
	
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDepartment_mail() {
		return department_mail;
	}
	public void setDepartment_mail(String department_mail) {
		this.department_mail = department_mail;
	}
	public String getDepartment_phone() {
		return department_phone;
	}
	public void setDepartment_phone(String department_phone) {
		this.department_phone = department_phone;
	}
	public List<MemberVO> getDepartment_member() {
		return department_member;
	}
	public void setDepartment_member(List<MemberVO> department_member) {
		this.department_member = department_member;
	}
	

	
}
