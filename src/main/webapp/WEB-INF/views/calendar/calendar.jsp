<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>캘린더</title>
    <link rel="stylesheet" href="/resources/calendar/css/style.css">
</head>
<body>

<h1>캘린더</h1>
<form action="/calendar/add" method="post">
    <input type="text" name="title" placeholder="이벤트 제목" required>
    <input type="datetime-local" name="startDate" required>
    <input type="datetime-local" name="endDate">
    <button type="submit">이벤트 추가</button>
</form>

<ul>
    <c:forEach var="event" items="${events}">
        <li>${event.title} ( ${event.startDate} ~ ${event.endDate} )</li>
    </c:forEach>
</ul>

</body>
</html>
