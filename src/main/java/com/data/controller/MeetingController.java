package com.data.controller;


import com.data.service.MeetingService;
import com.tring.domain.MeetingVO;
import com.tring.domain.MemberVO;
import com.user.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@RestController
public class MeetingController
{

	@Inject
	private MeetingService mservice;
	@Inject
	private UserService uservice;
	private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);
	
//////////////////////////////////////////////// 회의 데이터 ////////////////////////////////////////////////
	
	//회의 상세 정보 출력
	@RequestMapping(value = "/tring_data_meetingDetail", method = RequestMethod.POST)
    public Map<String, Object> meetingDetail(@RequestBody MeetingVO mvo) throws Exception {
    	
    	//요청 {user_uid, meeting_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	MeetingVO vo = mservice.meetingDetail(mvo);
    	if(vo != null) {
	    	result.put("meeting_uid", vo.getMeeting_uid());
	    	result.put("meeting_name", vo.getMeeting_name());
	    	result.put("meeting_date", vo.getMeeting_date());
	    	result.put("meeting_place", vo.getMeeting_place());
	    	result.put("meeting_memo", vo.getMeeting_memo());
	    	result.put("company_code", vo.getCompany_code());
	    	result.put("dept_uid", vo.getDept_uid());
	    	result.put("dept_name", vo.getDept_name());
	    	result.put("user_uid", vo.getUser_uid());
	    	
	    	//meeting_content
	    	final Map<String, Object> agenda = new HashMap<String, Object>();
	    	if(vo != null) {
		    	String meeting_content = vo.getMeeting_content();
		    	
		    	JSONObject ListJsonObject = new JSONObject(meeting_content);
		    	
		    	JSONArray meetingArr = (JSONArray) ListJsonObject.get("agenda");
		    	
		    	ArrayList<Map<String, Object>> array3 = new ArrayList<Map<String, Object>>();
		    	for(int i=0; i<meetingArr.length(); i++){
		    		JSONObject item = (JSONObject)meetingArr.get(i);
		    		Map<String, Object> map3 = new HashMap<String, Object>();
		    		
		    		if(item.has("agenda_content")) {
		//    			System.out.println((String)item.get("agenda_content"));
		    			map3.put("agenda_content", (String)item.get("agenda_content"));
		    		}
		    		
		    		if(item.has("agenda_result")) {
		//    			System.out.println((String)item.get("agenda_result"));
		    			map3.put("agenda_result", (String)item.get("agenda_result"));
		    		}
		    		
		    		if(item.has("issue")) {
		    			JSONArray issueArr = (JSONArray) item.get("issue");
		    			ArrayList<Map<String, Object>> array2 = new ArrayList<Map<String, Object>>();
		    			
		    			for(int j=0; j<issueArr.length(); j++){
		    				JSONObject item2 = (JSONObject)issueArr.get(j);
		    				Map<String, Object> map2 = new HashMap<String, Object>();
		    				
		    				if(item2.has("issue_content")) {
		//    					System.out.println((String)item2.get("issue_content"));
		    					map2.put("issue_content", (String)item2.get("issue_content"));
		    				}
		    				
		    				if(item2.has("consultation")) {
		    					JSONArray consultationArr = (JSONArray) item2.get("consultation");
		    					ArrayList<Map<String, Object>> array1 = new ArrayList<Map<String, Object>>();
		    					
		    					for(int e=0; e<consultationArr.length(); e++){
		    						JSONObject item3 = (JSONObject)consultationArr.get(e);
		    						
		    						if(item3.has("consultation_content")) {
		    							Map<String, Object> map1 = new HashMap<String, Object>();
		//    							System.out.println((String)item3.get("consultation_content"));
		    							map1.put("consultation_content", (String)item3.get("consultation_content"));
		    							array1.add(map1);
		    						}
		    					}
		    					map2.put("consultation", array1);
		    				}
		    				array2.add(map2);
		    			}
		    			map3.put("issue", array2);
		    		}
		    		array3.add(map3);
		    	}
		
		    	agenda.put("agenda", array3);
		    	result.put("meeting_content", agenda);
		    	
		    	//미팅멤버
		    	ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		    	mvo.setMs_type("member");
		    	List<String> meeting_memberUid = mservice.meetingMember(mvo);
		    	for(int i=0; i<meeting_memberUid.size(); i++) {
		    		MemberVO member = uservice.userInfo(meeting_memberUid.get(i));
		    		memberList.add(member);
		    		
//		    		if(meeting_memberUid.get(i) == vo.getUser_uid()) {
//		    			result.put("access", 1);
//		    		}
		    	}
		    	result.put("meeting_member", memberList);
		    	
		    	//공유멤버
		    	ArrayList<MemberVO> shareList = new ArrayList<MemberVO>();
		    	mvo.setMs_type("share");
		    	List<String> share_memberUid = mservice.meetingMember(mvo);
		    	for(int i=0; i<share_memberUid.size(); i++) {
		    		MemberVO member = uservice.userInfo(share_memberUid.get(i));
		    		shareList.add(member);
		    	}
		    	result.put("share_member", memberList);
		    	
		    	//파일 리스트
    		}
    	}
    	return result;
    }

	//회의 저장
	@RequestMapping(value = "/tring_data_meetingInsert", method = RequestMethod.POST)
    public Map<String, Object> meetingInsert(@RequestBody MeetingVO mvo, HttpServletRequest request) throws Exception {
		
    	//요청 {meeting_name, meeting_date, meeting_place, meeting_memo, company_code, dept_uid, meeting_content, user_uid, share_level}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	String meeting_uid = UUID.randomUUID().toString().replace("-","");
    	
    	mvo.setMeeting_uid(meeting_uid);
    	
    	int action = mservice.meetingInsert(mvo);
    	
    	mvo.setMs_type("member");
    	mservice.meetingMemberInsert(mvo);
    	
//    	//파일 업로드
//		String savePath = request.getSession().getServletContext().getRealPath("resources/meeting");
//    	
//		if(mvo.getMeetingfile() != null) {
//			System.out.println(mvo.getMeetingfile().length);
//			
//	    	for(int i = 0; i < mvo.getMeetingfile().length; i++) {
//				if(!mvo.getMeetingfile()[i].isEmpty()) {
//					String fileName = mvo.getMeetingfile()[i].getOriginalFilename();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//					String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
//					renameFileName = i + "_" + renameFileName + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
////					File originFile = new File(savePath + "/" + fileName);
//					File renameFile = new File(savePath + "/" + renameFileName);
//					mvo.getMeetingfile()[i].transferTo(renameFile);
//					
//					mvo.setSave_fileName(renameFileName);
//					mvo.setOriginal_fileName(fileName);
//					int action2 = mservice.meetingFileUpload(mvo);
//					
//					if(action2 != 1) {
//			    		result.put("resultMsg", "file upload error.");
//			    	}
//				}
//			}
//		}

    	
		if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
		
		return result;
	}
	
	//회의 수정
	@RequestMapping(value = "/tring_data_meetingUpdate", method = RequestMethod.POST)
    public Map<String, Object> meetingUpdate(@RequestBody MeetingVO mvo) throws Exception {
		
    	//요청 {meeting_uid, meeting_name, meeting_date, meeting_place, meeting_memo, meeting_content, share_level}
		
    	final Map<String, Object> result = new HashMap<String, Object>();

		int action = mservice.meetingUpdate(mvo);
		
		if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
		
		return result;
	}
	
	//회의 삭제
	@RequestMapping(value = "/tring_data_meetingDelete", method = RequestMethod.POST)
    public Map<String, Object> meetingDelete(@RequestBody MeetingVO mvo) throws Exception {
		
		//요청 {meeting_uid}
		
    	final Map<String, Object> result = new HashMap<String, Object>();

		int action = mservice.meetingDelete(mvo.getMeeting_uid());
		
		if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
		
		return result;
	}

	//회의 & 공유 멤버 추가
	@RequestMapping(value = "/tring_data_meetingMemberInsert", method = RequestMethod.POST)
    public Map<String, Object> meetingMemberInsert(@RequestBody MeetingVO mvo) throws Exception {
		
		//요청 {meeting_uid, user_uid, ms_type}
		
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	int action = mservice.meetingMemberInsert(mvo);
		
		if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
		return result;
	}
	
	//회의 & 공유 멤버 삭제
	@RequestMapping(value = "/tring_data_meetingMemberDelete", method = RequestMethod.POST)
    public Map<String, Object> meetingMemberDelete(@RequestBody MeetingVO mvo) throws Exception {
		
		//요청 {meeting_uid, user_uid}
		
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	int action = mservice.meetingMemberDelete(mvo);
		
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
		return result;
	}
	
	//회의 양식 다운로드
	@RequestMapping(value = "/tring_data_meetingDownload", method = RequestMethod.POST)
    public Map<String, Object> meetingDownload(@RequestBody MeetingVO mvo) throws Exception {
		
		//요청 {meeting_uid}
		
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
		return result;
	}
	
	//구글 캘린더 저장
	@RequestMapping(value = "/tring_data_googleCalendar", method = RequestMethod.POST)
    public Map<String, Object> googleCalendar(@RequestBody MeetingVO mvo) throws Exception {
		
		//요청 {meeting_uid}
		
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
		return result;
	}
}
