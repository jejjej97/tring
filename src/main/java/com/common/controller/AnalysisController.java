package com.common.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.MeetingService;
import com.tring.domain.MemberVO;



/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@RestController
public class AnalysisController
{

	@Inject
	private MeetingService mservice;
	
	private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);

//////////////////////////////////////////////// 메인 출력 자료 ////////////////////////////////////////////////
	
	//당일 회의 리스트
    @RequestMapping(value = "/tring_common_todayMeetingList", method = RequestMethod.POST)
    public Map<String, Object> todayMeetingList(@RequestBody MemberVO vo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
//    	result.put("", list);
    	
        return result;
    }

	//요일별 회의 횟수
    @RequestMapping(value = "/tring_common_MeetingCount", method = RequestMethod.POST)
    public Map<String, Object> MeetingCount(@RequestBody MemberVO vo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
//    	result.put("", list);
    	
        return result;
    }
    
	//당월 키워드 분석
    @RequestMapping(value = "/tring_common_MonthKeyword", method = RequestMethod.POST)
    public Map<String, Object> MonthKeyword(@RequestBody MemberVO vo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
//    	result.put("", list);
    	
        return result;
    }
    
	//같은 회의 참석자 순위
    @RequestMapping(value = "/tring_common_MeetingUserLanking", method = RequestMethod.POST)
    public Map<String, Object> MeetingUserLanking(@RequestBody MemberVO vo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
//    	result.put("", list);
    	
        return result;
    }
    
//////////////////////////////////////////////// 비주얼 리포트 ////////////////////////////////////////////////
    
	//주제별 업무 기간
    @RequestMapping(value = "/tring_common_AgendaPeriod", method = RequestMethod.POST)
    public Map<String, Object> AgendaPeriod(@RequestBody MemberVO vo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
//    	result.put("", list);
    	
        return result;
    }
    
    
	//회의 진행 부서별 순위
    @RequestMapping(value = "/tring_common_MeetingDeptLanking", method = RequestMethod.POST)
    public Map<String, Object> MeetingDeptLanking(@RequestBody MemberVO vo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
//    	result.put("", list);
    	
        return result;
    }
}
