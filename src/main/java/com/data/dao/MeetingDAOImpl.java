package com.data.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tring.domain.MeetingVO;

@Repository
public class MeetingDAOImpl implements MeetingDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.Meeting.mappers";

	@Override
	public List<MeetingVO> meetingUidList(String meeting_uid) throws Exception{
		return sql.selectList(namespace + ".meetingUidList", meeting_uid);
	}
	@Override
	public List<MeetingVO> meetingList(MeetingVO vo) throws Exception{
		return sql.selectList(namespace + ".meetingList", vo);
	}
	@Override
	public MeetingVO meetingDetail(MeetingVO vo) throws Exception{
		return sql.selectOne(namespace + ".meetingDetail", vo);
	}
	@Override
	public List<String> meetingMember(MeetingVO mvo) throws Exception{
		return sql.selectList(namespace + ".meetingMember", mvo);
	}
	@Override
	public int meetingInsert(MeetingVO vo) throws Exception{
		return sql.insert(namespace + ".meetingInsert", vo);
	}
	@Override
	public int meetingUpdate(MeetingVO vo) throws Exception{
		return sql.update(namespace + ".meetingUpdate", vo);
	};
	@Override
	public int meetingDelete(String meeting_uid) throws Exception{
		return sql.delete(namespace + ".meetingDelete", meeting_uid);
	}
	
	@Override
	public int meetingMemberInsert(MeetingVO vo) throws Exception{
		return sql.insert(namespace + ".meetingMemberInsert", vo);
	}
	@Override
	public int meetingMemberDelete(MeetingVO vo) throws Exception{
		return sql.delete(namespace + ".meetingMemberDelete", vo);
	}
	
	@Override
	public int meetingFileUpload(MeetingVO vo) throws Exception{
		return sql.insert(namespace + ".meetingFileUpload", vo);
	}
}
