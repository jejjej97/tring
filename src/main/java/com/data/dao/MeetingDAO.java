package com.data.dao;

import java.util.List;

import com.tring.domain.MeetingVO;

public interface MeetingDAO {

	public List<MeetingVO> meetingUidList(String meeting_uid) throws Exception;
	public List<MeetingVO> meetingList(MeetingVO vo) throws Exception;
	public MeetingVO meetingDetail(MeetingVO vo) throws Exception;
	public List<String> meetingMember(MeetingVO mvo) throws Exception;
	public int meetingInsert(MeetingVO vo) throws Exception;
	public int meetingUpdate(MeetingVO vo) throws Exception;
	public int meetingDelete(String meeting_uid) throws Exception;
	
	public int meetingMemberInsert(MeetingVO vo) throws Exception;
	public int meetingMemberDelete(MeetingVO vo) throws Exception;
	
	public int meetingFileUpload(MeetingVO vo) throws Exception;
}
