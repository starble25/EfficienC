<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="sidebar">
		<div class="logo">LOGO</div>
		<ul class="menu">
			<!-- requestScope.menuList 로부터 메뉴 생성 -->
			<c:forEach var="menuItem" items="${requestScope.menuList}">
				<!-- EL 삼항연산자 (EL 2.2 이상) -->
				<li class="${menuItem.active ? 'active' : ''}"><a
					href="<c:url value='${menuItem.link}'/>"> <c:out
							value="${menuItem.name}" />
				</a></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
