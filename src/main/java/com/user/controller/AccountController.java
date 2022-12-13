package com.user.controller;

import com.company.service.CompanyService;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;
import com.user.service.AccountService;
import com.user.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@RestController
public class AccountController
{

	@Inject
	private AccountService aservice;
	@Inject
	private UserService uservice;
	@Inject
	private CompanyService cpservice;
	@Inject
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private JavaMailSender mailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
//////////////////////////////////////////////// 회원 가입 ////////////////////////////////////////////////
	
	//기업 회원 가입
	@RequestMapping(value = "/tring_account_companyRegist", method = RequestMethod.POST)
    public Map<String, Object> companyRegist(@RequestBody ResultVO rvo) throws Exception {
		
		//요청 {user_email, company_code, user_password, department_code, user_position, user_name, user_phone}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	String uid = UUID.randomUUID().toString().replace("-","");
    	rvo.setUser_uid(uid);
    	
    	//패스워드 암호화
    	rvo.setUser_password(bcryptPasswordEncoder.encode(rvo.getUser_password()));
    	
    	int action = cpservice.approvalInsert(rvo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
		return result;
	}
	
//////////////////////////////////////////////// 로그인 ////////////////////////////////////////////////

//------------------- 기업 로그인-------------------

	@RequestMapping(value = "/tring_account_companyLogin", method = RequestMethod.POST)
    public Map<String, Object> companyLogin(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
		
		//요청 {user_email, user_password}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();

    	ResultVO mvo = aservice.companyLogin(rvo);

    	if(mvo != null && bcryptPasswordEncoder.matches(rvo.getUser_password(), mvo.getUser_password())) {
    		
    		int action = aservice.userRecordLog(mvo.getUser_uid());
    		
    		if(action == 1) {
    			session.setAttribute("sessionUid", mvo.getUser_uid());
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
	
//------------------- 구글 로그인-------------------

	//구글 로그인 페이지 url
	@RequestMapping(value = "/tring_account_googleLogin", method = RequestMethod.POST)
    public Map<String, Object> googleLogin(@RequestBody ResultVO rvo, HttpSession session) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
		return result;
	}
	
	//구글 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/googleCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> googleCallback(RedirectAttributes rttr, Model model, @RequestParam String code, @RequestParam String state, HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}

//------------------- 카카오 로그인-------------------

	//카카오 로그인 페이지 url
	@RequestMapping(value = "/tring_account_kakaoLogin", method = RequestMethod.POST)
    public Map<String, Object> kakaoLogin(@RequestBody ResultVO rvo) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
	//카카오 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/kakaoCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> kakaoCallback(RedirectAttributes rttr, Model model, @RequestParam String code, @RequestParam String state, HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
//------------------- 네이버 로그인-------------------
	
	//네이버 로그인 페이지 url
	@RequestMapping(value = "/tring_account_naverLogin", method = RequestMethod.POST)
    public Map<String, Object> naverLogin(@RequestBody ResultVO rvo) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/naverCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> naverCallback(RedirectAttributes rttr, Model model, @RequestParam String code, @RequestParam String state, HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}

//------------------- 애플 로그인-------------------

	//구글 로그인 페이지 url
	@RequestMapping(value = "/tring_account_aplleLogin", method = RequestMethod.POST)
    public Map<String, Object> aplleLogin(@RequestBody ResultVO rvo) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
	//구글 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/aplleCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> aplleCallback(RedirectAttributes rttr, Model model, @RequestParam String code, @RequestParam String state, HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
//////////////////////////////////////////////// 비밀 번호 찾기 ////////////////////////////////////////////////

	//인증 번호 전송
	@RequestMapping(value = "/tring_account_verificationSend", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> verificationSend(@RequestBody ResultVO rvo, HttpServletResponse response, HttpSession session) throws Exception {
		
		//요청 {user_email}
    	
		final Map<String, Object> result = new HashMap<String, Object>();
		try {
			//인증번호 생성
			String randCode = UUID.randomUUID().toString().replace("-","");
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
		    // MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		    logger.info("Message {}", mimeMessage);
		    
		    //test용 메일 내용
//		    messageHelper.setFrom(""); // 보내는사람 이메일 여기선 google 메일서버 사용하는 아이디를 작성하면됨

		    messageHelper.setTo(rvo.getUser_email()); // 받는사람 이메일

		      /**         
             * html 템플릿으로 보낼거면 true
             * plaintext로 보낼거면 false
             */
		    messageHelper.setSubject("TRING PASSWORD 인증 번호"); // 메일제목
		    messageHelper.setText(
		    		System.lineSeparator()
		    		+ randCode
		    		+ System.lineSeparator()
		    		, false);
		    
		    mailSender.send(mimeMessage);
		    
		    //쿠키
		    Cookie cookie = new Cookie("verificationCode", randCode);
			cookie.setMaxAge(1*30);
			response.addCookie(cookie);
			
		    result.put("resultMsg", "success.");
		    
		} catch (Exception e) {
			e.printStackTrace();
			result.put("resultMsg", "error : "+e);
		}
		
		return result;
	}
	
	//인증 번호 확인
	@RequestMapping(value = "/tring_account_verificationCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> verificationCheck(@RequestBody ResultVO rvo, HttpServletRequest request, HttpSession session) throws Exception {
		
		//요청 {user_email, verification_code}
		
    	final Map<String, Object> result = new HashMap<String, Object>();

		Cookie[] cookies = request.getCookies(); // 모든 쿠키 가져오기
	    if(cookies!=null){
	        for (Cookie c : cookies) {
	            String name = c.getName(); // 쿠키 이름 가져오기
	            String value = c.getValue(); // 쿠키 값 가져오기
	            
	            if (name.equals("verificationCode")) {
	            	
//	            	System.out.println(rvo.getVerification_code());
//		            System.out.println(value);
	            	
	            	if(rvo.getVerification_code().equals(value)) {
	            		//세션 켜기
	            		ResultVO mvo = aservice.companyLogin(rvo);
	            		session.setAttribute("sessionUid", mvo.getUser_uid());
	            		result.put("resultMsg", "check in.");
	            	}else {
	            		result.put("resultMsg", "code is wrong.");
	            	}
	            }else {
	            	result.put("resultMsg", "time out.");
	            }
	        }
	    }

		return result;
	}
	
//////////////////////////////////////////////// 로그 아웃 ////////////////////////////////////////////////
	
	//로그 아웃
	@RequestMapping(value = "/tring_account_logout", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> logout(HttpSession session) throws Exception {
		
		//요청 {}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
		
    	session.invalidate();
    	
		return result;
	}
}
