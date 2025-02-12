<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>사용자 목록</h2>

	<c:forEach var="user" items="${userList}">
		
		<p>
		
<%-- 		<a href="/admin/user/${user.id}"> --%>
		
		${user.id} ${user.pw} ${user.name}
		</a>
		</p>
		</c:forEach>
		
</body>
</html>