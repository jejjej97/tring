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
							<table>
  								 <thead>
                                   	<tr>
	                                   	<th>id</th>
	                                   	<th>권한</th>
	                                   	<th></th>
                                   	</tr>
                                 </thead>
                                 <tbody>
                                 <c:forEach items="${Calendar}" var="Calendar" varStatus="status">
                                    <tr>
                                            <td>${Calendar.calendarUserId}</td>
                                            <td>${Calendar.rule}</td>
                                            <td><a href="/googleCalendarPersonDel?calendarId=${calendarId}&calendarUserId=${Calendar.calendarUserId}">참여자 강퇴</a></td>
                                    </tr>
                                 </c:forEach>
                                 </tbody>
                            </table>
	
	<form action="/googleCalendarPersonAdd?calendarId=${calendarId}" method="post">
		<input placeholder="참여자 이메일" name="email" type="text">
		<input type="submit" value="참여자 추가">
	</form>
	
  </body>
</html>