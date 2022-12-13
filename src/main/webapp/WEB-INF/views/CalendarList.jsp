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
	                                   	<th>summary</th>
	                                   	<th>calendarId</th>
	                                   	<th></th>
                                   	</tr>
                                 </thead>
                                 <tbody>
                                 <c:forEach items="${CalendarList}" var="CalendarList" varStatus="status">
                                    <tr>
                                            <td><a href="/googleCalendarView?calendarId=${CalendarList.calendarId}">${CalendarList.summary}</a></td>
                                            <td>${CalendarList.calendarId}</td>
                                            <td><a href="/googleCalendarDelete?calendarId=${CalendarList.calendarId}">달력삭제</a></td>
                                    </tr>
                                 </c:forEach>
                                 </tbody>
                            </table>
	
	<form action="/googleCalendarInsert" method="post">
		<input name="summary" type="text">
		<input type="submit" value="달력 추가">
	</form>
  </body>
</html>