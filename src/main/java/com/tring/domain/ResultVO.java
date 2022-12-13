package com.tring.domain;

import java.util.Date;
import java.util.List;

public class ResultVO {

	private String Route;
	private String verification_code;
	private String search_type;
	private String search_keyword;
	
//////////////////////////////////////////////// MemberVO ////////////////////////////////////////////////
	
	private String user_uid;
	private String company_code;
	private String user_email;
	private String user_password;
	private String department_code;
	private String user_position;
	private String user_name;
	private String user_phone;
	private Date user_regdate;
	private int user_authority;
	private Date user_logdate;
	private String user_login_type;
	private int user_icon;
	
	private int user_status;
	private Date status_regdate;
	private String adress_user_uid;
	private List<String> add_user_uid_list;
	private List<String> del_user_uid_list;
	
//////////////////////////////////////////////// CompanyVO ////////////////////////////////////////////////
	
//	private String company_code;
	private String company_name;
	private String company_email;
	private String company_phone;
	private String company_fax;
	private String company_logo;
	
//////////////////////////////////////////////// DeptVO ////////////////////////////////////////////////

//	private String department_code;
//	private String company_code;
	private String department_name;
	private String department_mail;
	private String department_phone;
	private List<MemberVO> department_member;
	private List<String> add_department_code_list;
	
//////////////////////////////////////////////// FolderVO ////////////////////////////////////////////////

//	private String user_uid;
	private String folder_uid;
	private String folder_name;
	private String folder_highrank;
	private int folder_level;
	private int folder_type;
	private int folder_dataType;
	
	private String meeting_uid;
	private String memo_uid;

//////////////////////////////////////////////// MeetingVO ////////////////////////////////////////////////

	
//////////////////////////////////////////////// MemoVO ////////////////////////////////////////////////
	
	
//////////////////////////////////////////////// ProductVO ////////////////////////////////////////////////
	
	
//////////////////////////////////////////////// CalendarVO ////////////////////////////////////////////////

	
	
//////////////////////////////////////////////// getter & setter ////////////////////////////////////////////////

	public String getSearch_keyword() {
		return search_keyword;
	}

	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public String getVerification_code() {
		return verification_code;
	}

	public void setVerification_code(String verification_code) {
		this.verification_code = verification_code;
	}
	
	public String getUser_uid() {
		return user_uid;
	}

	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getUser_position() {
		return user_position;
	}

	public void setUser_position(String user_position) {
		this.user_position = user_position;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public Date getUser_regdate() {
		return user_regdate;
	}

	public void setUser_regdate(Date user_regdate) {
		this.user_regdate = user_regdate;
	}

	public int getUser_authority() {
		return user_authority;
	}

	public void setUser_authority(int user_authority) {
		this.user_authority = user_authority;
	}

	public Date getUser_logdate() {
		return user_logdate;
	}

	public void setUser_logdate(Date user_logdate) {
		this.user_logdate = user_logdate;
	}

	public String getUser_login_type() {
		return user_login_type;
	}

	public void setUser_login_type(String user_login_type) {
		this.user_login_type = user_login_type;
	}

	public int getUser_icon() {
		return user_icon;
	}

	public void setUser_icon(int user_icon) {
		this.user_icon = user_icon;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	public Date getStatus_regdate() {
		return status_regdate;
	}

	public void setStatus_regdate(Date status_regdate) {
		this.status_regdate = status_regdate;
	}

	public String getAdress_user_uid() {
		return adress_user_uid;
	}

	public void setAdress_user_uid(String adress_user_uid) {
		this.adress_user_uid = adress_user_uid;
	}

	public List<String> getAdd_user_uid_list() {
		return add_user_uid_list;
	}

	public void setAdd_user_uid_list(List<String> add_user_uid_list) {
		this.add_user_uid_list = add_user_uid_list;
	}

	public List<String> getDel_user_uid_list() {
		return del_user_uid_list;
	}

	public void setDel_user_uid_list(List<String> del_user_uid_list) {
		this.del_user_uid_list = del_user_uid_list;
	}

	public String getSearch_type() {
		return search_type;
	}

	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}

	public String getFolder_uid() {
		return folder_uid;
	}

	public void setFolder_uid(String folder_uid) {
		this.folder_uid = folder_uid;
	}

	public String getFolder_name() {
		return folder_name;
	}

	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}

	public String getFolder_highrank() {
		return folder_highrank;
	}

	public void setFolder_highrank(String folder_highrank) {
		this.folder_highrank = folder_highrank;
	}

	public int getFolder_level() {
		return folder_level;
	}

	public void setFolder_level(int folder_level) {
		this.folder_level = folder_level;
	}

	public int getFolder_type() {
		return folder_type;
	}

	public void setFolder_type(int folder_type) {
		this.folder_type = folder_type;
	}

	public int getFolder_dataType() {
		return folder_dataType;
	}

	public void setFolder_dataType(int folder_dataType) {
		this.folder_dataType = folder_dataType;
	}

	public String getMeeting_uid() {
		return meeting_uid;
	}

	public void setMeeting_uid(String meeting_uid) {
		this.meeting_uid = meeting_uid;
	}

	public String getMemo_uid() {
		return memo_uid;
	}

	public void setMemo_uid(String memo_uid) {
		this.memo_uid = memo_uid;
	}

	public String getRoute() {
		return Route;
	}

	public void setRoute(String route) {
		Route = route;
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

	public String getCompany_email() {
		return company_email;
	}

	public void setCompany_email(String company_email) {
		this.company_email = company_email;
	}

	public String getCompany_phone() {
		return company_phone;
	}

	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}

	public String getCompany_fax() {
		return company_fax;
	}

	public void setCompany_fax(String company_fax) {
		this.company_fax = company_fax;
	}

	public String getCompany_logo() {
		return company_logo;
	}

	public void setCompany_logo(String company_logo) {
		this.company_logo = company_logo;
	}

	public List<String> getAdd_department_code_list() {
		return add_department_code_list;
	}

	public void setAdd_department_code_list(List<String> add_department_code_list) {
		this.add_department_code_list = add_department_code_list;
	}
	
	
}
