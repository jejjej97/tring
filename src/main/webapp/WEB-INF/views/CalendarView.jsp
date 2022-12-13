<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Google Calendar API Quickstart</title>
    <meta charset="utf-8" />
  </head>
  <body>
    <a href="/googleCalendarUrl">Calendar List</a>
	<br>
	<a href="/googleCalendarPersonList?calendarId=${calendarId}">참여자 보기</a>
	<br>
							<table>
  								 <thead>
                                   	<tr>
	                                   	<th>제목</th>
	                                   	<!-- <th>내용</th> -->
	                                   	<th>startTime</th>
	                                   	<th>endTime</th>
	                                   	<th></th>
                                   	</tr>
                                 </thead>
                                 <tbody>
                                 <c:forEach items="${Calendar}" var="Calendar" varStatus="status">
                                    <tr>
                                            <td>${Calendar.summary}</td>
                                            <%-- <td>${Calendar.description}</td> --%>
                                            <td>${Calendar.startTime}</td>
                                            <td>${Calendar.endTime}</td>
                                            <td><a href="/googleCalendarUpdateEvent?calendarId=${calendarId}&eventId=${Calendar.eventId}">일정 수정</a></td>
                                            <td><a href="/googleCalendarDeleteEvent?calendarId=${calendarId}&eventId=${Calendar.eventId}">일정 삭제</a></td>
                                            <td></td>
                                    </tr>
                                 </c:forEach>
                                 </tbody>
                            </table>
	
	<form action="/googleCalendarInsertEvent?calendarId=${calendarId}" method="post">
		<input placeholder="일정제목" name="summary" type="text">
		<input placeholder="시작시간" name="startDate" type="text">
		<input placeholder="종료시간" name="endDate" type="text">
		<input type="submit" value="일정 추가">
	</form>
  </body>
</html>