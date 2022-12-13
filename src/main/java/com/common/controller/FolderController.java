package com.common.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.service.FolderService;
import com.tring.domain.FolderVO;



/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@RestController
public class FolderController
{

	@Inject
	private FolderService fservice;
	
	private static final Logger logger = LoggerFactory.getLogger(FolderController.class);

//////////////////////////////////////////////// 폴더 구조 ////////////////////////////////////////////////
	
	//최상위 폴더 리스트 출력
    @RequestMapping(value = "/tring_common_folderList", method = RequestMethod.POST)
    public Map<String, Object> folderList(@RequestBody FolderVO vo) throws Exception {
    	
    	//요청 {user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	vo.setFolder_level(0);
    	
    	List<FolderVO> list = new ArrayList<FolderVO>();
    	
    	if(vo.getRoute() == "meeting") {
    		list = fservice.userFolderList(vo);
    	}else if(vo.getRoute() == "memo") {
    		list = fservice.userFolderList(vo);
    	}else if(vo.getRoute() == "department") {
    		list = fservice.userFolderList(vo);
    	}
    	
    	result.put("folder_list", list);
    	
        return result;
    }

    //하위 폴더 및 데이터 리스트 출력
    @RequestMapping(value = "/tring_common_folderSelect", method = RequestMethod.POST)
    public Map<String, Object> folderSelect(@RequestBody FolderVO vo) throws Exception {
    	
    	//요청 {user_uid, upfolder_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();

    	List<FolderVO> list = fservice.userFolderList(vo);
    	
    	result.put("folder_downList", list);
    	
    	List<FolderVO> meeting_uid = fservice.userFolderDataList(vo.getFolder_highrank());

    	ArrayList<Map<String, Object>> meeting_array = new ArrayList<Map<String, Object>>();
    	for(int i=0; i<meeting_uid.size(); i++) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		
    		//meeting_uid, meeting_name
    		map.put("meeting_uid", meeting_uid.get(i).getMeeting_uid());
    		meeting_array.add(map);
    	}
    	
    	result.put("meeting_list", meeting_array);
    	
    	return result;
    }
    
    //폴더 생성
    @RequestMapping(value = "/tring_common_folderInsert", method = RequestMethod.POST)
    public Map<String, Object> folderInsert(@RequestBody FolderVO vo) throws Exception {
    	
    	//요청 {folder_name, upfolder_uid, user_uid, folder_level}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();

    	String uid = UUID.randomUUID().toString().replace("-","");
    	vo.setFolder_uid(uid);
    	if(vo.getFolder_level()+1 >= 5) {
    		result.put("resultMsg", "fail(folder level check please).");
    		return result;
    	}else {
    		vo.setFolder_level(vo.getFolder_level()+1);
    	}
    	
    	int action = fservice.userFolderInsert(vo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
    
    //폴더 이름 수정
    @RequestMapping(value = "/tring_common_folderNameUpdate", method = RequestMethod.POST)
    public Map<String, Object> folderNameUpdate(@RequestBody FolderVO vo) throws Exception {
    	
    	//요청 {folder_uid, folder_name}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();

    	int action = fservice.userFolderNameUpdate(vo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
    
    //폴더 이동
    @RequestMapping(value = "/tring_common_folderMove", method = RequestMethod.POST)
    public Map<String, Object> folderMove(@RequestBody FolderVO vo) throws Exception {
    	
    	//요청 {folder_uid, upfolder_uid, folder_level}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	int action = 0;
    	
    	if(vo.getFolder_level()+1 <= 4) {
    	
    		int depth = 1;
    		
	    	vo.setFolder_level(vo.getFolder_level()+1);
	    	action = fservice.userFolderMove(vo);
	    	
	    	//깊이 추출
	    	List<String> folderDown_uid_depth2 = fservice.userFolderDown_uid(vo.getFolder_uid());
	    	for(int a=0; a<folderDown_uid_depth2.size(); a++) {
	    		List<String> folderDown_uid_depth3 = fservice.userFolderDown_uid(folderDown_uid_depth2.get(a));
	    		depth = 2;
	    		for(int b=0; b<folderDown_uid_depth3.size(); b++) {
	        		List<String> folderDown_uid_depth4 = fservice.userFolderDown_uid(folderDown_uid_depth3.get(b));
	        		depth = 3;
	        		for(int c=0; c<folderDown_uid_depth4.size(); c++) {
	        			depth = 4;
	            	}
	        	}
	    	}
	    	
	    	//하위폴더 level 변경
	    	if(depth + 1 <= 4) {
		    	List<String> folderDown_uid_level2 = fservice.userFolderDown_uid(vo.getFolder_uid());
		    	for(int a=0; a<folderDown_uid_level2.size(); a++) {
		    		action = fservice.userFolderMove_level(folderDown_uid_level2.get(a), vo.getFolder_level()+1);
		    		
		    		List<String> folderDown_uid_level3 = fservice.userFolderDown_uid(folderDown_uid_level2.get(a));
		        	for(int b=0; b<folderDown_uid_level3.size(); b++) {
		        		action = fservice.userFolderMove_level(folderDown_uid_level3.get(b), vo.getFolder_level()+2);
		        		
		        		List<String> folderDown_uid_level4 = fservice.userFolderDown_uid(folderDown_uid_level3.get(b));
		            	for(int c=0; c<folderDown_uid_level4.size(); c++) {
		            		action = fservice.userFolderMove_level(folderDown_uid_level4.get(c), vo.getFolder_level()+3);
		            	}
		        	}
		    	}
	    	}else {
	    		result.put("resultMsg", "fail(folder level check please).");
	    		return result;
	    	}
    	}else {
    		result.put("resultMsg", "fail(folder level check please).");
    		return result;
    	}
    	

    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
    
    //폴더 삭제
    @RequestMapping(value = "/tring_common_folderDelete", method = RequestMethod.POST)
    public Map<String, Object> folderDelete(@RequestBody FolderVO vo) throws Exception {
    	
    	//요청 {folder_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	int action = 0;
    	
    	action = fservice.userFolderDelete(vo.getFolder_uid());
    	
    	List<String> folderDown_uid_level2 = fservice.userFolderDown_uid(vo.getFolder_uid());
    	for(int a=0; a<folderDown_uid_level2.size(); a++) {
    		action = fservice.userFolderDelete(folderDown_uid_level2.get(a));
    		
    		List<String> folderDown_uid_level3 = fservice.userFolderDown_uid(folderDown_uid_level2.get(a));
        	for(int b=0; b<folderDown_uid_level3.size(); b++) {
        		action = fservice.userFolderDelete(folderDown_uid_level3.get(b));
        		
        		List<String> folderDown_uid_level4 = fservice.userFolderDown_uid(folderDown_uid_level3.get(b));
            	for(int c=0; c<folderDown_uid_level4.size(); c++) {
            		action = fservice.userFolderDelete(folderDown_uid_level4.get(c));
            	}
        	}
    	}

    	if(action >= 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
    
////////////////////////////////////////////////폴더 데이터 구조 ////////////////////////////////////////////////
    
    //폴더 데이터 생성
    @RequestMapping(value = "/tring_common_folderDataInsert", method = RequestMethod.POST)
    public Map<String, Object> folderDataInsert(@RequestBody FolderVO vo) throws Exception {

    	//요청 {folder_uid, meeting_uid, meeting_name, user_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	    	
    	//동일 미팅 검증
    	List<String> meeting_uid = fservice.userFolderDataUid_check(vo.getUser_uid());
    	for(int i=0; i<meeting_uid.size(); i++) {
    		if(meeting_uid.get(i).equals(vo.getMeeting_uid())) {
    			result.put("resultMsg", "fail(The same meeting_uid).");
    			return result;
    		}
    	}
    	
    	int action = fservice.userFolderDataInsert(vo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }

    //폴더 데이터 이동
    @RequestMapping(value = "/tring_common_folderDataMove", method = RequestMethod.POST)
    public Map<String, Object> folderDataMove(@RequestBody FolderVO vo) throws Exception {

    	//요청 {folder_uid, meeting_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	int action = fservice.userFolderDataMove(vo);
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
    
    //폴더 데이터 삭제
    @RequestMapping(value = "/tring_common_folderDataDelete", method = RequestMethod.POST)
    public Map<String, Object> folderDataDelete(@RequestBody FolderVO vo) throws Exception {

    	//요청 {meeting_uid}
    	
    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	int action = fservice.userFolderDataDelete(vo.getMeeting_uid());
    	
    	if(action == 1) {
    		result.put("resultMsg", "success.");
    	}else {
    		result.put("resultMsg", "error.");
    	}
    	
    	return result;
    }
}
