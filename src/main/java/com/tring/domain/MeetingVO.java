package com.tring.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MeetingVO {

	private String meeting_uid;
	private String meeting_name;
	private String meeting_date;
	private String meeting_place;
	private String meeting_memo;
	private String company_code;
	private String dept_uid;
	private String dept_name;
	private String meeting_content;
	private String user_uid;
	private String ms_type;
	private int share_level;
	private int accessUser=2;
	private int searchType;
	
	private List<String> user_uid_list;
	private List<String> dept_uid_list;
	
	public String getMeeting_uid() {
		return meeting_uid;
	}
	public void setMeeting_uid(String meeting_uid) {
		this.meeting_uid = meeting_uid;
	}
	public String getMeeting_name() {
		return meeting_name;
	}
	public void setMeeting_name(String meeting_name) {
		this.meeting_name = meeting_name;
	}
	public String getMeeting_date() {
		return meeting_date;
	}
	public void setMeeting_date(String meeting_date) {
		this.meeting_date = meeting_date;
	}
	public String getMeeting_place() {
		return meeting_place;
	}
	public void setMeeting_place(String meeting_place) {
		this.meeting_place = meeting_place;
	}
	public String getMeeting_memo() {
		return meeting_memo;
	}
	public void setMeeting_memo(String meeting_memo) {
		this.meeting_memo = meeting_memo;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getDept_uid() {
		return dept_uid;
	}
	public void setDept_uid(String dept_uid) {
		this.dept_uid = dept_uid;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getMeeting_content() {
		return meeting_content;
	}
	public void setMeeting_content(String meeting_content) {
		this.meeting_content = meeting_content;
	}

	public String getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}
	public int getShare_level() {
		return share_level;
	}
	public void setShare_level(int share_level) {
		this.share_level = share_level;
	}
	public String getMs_type() {
		return ms_type;
	}
	public void setMs_type(String ms_type) {
		this.ms_type = ms_type;
	}
	public int getAccessUser() {
		return accessUser;
	}
	public void setAccessUser(int accesUser) {
		this.accessUser = accesUser;
	}
	
	public List<String> getUser_uid_list() {
		return user_uid_list;
	}
	public void setUser_uid_list(List<String> user_uid_list) {
		this.user_uid_list = user_uid_list;
	}
	public List<String> getDept_uid_list() {
		return dept_uid_list;
	}
	public void setDept_uid_list(List<String> dept_uid_list) {
		this.dept_uid_list = dept_uid_list;
	}
	
	public int getSearchType() {
		return searchType;
	}
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

}
