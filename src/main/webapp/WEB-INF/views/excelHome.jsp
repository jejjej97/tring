<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Google Calendar API Quickstart</title>
    <meta charset="utf-8" />
  </head>
  <body>
    <a href="/googleCalendarUrl">Calendar List</a>
    <a href="/excelHome">excel</a>
    
    <br><br>
	<a href="/resources/excel/test.xlsx">엑셀 폼 다운로드</a>
	
	<br><br>
	<form action="/excelRead" method="post" enctype="multipart/form-data">
		<input type="file" id="excelFile" name="excelFile">
		<input type="submit" class="btn btn-primary" value="파일 첨부하기">
	</form>
	
  </body>
</html>