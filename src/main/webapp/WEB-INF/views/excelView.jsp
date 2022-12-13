<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Google Calendar API Quickstart</title>
    <meta charset="utf-8" />
  </head>
  <body>
    <a href="javascript:history.back();">Back</a>
	<br>

							<table>
  								 <thead>
                                   	<tr>
	                                   	<th>회사명</th>
	                                   	<th>부서명</th>
	                                   	<th>순번</th>
	                                   	<th>직책</th>
	                                   	<th>성명</th>
	                                   	<th>생년월일</th>
	                                   	<th>휴대폰</th>
	                                   	<th>이메일</th>
                                   	</tr>
                                 </thead>
                                 <tbody>
                                 <c:forEach items="${list}" var="list" varStatus="status">
                                    <tr>
                                            <td>${list.company_name}</td>
                                            <td>${list.dept_name}</td>
                                            <td>${list.dept_no}</td>
                                            <td>${list.job}</td>
                                            <td>${list.nickName}</td>
                                            <td>${list.birth}</td>
                                            <td>${list.phone}</td>
                                            <td>${list.email}</td>
                                    </tr>
                                 </c:forEach>
                                 </tbody>
                            </table>
	
  </body>
</html>