package com.company.controller;

import com.common.service.FolderService;
import com.company.service.CompanyService;
import com.tring.domain.ApprovalVO;
import com.tring.domain.CompanyVO;
import com.tring.domain.DeptVO;
import com.tring.domain.FolderVO;
import com.tring.domain.MemberVO;
import com.tring.domain.ResultVO;
import com.user.service.UserService;

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



/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@RestController
public class CompanyController
{
	@Inject
	private UserService uservice;
	@Inject
	private CompanyService cpservice;
	@Inject
	private FolderService fservice;
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
//////////////////////////////////////////////// 회사 정보 ////////////////////////////////////////////////
	
	//회사 상세 정보 출력
	@RequestMapping(value = "/tring_company_companyDetail", method = RequestMethod.POST)
	public Map<String, Object> companyDetail(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {company_code}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		CompanyVO vo = cpservice.companyDetail(rvo.getCompany_code());
		
		result.put("company", vo);
		
		return result;
	}
	
	//회사 정보 수정
	@RequestMapping(value = "/tring_company_companyUpdate", method = RequestMethod.POST)
	public Map<String, Object> companyUpdate(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {company_code, company_name, company_email, company_phone, company_fax}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		int action = cpservice.companyUpdate(rvo);
		
		if(action == 1) {
			result.put("resultMsg", "success.");
		}else {
			result.put("resultMsg", "error.");
		}
		
		return result;
	}
	
	//회사 로고 수정
	@RequestMapping(value = "/tring_company_companyLogoUpdate", method = RequestMethod.POST)
	public Map<String, Object> companyLogoUpdate(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {company_code, company_logo}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		int action = cpservice.companyLogoUpdate(rvo);
		
		if(action == 1) {
			result.put("resultMsg", "success.");
		}else {
			result.put("resultMsg", "error.");
		}
		
		return result;
	}

	
//////////////////////////////////////////////// 부서 정보 ////////////////////////////////////////////////

	//부서 상세 정보 출력
	@RequestMapping(value = "/tring_company_deptDetail", method = RequestMethod.POST)
	public Map<String, Object> dept_view(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {department_code}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		DeptVO vo = cpservice.deptDetail(rvo.getDepartment_code());
		
		//해당부서 멤버
		List<MemberVO> member = cpservice.deptMemberList(rvo.getDepartment_code());
		vo.setDepartment_member(member);
		
		result.put("department", vo);
		
		return result;
	}
	
	//부서 생성
	@RequestMapping(value = "/tring_company_deptInsert", method = RequestMethod.POST)
	public Map<String, Object> deptInsert(@RequestBody ResultVO rvo) throws Exception {
		
		//요청 {company_code, department_name, department_mail, department_phone, add_user_uid_list(리스트)}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		String department_code = UUID.randomUUID().toString().replace("-","");
		rvo.setDepartment_code(department_code);
		
		int action = cpservice.deptInsert(rvo);
		
		if(action == 1) {
			result.put("resultMsg", "success.");
		}else {
			result.put("resultMsg", "error.");
		}
		
		//부서원 추가
		for(int i=0; i<rvo.getAdd_user_uid_list().size(); i++) {
			int action2 = uservice.userDeptUpdate(department_code, rvo.getAdd_user_uid_list().get(i));
			
			if(action2 == 1) {
				result.put("resultMsg2", "success.");
			}else {
				result.put("resultMsg2", "error.");
				return result;
			}
		}
		
		return result;
	}
	
	//부서 수정
	@RequestMapping(value = "/tring_company_deptUpdate", method = RequestMethod.POST)
	public Map<String, Object> deptUpdate(@RequestBody ResultVO rvo) throws Exception {
		
		//요청 {department_code, department_name, department_mail, department_phone, add_user_uid_list(리스트),  del_user_uid_list(리스트)}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		int action = cpservice.deptUpdate(rvo);
		
		if(action == 1) {
			result.put("resultMsg", "success.");
		}else {
			result.put("resultMsg", "error.");
		}
		
		//부서원 변경
			//부서원 제외
		for(int i=0; i<rvo.getDel_user_uid_list().size(); i++) {
			int action2 = uservice.userDeptUpdate(null, rvo.getDel_user_uid_list().get(i));
			
			if(action2 == 1) {
				result.put("resultMsg2", "success.");
			}else {
				result.put("resultMsg2", "error.");
				return result;
			}
		}
			//부서원 추가
		for(int i=0; i<rvo.getAdd_user_uid_list().size(); i++) {
			int action2 = uservice.userDeptUpdate(rvo.getDepartment_code(), rvo.getAdd_user_uid_list().get(i));
			
			if(action2 == 1) {
				result.put("resultMsg3", "success.");
			}else {
				result.put("resultMsg3", "error.");
				return result;
			}
		}

		return result;
	}
	
	//부서 삭제
	@RequestMapping(value = "/tring_company_deptDelete", method = RequestMethod.POST)
	public Map<String, Object> deptDelete(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {department_code}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		int action = cpservice.deptDelete(rvo.getDepartment_code());
		
		if(action == 1) {
			result.put("resultMsg", "success.");
		}else {
			result.put("resultMsg", "error.");
		}
		
		return result;
	}

//////////////////////////////////////////////// 사원 정보 ////////////////////////////////////////////////
	//사원 상세 정보 출력
	@RequestMapping(value = "/tring_company_employeeDetail", method = RequestMethod.POST)
	public Map<String, Object> employeeDetail(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {user_uid}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		rvo.setDepartment_code(null);
		
		MemberVO mvo = uservice.userInfo(rvo.getUser_uid());
		DeptVO dvo = cpservice.deptDetail(mvo.getDepartment_code());

		if(mvo != null && dvo != null) {
			result.put("user_uid", mvo.getUser_uid());
			result.put("user_name", mvo.getUser_name());
			result.put("user_email", mvo.getUser_email());
			result.put("user_phone", mvo.getUser_phone());
			result.put("department_name", dvo.getDepartment_name());
			result.put("department_code", dvo.getDepartment_code());
			result.put("user_position", mvo.getUser_position());
			
			//권한
			List<String> employeeAuthority = cpservice.employeeAuthorityList(rvo.getUser_uid());
			for(int i=0; i<employeeAuthority.size(); i++) {
				DeptVO dvo2 = cpservice.deptDetail(employeeAuthority.get(i));
				employeeAuthority.set(i, dvo2.getDepartment_name());
			}
			result.put("employeeAuthority", employeeAuthority);
		}
		
		return result;
	}
		
	//사원 정보 수정
	@RequestMapping(value = "/tring_company_employeeUpdate", method = RequestMethod.POST)
	public Map<String, Object> employeeUpdate(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {user_uid, department_code, user_position, add_department_code_list}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		int action = cpservice.employeeUpdate(rvo);
		if(action == 1) {
			result.put("resultMsg", "success.");
		}else {
			result.put("resultMsg", "error.");
		}
		
		//부서 권한 부여
		//권한 초기화
		int action2 = cpservice.employeeAuthorityDelete(rvo);
		if(action2 > 0) {
			result.put("resultMsg2", "success.");
		}else {
			result.put("resultMsg2", "error.");
		}
		//권한 새로 받은 값으로 재등록
		for(int i=0; i<rvo.getAdd_department_code_list().size(); i++) {
			rvo.setDepartment_code(rvo.getAdd_department_code_list().get(i));
			int action3 = cpservice.employeeAuthorityInsert(rvo);
			if(action3 == 1) {
				result.put("resultMsg3", "success.");
			}else {
				result.put("resultMsg3", "error.");
				return result;
			}
		}
		
		return result;
	}
	
	//사원 정보 삭제
	@RequestMapping(value = "/tring_company_employeeDelete", method = RequestMethod.POST)
	public Map<String, Object> employeeDelete(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {user_uid}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		rvo.setCompany_code(null);
		rvo.setDepartment_code(null);
		int action = cpservice.employeeDelete(rvo);

		if(action == 1) {
			result.put("resultMsg", "success.");
		}else {
			result.put("resultMsg", "error.");
		}
		
		return result;
	}
	
	//엑셀 다운로드
	@RequestMapping(value = "/tring_company_excelRead", method = RequestMethod.POST)
	public Map<String, Object> excelRead(@RequestBody ResultVO rvo) throws Exception {
	
		//요청 {company_code}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
//		DeptVO vo = cpservice.employeeDelete(dvo.getDept_uid());
//		
//		result.put("company", vo);
		
		return result;
	}
	
//	//엑셀 업로드
//	@RequestMapping(value = "/tring_company_excelUpload", method = RequestMethod.POST)
//    public Map<String, Object> excelUpload(HttpServletRequest request, Model model, HttpServletResponse response,
//    		@RequestParam(name = "excelFile", required = false) MultipartFile mfile) throws IOException {
//    	
//		final Map<String, Object> result = new HashMap<String, Object>();
//		
//    	System.out.println("excel 읽기");
//    	String savePath = request.getSession().getServletContext().getRealPath("resources/excel/excelData");
//		
//    	String company = "";
//    	String deptName = "";
//    	List<MemberVO> list = new ArrayList<MemberVO>();
//    	
//		try {
//			
//			//엑셀 저장
//			if (!mfile.isEmpty()) {
//				String fileName = mfile.getOriginalFilename();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
//				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
////				File originFile = new File(savePath + "/" + fileName);
//				File renameFile = new File(savePath + "/" + renameFileName);
//				mfile.transferTo(renameFile);
//				
//				//엑셀 읽기
//				FileInputStream file = new FileInputStream(savePath + "/" + renameFileName);
//		        XSSFWorkbook workbook = new XSSFWorkbook(file);
//		
//		        int rowNo = 0;
//		        int cellIndex = 0;
//		        
//		        XSSFSheet sheet = workbook.getSheetAt(0); // 0 번째 시트를 가져온다 
//		        										  // 만약 시트가 여러개 인 경우 for 문을 이용하여 각각의 시트를 가져온다
//		        int rows = sheet.getPhysicalNumberOfRows()+1; // 사용자가 입력한 엑셀 Row수를 가져온다
//		        System.out.println("rows:"+rows);
//		        for(rowNo = 0; rowNo < rows; rowNo++){
//		        	
//		        	MemberVO vo = new MemberVO();
//		        	
//		            XSSFRow row = sheet.getRow(rowNo);
//		            if(row != null){
//		                int cells = row.getPhysicalNumberOfCells(); // 해당 Row에 사용자가 입력한 셀의 수를 가져온다
//		                for(cellIndex = 0; cellIndex <= cells; cellIndex++){  
//		                    XSSFCell cell = row.getCell(cellIndex); // 셀의 값을 가져온다	        
//		                    String value = "";	                    
//		                    if(cell == null){ // 빈 셀 체크 
//		                        continue;
//		                    }else{
//		                        // 타입 별로 내용을 읽는다
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
////		                    System.out.println( (rowNo+1) + "번 행 : " + (cellIndex+1) + "번 열 값은: " + value);
//		                    
//		                    //회사명
//		                    if(rowNo ==1 && cellIndex==1){
//		                    	company = value;
//		                    }
//		                    //부서명
//		                    if(rowNo ==1 && cellIndex==4){
//		                    	deptName = value;
//		                    }
//		                    //직원 정보
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
//		    		                //직원 DB 업데이트
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
////			result.put("msg", "success");
//			
//			return result;
//			
//		} catch (Exception e) {
//
////			result.put("msg", "fail");
//            return result;
//		}
//
//    }

//////////////////////////////////////////////// 사원 승인 내역 ////////////////////////////////////////////////
	
	//사원 승인 요청 회원 리스트 출력
    @RequestMapping(value = "/tring_company_approvalList", method = RequestMethod.POST)
    public Map<String, Object> approvalList(@RequestBody ResultVO rvo) throws Exception {
    	
    	//요청 {company_code}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	List<ApprovalVO> approvalList = cpservice.approvalList(rvo.getCompany_code());
    	for(int i=0; i<approvalList.size(); i++) {
    		DeptVO dvo = cpservice.deptDetail(approvalList.get(i).getDepartment_code());
    		if(dvo != null) {
    			approvalList.get(i).setDepartment_name(dvo.getDepartment_name());
    		}
    	}
    	
    	result.put("approvalList", approvalList);
        
    	return result;
    }
	
    //사원 요청 처리
    @RequestMapping(value = "/tring_company_approvalProcess", method = RequestMethod.POST)
    public Map<String, Object> approvalProcess(@RequestBody ResultVO rvo) throws Exception {
    	
    	//요청 {user_uid, apv_user_status}

    	final Map<String, Object> result = new HashMap<String, Object>();

    	//승인요청 처리
    	int action1 = cpservice.approvalProcess(rvo);
    	
    	if(action1 == 1) {
    		result.put("resultMsg1", "success.");
    	}else {
    		result.put("resultMsg1", "error.");
    	}
    	
    	//유저 인서트
    	int action2 = 0;
    	int action3 = 0;
    	if(rvo.getUser_status() == 1) {
    		ResultVO vo = cpservice.approvalUser(rvo.getUser_uid());
	    	vo.setUser_authority(2);
	    	vo.setUser_login_type("Company");
	    	
	    	action2 = uservice.userInsert(vo);
	    	if(action2 == 1) {
	    		result.put("resultMsg2", "success.");
	    	}else {
	    		result.put("resultMsg2", "error.");
	    	}
	    	
	    	//기본 폴더 생성
	    	for(int i=0; i<2; i++) {
		    	FolderVO fvo = new FolderVO();
		    	fvo.setUser_uid(rvo.getUser_uid());
		    	fvo.setFolder_level(0);
		    	fvo.setFolder_dataType(i); // 0-회의, 1-메모

		    	fvo.setFolder_name("내 폴더");
		    	fvo.setFolder_type(1); // 0-생성, 1-기본, 2-공유
		    	fvo.setFolder_uid(UUID.randomUUID().toString().replace("-",""));
		    	action3 = fservice.userFolderInsert(fvo);
		    	if(action3 == 0) {
		    		result.put("resultMsg3-1", "error.");
		    	}else {
		    		result.put("resultMsg3-1", "success.");
		    	}
		    	
		    	fvo.setFolder_name("공유 폴더");
		    	fvo.setFolder_type(2); // 0-생성, 1-기본, 2-공유
		    	fvo.setFolder_uid(UUID.randomUUID().toString().replace("-",""));
		    	action3 = fservice.userFolderInsert(fvo);
		    	if(action3 == 0) {
		    		result.put("resultMsg3-2", "error.");
		    	}else {
		    		result.put("resultMsg3-2", "success.");
		    	}
	    	}
    	}
        	
    	return result;
    }
    
}
