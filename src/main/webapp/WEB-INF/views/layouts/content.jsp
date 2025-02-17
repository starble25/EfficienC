<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="sidebar-menu">
		<ul>
			<li><a href="<c:url value='/home'/>">홈</a></li>
			<li><a href="<c:url value='/calendar'/>">일정 관리</a></li>
			<li><a href="<c:url value='/tasks'/>">할 일 관리</a></li>
		</ul>
	</div>

</body>
</html>