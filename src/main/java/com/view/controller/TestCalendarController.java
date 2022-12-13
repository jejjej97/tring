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
public class TestCalendarController
{
	@Inject
	private CompanyService cpservice;
	@Inject
	private MeetingService dservice;
	//운영
//    private final String API_KEY = "AIzaSyAfWbzbJ0hoFQUiVLfB0QIlqAM_sgV3ATw";
//    private final String CLIENT_ID = "255496301733-g99jl727dntugcr4pvu7n4r30kdkll7h.apps.googleusercontent.com";
//    private final String CLIENT_SECRET= "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";
//    private final String REDIRECT_URI= "http://localhost:8081/googleCalendarOAuth";
	
	//로컬
    private final String API_KEY = "AIzaSyAfWbzbJ0hoFQUiVLfB0QIlqAM_sgV3ATw";
    private final String CLIENT_ID = "255496301733-g99jl727dntugcr4pvu7n4r30kdkll7h.apps.googleusercontent.com";
    private final String CLIENT_SECRET= "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";
    private final String REDIRECT_URI= "http://localhost:8081/googleCalendarOAuth";
    
    private final String GRANT_TYPE= "authorization_code";
    private final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    
	private static final Logger logger = LoggerFactory.getLogger(TestCalendarController.class);
    
	private String accessToken = "";
	
    @RequestMapping(value = "/googleCalendarUrl")
    public String googleCalendarOAuth() {
    	String http = "https://accounts.google.com/o/oauth2/v2/auth?"
    			+ "scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar.readonly%20"
    			+ "&access_type=offline"
    			+ "&include_granted_scopes=true"
    			+ "&response_type=code"
    			+ "&state=state_parameter_passthrough_value"
    			+ "&redirect_uri="+REDIRECT_URI
    			+ "&client_id="+CLIENT_ID;
    	
        return "redirect:"+http;
    }

    @RequestMapping("/googleCalendarOAuth")
    public String receiveAC(@RequestParam("code") String code, Model model){
    	
    	if(accessToken == "") {
	        String accessTokenJsonData = getAccessTokenJsonData(code);
	        JSONObject accessTokenjsonObject = new JSONObject(accessTokenJsonData);
	        
	        accessToken = accessTokenjsonObject.get("access_token").toString();
	        //user accessToken db update
    	}
    	
        // Get CalendarList
        String calendarListJsonData = getCalendarList(accessToken);

        JSONObject calendarListJsonObject = new JSONObject(calendarListJsonData);
        
        System.out.println(calendarListJsonObject);
        
        JSONArray itemArr = (JSONArray) calendarListJsonObject.get("items");

        List<CalendarVO> CalendarList = new ArrayList<CalendarVO>();
        
        //결과 출력
        for(int i=0; i<itemArr.length(); i++){
        	CalendarVO vo = new CalendarVO();
            JSONObject item = (JSONObject)itemArr.get(i);

            vo.setSummary((String)item.get("summary"));
            vo.setCalendarId((String)item.get("id"));
            
            CalendarList.add(i, vo);
        }
        
        model.addAttribute("accessToken",accessToken);
        model.addAttribute("CalendarList",CalendarList);
        
        return "CalendarList";
    }

    public String getAccessTokenJsonData(String code){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", CLIENT_ID);
        params.put("client_secret", CLIENT_SECRET);
        params.put("redirect_uri", REDIRECT_URI);
        params.put("grant_type", GRANT_TYPE);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(TOKEN_URL, params, String.class); // POST 방식으로 전송

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody(); // JSON 데이터 반환
        }
        return "error";
    }

    public String getCalendarList(String accessToken){
        try {
            String jsonData = "";
            String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/users/me/calendarList";
            // URI를 URL객체로 저장
            URL url = new URL(HTTP_REQUEST + "?access_token=" + accessToken);
			
            // 버퍼 데이터(응답 메세지)를 한 줄씩 읽어서 jsonData에 저장
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            
            String line;
            while((line = bf.readLine()) != null){
                jsonData+=line;
//                System.out.println(line);
            }
            return jsonData;

        } catch(Exception e) {
            return "error";
        }
    }

//--------------------------------------------------캘린더 info--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarView")
    public String googleCalendarView(String calendarId, HttpSession session, Model model){

    	String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"/events?timeZone=Asia%2FSeoul&key="+API_KEY;
	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("timeZone", "Asia/Seoul");
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }

	        JSONObject calendarListJsonObject = new JSONObject(result);
	        JSONArray itemArr = (JSONArray) calendarListJsonObject.get("items");

	        List<CalendarVO> Calendar = new ArrayList<CalendarVO>();
	        
	        //결과 출력
	        for(int i=0; i<itemArr.length(); i++){
	        	CalendarVO vo = new CalendarVO();
	        	
	            JSONObject item = (JSONObject)itemArr.get(i);
	            JSONObject startTime = (JSONObject) item.get("start");
	            JSONObject endTime = (JSONObject) item.get("end");
	            
	            if(item.has("summary")) {
	            	vo.setSummary((String)item.get("summary"));
	            }
//	            if(item.has("description")) {
//	            	vo.setDescription((String)item.get("description"));
//	            }
            	vo.setEventId((String)item.get("id"));
            	vo.setStartTime((String)startTime.get("dateTime"));
            	vo.setEndTime((String)endTime.get("dateTime"));

	            Calendar.add(i, vo);
	        }
	        
	        model.addAttribute("calendarId",calendarId);
	        model.addAttribute("Calendar",Calendar);
	        
	        return "CalendarView";
	        
        } catch(Exception e) {
            return "error";
        }

    }

//--------------------------------------------------캘린더 생성--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarInsert")
    public String googleCalendarInsert(HttpSession session){

        String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars?key="+API_KEY;
	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        String ParamData = "{\"summary\" : \""+"회의실록"+"\"}";
	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true);
	        try (OutputStream os = conn.getOutputStream()){
				byte request_data[] = ParamData.getBytes("utf-8");
				os.write(request_data);
				os.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }

        } catch(Exception e) {
            return "error";
        }
		return "redirect:googleCalendarUrl";
    }

//--------------------------------------------------캘린더 삭제--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarDelete")
    public String googleCalendarDelete(String calendarId, HttpSession session){
    	
    	String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"?key="+API_KEY;
	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestMethod("DELETE");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }

        } catch(Exception e) {
            return "error";
        }
    	
    	return "redirect:googleCalendarUrl";
    }
    

//--------------------------------------------------캘린더 이벤트 생성--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarInsertEvent")
    public String googleCalendarInsertEvent(String calendarId, String summary, String startDate, String endDate, HttpSession session){
    	
    	String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"/events?key="+API_KEY;

	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        String ParamData = "{"
	        		+ "\"summary\": \""+summary+"\","
	        		+ "\"end\": {\"dateTime\": \""+endDate.trim()+"\","
	        		+ "\"timeZone\": \"Asia/Seoul\"},"
	        		+ "\"start\": {\"dateTime\": \""+startDate.trim()+"\","
	        		+ "\"timeZone\": \"Asia/Seoul\"}"
	        		+ "}";
	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true); 
	        try (OutputStream os = conn.getOutputStream()){
				byte request_data[] = ParamData.getBytes("utf-8");
				os.write(request_data);
				os.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }

        } catch(Exception e) {
            return "error";
        }
		return "redirect:googleCalendarUrl";
    }
    
//--------------------------------------------------캘린더 이벤트 삭제--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarDeleteEvent")
    public String googleCalendarDeleteEvent(String calendarId, String eventId, HttpSession session){
    	
    	String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"/events/"+eventId+"?key="+API_KEY;
	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestMethod("DELETE");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }

        } catch(Exception e) {
            return "error";
        }
    	
    	return "redirect:googleCalendarUrl";
    }

//--------------------------------------------------캘린더 이벤트 수정--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarUpdateEvent")
    public String googleCalendarUpdateEvent(String calendarId, String eventId,  String summary, String startDate, String endDate, HttpSession session){
    	
    	String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"/events/"+eventId+"?key="+API_KEY;

	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        String ParamData = "{"
	        		+ "\"summary\": \""+"수정"+"\","
	        		+ "\"end\": {\"dateTime\": \""+"2022-10-05T18:30:00"+"\","
	        		+ "\"timeZone\": \"Asia/Seoul\"},"
	        		+ "\"start\": {\"dateTime\": \""+"2022-10-05T17:00:00"+"\","
	        		+ "\"timeZone\": \"Asia/Seoul\"}"
	        		+ "}";
	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestMethod("PUT");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true); 
	        try (OutputStream os = conn.getOutputStream()){
				byte request_data[] = ParamData.getBytes("utf-8");
				os.write(request_data);
				os.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }

        } catch(Exception e) {
            return "error";
        }
		return "redirect:googleCalendarUrl";
    }

//--------------------------------------------------캘린더 이벤트 참여자 목록--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarPersonList")
    public String googleCalendarPersonList(String calendarId, Model model, HttpSession session){
    	
    	String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"/acl?key="+API_KEY;
	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }
	        

	        JSONObject calendarListJsonObject = new JSONObject(result);
	        JSONArray itemArr = (JSONArray) calendarListJsonObject.get("items");
	        
	        List<CalendarVO> Calendar = new ArrayList<CalendarVO>();
	        
	        //결과 출력
	        for(int i=0; i<itemArr.length(); i++){
	        	CalendarVO vo = new CalendarVO();
	        	
	            JSONObject item = (JSONObject)itemArr.get(i);
	            JSONObject scope = (JSONObject) item.get("scope");

	            vo.setRule((String)item.get("role"));
	            vo.setCalendarUserId((String)scope.get("value"));

	            Calendar.add(i, vo);
	        }
	        
	        model.addAttribute("calendarId",calendarId);
	        model.addAttribute("Calendar",Calendar);
	        
	        return "CalendarPerson";

        } catch(Exception e) {
            return "error";
        }
	    
    }
    
//--------------------------------------------------캘린더 이벤트 참여자 추가--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarPersonAdd")
    public String googleCalendarPersonAdd(String calendarId, String email, HttpSession session){
    	
    	String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"/acl?key="+API_KEY;

	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        String ParamData = "{"
	        		+ "\"role\": \""+"reader"+"\","
	        		+ "\"scope\": {\"type\": \""+"user"+"\","
	        		+ "\"value\": \""+email+"\"}"
	        		+ "}";
	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true); 
	        try (OutputStream os = conn.getOutputStream()){
				byte request_data[] = ParamData.getBytes("utf-8");
				os.write(request_data);
				os.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }

        } catch(Exception e) {
            return "error";
        }
		return "redirect:googleCalendarUrl";
    }

//--------------------------------------------------캘린더 이벤트 참여자 삭제--------------------------------------------------------------
    @RequestMapping(value = "/googleCalendarPersonDel")
    public String googleCalendarPersonDel(String calendarId, String calendarUserId, HttpSession session){

    	String HTTP_REQUEST = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"/acl/user:"+calendarUserId+"?key="+API_KEY;
	    try {
	        URL url = new URL(HTTP_REQUEST);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestMethod("DELETE");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Accept", "application/json");
	        
	        conn.connect();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
//	            System.out.println(line);
	        }

        } catch(Exception e) {
            return "error";
        }
		return "redirect:googleCalendarUrl";
    }
}
