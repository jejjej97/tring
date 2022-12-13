package com.tring.domain;


public class FolderVO {

	private String folder_uid;
	private String user_uid;
	private String folder_name;
	private String folder_highrank;
	private int folder_level;
	private int folder_type;
	private int folder_dataType;
	
	private String meeting_uid;
	private String memo_uid;
	
	private String department_code;
	
	private String Route;
	
	public String getFolder_uid() {
		return folder_uid;
	}
	public void setFolder_uid(String folder_uid) {
		this.folder_uid = folder_uid;
	}
	public String getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
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
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public String getRoute() {
		return Route;
	}
	public void setRoute(String route) {
		Route = route;
	}
}
