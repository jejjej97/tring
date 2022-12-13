package com.view.controller;

import com.company.service.CompanyService;
import com.data.service.MeetingService;
import com.tring.domain.CalendarVO;
import com.tring.domain.MeetingVO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


/**
 * Handles requests for the application home page.
 */
@Controller
public class TestViewController
{
	
	@Inject
	private MeetingService dservice;
	
	@RequestMapping(value = "/meeting")
    public String meeting(Model model) throws Exception {
    	
        return "meeting";
    }
	
	@ResponseBody
	@RequestMapping(value = "/meeting_content", method = { RequestMethod.POST })
    public String meeting_content(Model model , 
    		@RequestParam String meeting_content) throws Exception {
		
		System.out.println(meeting_content);
		
		MeetingVO mvo = new MeetingVO();
		
		String meeting_uid = UUID.randomUUID().toString().replace("-","");
		
		mvo.setMeeting_uid(meeting_uid);
		mvo.setMeeting_content(meeting_content);
		
		dservice.meetingInsert(mvo);

        return "success";
    }
	
    @RequestMapping(value = "/")
    public String home(Model model) throws Exception {
    	
        return "home";
    }

}
