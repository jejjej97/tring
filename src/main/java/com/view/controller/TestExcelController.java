package com.view.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tring.domain.CalendarVO;
import com.tring.domain.MemberVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class TestExcelController
{

	private static final Logger logger = LoggerFactory.getLogger(TestExcelController.class);
	
    @RequestMapping(value = "/excelHome")
    public String excelHome(HttpServletRequest request, Model model) {
    	
        return "excelHome";
    }

//    @RequestMapping(value = "/excelRead", method = RequestMethod.POST)
//    public String excelRead(HttpServletRequest request, Model model, HttpServletResponse response,
//    		@RequestParam(name = "excelFile", required = false) MultipartFile mfile) throws IOException {
//    	
//    	System.out.println("excel ??????");
//    	String savePath = request.getSession().getServletContext().getRealPath("resources/excel/excelData");
//		
//    	String company = "";
//    	String deptName = "";
//    	List<MemberVO> list = new ArrayList<MemberVO>();
//    	
//		try {
//			
//			//?????? ??????
//			if (!mfile.isEmpty()) {
//				String fileName = mfile.getOriginalFilename();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
//				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
////				File originFile = new File(savePath + "/" + fileName);
//				File renameFile = new File(savePath + "/" + renameFileName);
//				mfile.transferTo(renameFile);
//				
//				//?????? ??????
//				FileInputStream file = new FileInputStream(savePath + "/" + renameFileName);
//		        XSSFWorkbook workbook = new XSSFWorkbook(file);
//		
//		        int rowNo = 0;
//		        int cellIndex = 0;
//		        
//		        XSSFSheet sheet = workbook.getSheetAt(0); // 0 ?????? ????????? ???????????? 
//		        										  // ?????? ????????? ????????? ??? ?????? for ?????? ???????????? ????????? ????????? ????????????
//		        int rows = sheet.getPhysicalNumberOfRows()+1; // ???????????? ????????? ?????? Row?????? ????????????
//		        System.out.println("rows:"+rows);
//		        for(rowNo = 0; rowNo < rows; rowNo++){
//		        	
//		        	MemberVO vo = new MemberVO();
//		        	
//		            XSSFRow row = sheet.getRow(rowNo);
//		            if(row != null){
//		                int cells = row.getPhysicalNumberOfCells(); // ?????? Row??? ???????????? ????????? ?????? ?????? ????????????
//		                for(cellIndex = 0; cellIndex <= cells; cellIndex++){  
//		                    XSSFCell cell = row.getCell(cellIndex); // ?????? ?????? ????????????	        
//		                    String value = "";	                    
//		                    if(cell == null){ // ??? ??? ?????? 
//		                        continue;
//		                    }else{
//		                        // ?????? ?????? ????????? ?????????
//		                        switch (cell.getCellType()){
//		                        case XSSFCell.CELL_TYPE_FORMULA:
//		                            value = cell.getCellFormula();
//		                            break;
//		                        case XSSFCell.CELL_TYPE_NUMERIC:
//		                            value = cell.getNumericCellValue() + "";
//		                            break;
//		                        case XSSFCell.CELL_TYPE_STRING:
//		                            value = cell.getStringCellValue() + "";
//		                            break;
//		                        case XSSFCell.CELL_TYPE_BLANK:
//		                            value = cell.getBooleanCellValue() + "";
//		                            break;
//		                        case XSSFCell.CELL_TYPE_ERROR:
//		                            value = cell.getErrorCellValue() + "";
//		                            break;
//		                        }
//		                    }
////		                    System.out.println( (rowNo+1) + "??? ??? : " + (cellIndex+1) + "??? ??? ??????: " + value);
//		                    
//		                    //?????????
//		                    if(rowNo ==1 && cellIndex==1){
//		                    	company = value;
//		                    }
//		                    //?????????
//		                    if(rowNo ==1 && cellIndex==4){
//		                    	deptName = value;
//		                    }
//		                    //?????? ??????
//		                    if(rowNo >= 4){
//		                    	if(cellIndex == 0) {
//		                    		Double deptno = Double.parseDouble(value);
////		                    		vo.setDept_no(deptno.intValue());
//		                    	}else if(cellIndex == 1) {
//		                    		vo.setJob(value);
//		                    	}else if(cellIndex == 2) {
//		                    		vo.setNickName(value);
//		                    	}else if(cellIndex == 3) {
//		                    		Double birth1 = Double.parseDouble(value);
//		                    		int birth2 = birth1.intValue();
//		                    		vo.setBirth(birth2+"");
//		                    	}else if(cellIndex == 4) {
//		                    		vo.setPhone(value);
//		                    	}else if(cellIndex == 5) {
//		                    		vo.setEmail(value);
//		                    		
//		                    		vo.setCompany_name(company);
//		    		                vo.setDept_name(deptName);
//		    		                list.add(vo);
//		    		                //?????? DB ????????????
//		                    	}
//		                    }
//		                }
//		            }
//		        }
//			}
//			
//			for(int i=0; i<list.size(); i++) {
//				
//				System.out.println(list.get(i).getCompany_name() +"/"+ list.get(i).getDept_name() +"/"+ list.get(i).getJob() +"/"+ list.get(i).getNickName()
//						+"/"+ list.get(i).getBirth() +"/"+ list.get(i).getPhone() +"/"+ list.get(i).getEmail());
//			}
//			
//			model.addAttribute("list",list);
//			
//			return "excelView";
//			
//		} catch (Exception e) {
//			PrintWriter writer = response.getWriter();    
//            writer.println("<script type='text/javascript'>");
//            writer.println("window.onload = function(){alert('?????? ????????? ????????????.'); location.href='/';}");
//            writer.println("</script>"); 
//            writer.flush(); 
//			return null;
//		}
//
//    }
}
