package com.data.controller;


import com.data.service.MemoService;
import com.tring.domain.MemoVO;
import com.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

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
public class MemoController
{

	@Inject
	private MemoService mservice;
	@Inject
	private UserService uservice;
	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	
//////////////////////////////////////////////// 회의 데이터 ////////////////////////////////////////////////
	
	//메모 검색 리스트 출력
	@RequestMapping(value = "/tring_data_memoSearchList", method = RequestMethod.POST)
    public Map<String, Object> memoSearchList(@RequestBody MemoVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
	
	//메모 상세 정보 출력
	@RequestMapping(value = "/tring_data_memoDetail", method = RequestMethod.POST)
    public Map<String, Object> memoDetail(@RequestBody MemoVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }

	//메모 저장
	@RequestMapping(value = "/tring_data_memoInsert", method = RequestMethod.POST)
    public Map<String, Object> memoInsert(@RequestBody MemoVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }

	//메모 수정
	@RequestMapping(value = "/tring_data_memoUpdate", method = RequestMethod.POST)
    public Map<String, Object> memoUpdate(@RequestBody MemoVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
	
	//메모 삭제
	@RequestMapping(value = "/tring_data_memoDelete", method = RequestMethod.POST)
    public Map<String, Object> memoDelete(@RequestBody MemoVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
	
	//메모 공유하기
	@RequestMapping(value = "/tring_data_memoShare", method = RequestMethod.POST)
    public Map<String, Object> memoShare(@RequestBody MemoVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
}