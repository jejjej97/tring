package com.view.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.FolderService;
import com.data.service.MeetingService;
import com.tring.domain.FolderVO;




/**
 * Handles requests for the application home page.
 */
@Controller
public class TestFolderController
{
	@Inject
	private MeetingService mservice;
	@Inject
	private FolderService fservice;
	private static final Logger logger = LoggerFactory.getLogger(TestFolderController.class);
	
    @RequestMapping(value = "/folderHome")
    public String folderHome(HttpServletRequest request, FolderVO vo, Model model) throws Exception {
    	
    	//요청 {dept_uid, company_code, access}
    	
		vo.setUser_uid("beginIT");

		vo.setFolder_level(1);
    	List<FolderVO> list = fservice.userFolderList(vo);
    	model.addAttribute("folderList", list);
    	
        return "folderHome";
    }

    @ResponseBody
    @RequestMapping(value = "/folderSelect2")
    public JSONArray folderSelect2(HttpServletRequest request, FolderVO vo, Model model) throws Exception {
    	
    	JSONArray jarr = new JSONArray();
    	
    	List<FolderVO> list = fservice.userFolderList(vo);
    	jarr.add(list);
    	
    	List<FolderVO> meeting_uid = fservice.userFolderDataList(vo.getFolder_highrank());
    	
    	ArrayList<Map<String, Object>> meeting_array = new ArrayList<Map<String, Object>>();
    	for(int i=0; i<meeting_uid.size(); i++) {
    		Map<String, Object> map = new HashMap<String, Object>();

    		//meeting_uid, meeting_name
    		map.put("meeting_uid", meeting_uid.get(i).getMeeting_uid());
    		meeting_array.add(map);
    	}
    	jarr.add(meeting_array);
    	
    	return jarr;
    }
    
    @ResponseBody
    @RequestMapping(value = "/folderInsert2")
    public JSONArray folderInsert2(HttpServletResponse response, HttpServletRequest request, FolderVO vo, Model model) throws Exception {
    	JSONArray jarr = new JSONArray();
    	
//    	System.out.println("그룹:"+vo.getGroup_uid());
//    	System.out.println("레벨:"+vo.getFolder_level());
//    	System.out.println("상위폴더:"+vo.getUpfolder_uid());
//    	System.out.println("사용자:"+vo.getFolder_use());
//    	
//    	if(vo.getUpfolder_uid() == null) {
//    		String groupUid = UUID.randomUUID().toString().replace("-","");
//        	vo.setGroup_uid(groupUid);
//    	}else {
//    		vo.setFolder_level(vo.getFolder_level()+1);
//    	}
    	
    	String uid = UUID.randomUUID().toString().replace("-","");
    	vo.setFolder_uid(uid);
    	if(vo.getFolder_level()+1 >= 5) {
            return null;
    	}else {
    		vo.setFolder_level(vo.getFolder_level()+1);
    	}
    	
    	int result = fservice.userFolderInsert(vo);
    	
    	if(result == 1) {
    		vo.getFolder_highrank();

        	jarr.add(vo);
    		
    		return jarr;
    	}else {
    		return null;
    	}
    }
    
    @ResponseBody
    @RequestMapping(value = "/folderDelete2")
    public String folderDelete2(HttpServletRequest request, FolderVO vo, Model model) throws Exception {
    	
    	fservice.userFolderDelete(vo.getFolder_uid());
    	
    	List<String> folderDown_uid_level1 = fservice.userFolderDown_uid(vo.getFolder_uid());
    	
    	for(int a=0; a<folderDown_uid_level1.size(); a++) {
    		fservice.userFolderDelete(folderDown_uid_level1.get(a));
    		
//    		System.out.println("leve2");
//    		System.out.println(folderDown_uid_level1.get(a));
//    		System.out.println(folderDown_uid_level1.size());
    		
    		List<String> folderDown_uid_level2 = fservice.userFolderDown_uid(folderDown_uid_level1.get(a));
        	for(int b=0; b<folderDown_uid_level2.size(); b++) {
        		fservice.userFolderDelete(folderDown_uid_level2.get(b));
        		
        		List<String> folderDown_uid_level3 = fservice.userFolderDown_uid(folderDown_uid_level2.get(b));
            	for(int c=0; c<folderDown_uid_level3.size(); c++) {
            		fservice.userFolderDelete(folderDown_uid_level3.get(c));
            	}
        	}
    	}
    	
    	return "success";
    }
}
