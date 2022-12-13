package com.user.controller;

import com.company.service.CompanyService;
import com.tring.domain.AdressMemberVO;
import com.tring.domain.DeptVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;
import com.user.service.AccountService;
import com.user.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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



/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@RestController
public class UserController
{
	
	@Inject
	private AccountService aservice;
	@Inject
	private UserService uservice;
	@Inject
	private CompanyService cpservice;
	@Inject
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
//////////////////////////////////////////////// 회원 정보 ////////////////////////////////////////////////
	
	//회원 정보 출력
	@RequestMapping(value = "/tring_user_userInfo", method = RequestMethod.POST)
    public Map<String, Object> userInfo(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
    	
    	//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
    	String user_uid = (String) session.getAttribute("sessionUid");
    	
    	MemberVO user = uservice.userInfo(user_uid);
    	    	
    	DeptVO dvo = cpservice.deptDetail(user.getDepartment_code());
		if(dvo != null) {
			user.setDepartment_name(dvo.getDepartment_name());
		}
    	
    	result.put("myInfo", user);
    	
    	return result;
    }

	//회원 정보 수정
	@RequestMapping(value = "/tring_user_userInfoUpdate", method = RequestMethod.POST)
    public Map<String, Object> userInfoUpdate(@RequestBody ResultVO rvo) throws Exception {
    	
    	//요청 {user_uid, department_code, user_position, user_name, user_phone}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	int action = uservice.userInfoUpdate(rvo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
	
	//회원 아이콘 수정
	@RequestMapping(value = "/tring_user_userIconUpdate", method = RequestMethod.POST)
    public Map<String, Object> userIconUpdate(@RequestBody ResultVO rvo) throws Exception {
    	
    	//요청 {user_uid, user_icon}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	int action = uservice.userIconUpdate(rvo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
	
	//회원 탈퇴
	@RequestMapping(value = "/tring_user_userInfoDelete", method = RequestMethod.POST)
    public Map<String, Object> userInfoDelete(@RequestBody ResultVO rvo) throws Exception {
    	
    	//요청 {user_email, user_password}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	//패스워드 확인
    	ResultVO mvo = aservice.companyLogin(rvo);
    	
    	if(mvo != null && bcryptPasswordEncoder.matches(rvo.getUser_password(), mvo.getUser_password())) {
    		
    		//유저 삭제
    		int action = uservice.userInfoDelete(mvo.getUser_uid());
    		
    		if(action == 1) {
        		result.put("resultMsg", "success.");
        	}else {
        		result.put("resultMsg", "error.");
        	}
        	
		}else if(mvo == null){
			result.put("resultMsg", "not exist user.");
		}else if(!bcryptPasswordEncoder.matches(rvo.getUser_password(), mvo.getUser_password())){
			result.put("resultMsg", "password wrong.");
		}else {
			result.put("resultMsg2", "error.");
		}
    	
    	return result;
    }
	
	//알람 리스트 출력(3개월 전까지)
	@RequestMapping(value = "/tring_user_userAlarmList", method = RequestMethod.POST)
    public Map<String, Object> userAlarmList(@RequestBody ResultVO rvo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
	
	//알람 확인 처리
	@RequestMapping(value = "/tring_user_userAlarmCheck", method = RequestMethod.POST)
    public Map<String, Object> userAlarmCheck(@RequestBody ResultVO rvo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
	
//////////////////////////////////////////////// 주소록 ////////////////////////////////////////////////
	
	//주소록 등록자 리스트 출력
	@RequestMapping(value = "/tring_user_adressUserSearch", method = RequestMethod.POST)
    public Map<String, Object> adressUserSearch(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
    	
    	//요청 {search_type, search_keyword} search_type = name, email, phone
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	String user_uid = (String) session.getAttribute("sessionUid");
    	rvo.setUser_uid(user_uid);
    	
    	rvo.setSearch_keyword("%"+rvo.getSearch_keyword()+"%");
    	
    	List<AdressMemberVO> adressUserList = uservice.adressUserSearch(rvo);
    	
    	result.put("adressUserSearchList", adressUserList);
    	
    	return result;
    }
	
	//주소록 등록자 리스트 출력
	@RequestMapping(value = "/tring_user_adressUserList", method = RequestMethod.POST)
    public Map<String, Object> adressUserList(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
    	
    	//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	String user_uid = (String) session.getAttribute("sessionUid");
    	
    	List<AdressMemberVO> adressUserList = uservice.adressUserList(user_uid);
    	
    	result.put("adressUserList", adressUserList);
    	
    	return result;
    }
    
	//주소록 등록
    @RequestMapping(value = "/tring_user_adressUserInsert", method = RequestMethod.POST)
    public Map<String, Object> adressUserInsert(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
    	
    	//요청 {user_email, user_name, user_phone, company_name, department_name, user_position}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	String user_uid = (String) session.getAttribute("sessionUid");
    	rvo.setUser_uid(user_uid);
    	
    	String adress_user_uid = UUID.randomUUID().toString().replace("-","");
    	rvo.setAdress_user_uid(adress_user_uid);
    	
    	int action = uservice.adressUserInsert(rvo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
    
    //주소록 수정
    @RequestMapping(value = "/tring_user_adressUserUpdate", method = RequestMethod.POST)
    public Map<String, Object> adressUserUpdate(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
    	
    	//요청 {adress_user_uid, user_email, user_name, user_phone, company_name, department_name, user_position}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	String user_uid = (String) session.getAttribute("sessionUid");
    	rvo.setUser_uid(user_uid);
    	
    	int action = uservice.adressUserUpdate(rvo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
    
    //주소록 삭제
    @RequestMapping(value = "/tring_user_adressUserDelete", method = RequestMethod.POST)
    public Map<String, Object> userConnectDelete(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
    	
    	//요청 {adress_user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	String user_uid = (String) session.getAttribute("sessionUid");
    	rvo.setUser_uid(user_uid);
    	
    	int action = uservice.adressUserDelete(rvo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
    
//////////////////////////////////////////////// 구매 내역 ////////////////////////////////////////////////

    //구매 상품 내역
    @RequestMapping(value = "/tring_user_userProductList", method = RequestMethod.POST)
    public Map<String, Object> userProductList(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
    	
    	//요청 {user_uid}
    	
    	String user_uid = (String) session.getAttribute("sessionUid");
    	rvo.setUser_uid(user_uid);
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
    
}
