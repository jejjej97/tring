package com.tring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarVO {

	private String summary;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String description;
	private String eventId;
	private String calendarId;
	
	private String calendarUserId;
	private String rule;
	
	{
		description = "";
	}
	
	public Date getStartDateTime() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		return format.parse(startDate + startTime);
	}
	
	public Date getEndDateTime() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		return format.parse(endDate + endTime);
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getCalendarUserId() {
		return calendarUserId;
	}

	public void setCalendarUserId(String calendarUserId) {
		this.calendarUserId = calendarUserId;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
	
	
	
}
