<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>할 일 관리</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
    <script src="<c:url value='/js/tasks.js'/>"></script>
</head>
<body>

<h2>할 일 목록</h2>

<!-- 할 일 추가 폼 -->
<form action="<c:url value='/tasks/add'/>" method="post">
    <input type="text" name="title" placeholder="할 일을 입력하세요" required>
    <button type="submit">추가</button>
</form>

<!-- 할 일 목록 -->
<table border="1">
    <tr>
        <th>번호</th>
        <th>할 일</th>
        <th>삭제</th>
    </tr>
    <c:forEach var="task" items="${taskList}">
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>
                <form action="<c:url value='/tasks/delete'/>" method="post">
                    <input type="hidden" name="id" value="${task.id}">
                    <button type="submit">삭제</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
