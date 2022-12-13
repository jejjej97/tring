package com.data.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.data.dao.MeetingDAO;
import com.tring.domain.MeetingVO;



@Service
public class MeetingServiceImpl implements MeetingService{
	
	@Inject
	private MeetingDAO dao;
	
	@Override
	public List<MeetingVO> meetingUidList(String meeting_uid) throws Exception{
		return dao.meetingUidList(meeting_uid);
	}
	@Override
	public List<MeetingVO> meetingList(MeetingVO vo) throws Exception{
		return dao.meetingList(vo);
	}
	@Override
	public MeetingVO meetingDetail(MeetingVO vo) throws Exception{
		return dao.meetingDetail(vo);
	}
	@Override
	public List<String> meetingMember(MeetingVO mvo) throws Exception{
		return dao.meetingMember(mvo);
	}
	@Override
	public int meetingInsert(MeetingVO vo) throws Exception{
		return dao.meetingInsert(vo);
	}
	@Override
	public int meetingUpdate(MeetingVO vo) throws Exception{
		return dao.meetingUpdate(vo);
	}
	@Override
	public int meetingDelete(String meeting_uid) throws Exception{
		return dao.meetingDelete(meeting_uid);
	}
	
	@Override
	public int meetingMemberInsert(MeetingVO vo) throws Exception{
		return dao.meetingMemberInsert(vo);
	}
	@Override
	public int meetingMemberDelete(MeetingVO vo) throws Exception{
		return dao.meetingMemberDelete(vo);
	}
	
	@Override
	public int meetingFileUpload(MeetingVO vo) throws Exception{
		return dao.meetingFileUpload(vo);
	}
}
