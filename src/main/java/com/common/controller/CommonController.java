package com.common.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.service.CompanyService;
import com.data.service.MeetingService;
import com.tring.domain.DeptVO;
import com.tring.domain.MeetingVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;
import com.user.service.AccountService;
import com.user.service.UserService;




/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@RestController
public class CommonController
{
	
	@Inject
	private AccountService aservice;
	@Inject
	private UserService uservice;
	@Inject
	private CompanyService cpservice;
	@Inject
	private MeetingService dservice;
	@Inject
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	//비밀 번호 변경
    @RequestMapping(value = "/tring_common_userPwdUpdate", method = RequestMethod.POST)
    public Map<String, Object> userPwdUpdate(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
    	
    	//요청 {user_password}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	String user_uid = (String) session.getAttribute("sessionUid");
    	rvo.setUser_uid(user_uid);
    	
    	//패스워드 암호화
    	rvo.setUser_password(bcryptPasswordEncoder.encode(rvo.getUser_password()));
    	
    	int action = aservice.userPwdUpdate(rvo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
        return result;
    }
    
    //회사 부서 리스트
    @RequestMapping(value = "/tring_common_deptList", method = RequestMethod.POST)
    public Map<String, Object> deptList(@RequestBody ResultVO mvo) throws Exception {
    	
    	//요청 {company_code}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	List<DeptVO> list = cpservice.deptList(mvo.getCompany_code());
    	
    	if(list.size() > 0) {
    		result.put("department_list", list);
    		result.put("resultMsg", "department exists.");
    	}else {
    		result.put("resultMsg", "department none.");
    	}

        return result;
    }
	
    //회의록 검색
    @RequestMapping(value = "/tring_common_meetingList", method = RequestMethod.POST)
    public Map<String, Object> meetingList(@RequestBody MeetingVO mvo) throws Exception {
		
		//요청 {user_uid, company_code}
    	
		final Map<String, Object> result = new HashMap<String, Object>();
		
		MemberVO user = uservice.userInfo(mvo.getUser_uid());
		List<MeetingVO> meeting_list = new ArrayList<MeetingVO>();
		
		if(user != null) {
			//마스터
			if(user.getUser_authority() == 0 || user.getUser_authority() == 1) {
				mvo.setAccessUser(user.getUser_authority());
				meeting_list = dservice.meetingList(mvo);
			}
	
			//일반
			else {
				
				mvo.setAccessUser(user.getUser_authority());
				List<String> deptUid_list = uservice.userAccess_deptUid(mvo.getUser_uid());
				
				//권한부여자
				if(deptUid_list.size() > 0) {
					System.out.println("권한");
					for(int i=0; i<deptUid_list.size(); i++) {
						Map<String, Object> dept = new HashMap<String, Object>();
						
						mvo.setDept_uid_list(deptUid_list);
						List<MeetingVO> dept_list = dservice.meetingList(mvo);
						
						meeting_list = dept_list;
					}
				}
				
				//일반사원
				else {
					System.out.println("사원");
					List<MeetingVO> meetingUid_list = dservice.meetingUidList(mvo.getUser_uid());
					for(int i=0; i<meetingUid_list.size(); i++) {
	
						mvo.setMeeting_uid(meetingUid_list.get(i).getMeeting_uid());
						mvo.setMs_type(meetingUid_list.get(i).getMs_type());
						
						MeetingVO meeting = dservice.meetingDetail(mvo);
				
						MeetingVO vo = new MeetingVO();
						vo.setMeeting_uid(meeting.getMeeting_uid());
						vo.setMeeting_name(meeting.getMeeting_name());
						vo.setMeeting_date(meeting.getMeeting_date());
						vo.setMeeting_place(meeting.getMeeting_place());
						vo.setMeeting_memo(meeting.getMeeting_memo());
						vo.setShare_level(meeting.getShare_level());
						vo.setMs_type(meetingUid_list.get(i).getMs_type());
						
						meeting_list.add(vo);
	
					}
				}
			}
		}
    	
		result.put("meeting_list", meeting_list);
		
    	return result;
	}
    
	//유저 검색
	@RequestMapping(value = "/tring_common_userSearch", method = RequestMethod.POST)
    public Map<String, Object> userSearch(@RequestBody ResultVO rvo) throws Exception {
		
		//요청 {company_code, search_type, search_keyword}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	if(rvo.getSearch_type() != null) {
	    	//이메일 중복 체크
	    	if(rvo.getSearch_type().equals("emailCheck")) {
	    		List<MemberVO> check = uservice.userSearch(rvo);
	        	if(check.size() > 0) {
	        		result.put("resultMsg", "overlap");
	        	}else {
	        		result.put("resultMsg", "available");
	        	}
	        
	        //유저 이메일 검색
	    	} else if(rvo.getSearch_type().equals("userEmail")) {
	    		rvo.setSearch_keyword("%"+rvo.getSearch_keyword()+"%");
	    		
	    	//회사 유저 검색
	    		//전체 검색
	    	} else if(rvo.getSearch_type().equals("companyUserAll")) {
	    		rvo.setSearch_keyword(null);
	    		//이름 검색
	    	} else if(rvo.getSearch_type().equals("companyUserName")) {
	    		rvo.setSearch_keyword("%"+rvo.getSearch_keyword()+"%");
	    	}
	
	    	List<MemberVO> user = uservice.userSearch(rvo);
	    	for(int i=0; i<user.size(); i++) {
	    		if(user.get(i).getDepartment_code() == null) {
	    			user.get(i).setDepartment_name("미배정");
	    		}
	    	}
	    	
	    	
	    	result.put("searchInfo", user);
    	}
    	
		return result;
	}
	
	//회의 멤버 리스트
//	@RequestMapping(value = "/tring_common_meetingMemberList", method = RequestMethod.POST)
//	public Map<String, Object> meetingMemberList(@RequestBody MeetingVO mvo) throws Exception {
//	
//		//요청 {meeting_uid, company_code, user_uid, ms_type, searchType = 0 or 1} searchType는 주소에 get 방식
//		
//		final Map<String, Object> result = new HashMap<String, Object>();
//		
//		//자신과 현재 참여중 멤버 제외
//		List<String> meeting_memberUid = dservice.meetingMember(mvo);
//		
//		if(meeting_memberUid != null) {
//			MemberVO mbvo = new MemberVO();
//			mbvo.setCompany_code(mvo.getCompany_code());
//			mbvo.setUser_uid(mvo.getUser_uid());
//			mbvo.setUser_uid_list(meeting_memberUid);
//			
//			//주소록 검색
//			ArrayList<MemberRVO> adress_userlist = new ArrayList<MemberRVO>();
//			if(mvo.getSearchType() == 0) {
//				List<MemberVO> adress_userUid_list = uservice.userConnectList(mbvo);
//					for(int i=0; i<adress_userUid_list.size(); i++) {
//					MemberRVO user = uservice.userInfo(adress_userUid_list.get(i).getConnect_user_uid());
//					adress_userlist.add(user);
//				}
//				
//				result.put("adress_userlist", adress_userlist);
//			}
//			
//			//부서명 검색
//			else if(mvo.getSearchType() == 1) {
//				List<DeptVO> vo = cpservice.deptList(mvo.getCompany_code());
//				ArrayList<Map<String, Object>> dept_array = new ArrayList<Map<String, Object>>();
//				
//				for(int i=0; i<vo.size(); i++) {
//					Map<String, Object> dept = new HashMap<String, Object>();
//					
//					//부서 분류
//					dept.put("dept_uid", vo.get(i).getDept_uid());
//					dept.put("dept_name", vo.get(i).getDept_name());
//					//부서 멤버
//					mbvo.setDept_name(vo.get(i).getDept_name());
//					List<MemberRVO> dept_userlist = uservice.deptUserConnectList(mbvo);
//					dept.put("dept_userlist", dept_userlist);
//					
//					dept_array.add(dept);
//				}
//				
//				result.put("deptlist", dept_array);
//			}
//			
//			else {
//				result.put("resultMsg", "error.");
//			}
//		}
//		
//		return result;
//	}

	//파일 리스트 출력
    @RequestMapping(value = "/tring_common_fileList", method = RequestMethod.POST)
    public Map<String, Object> fileList(@RequestBody MemberVO mvo) throws Exception {
    	
    	//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
        return result;
    }
    
	//파일 검색
    @RequestMapping(value = "/tring_common_fileSearch", method = RequestMethod.POST)
    public Map<String, Object> fileSearch(@RequestBody MemberVO mvo) throws Exception {
    	
    	//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
        return result;
    }
    
	//파일 삭제
    @RequestMapping(value = "/tring_common_fileDelete", method = RequestMethod.POST)
    public Map<String, Object> fileDelete(@RequestBody MemberVO mvo) throws Exception {
    	
    	//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
        return result;
    }

}
