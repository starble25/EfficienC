<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>캘린더</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>캘린더</h1>
    
    <form action="${pageContext.request.contextPath}/calendar/add" method="post">
        <input type="text" name="title" placeholder="이벤트 제목" required>
        <input type="date" name="startDate" required>
        <input type="date" name="endDate" required>
        <button type="submit">이벤트 추가</button>
    </form>

    <ul>
        <c:forEach var="event" items="${events}">
            <li>${event.title} ( ${event.startDate} ~ ${event.endDate} )</li>
        </c:forEach>
    </ul>
</body>
</html>
