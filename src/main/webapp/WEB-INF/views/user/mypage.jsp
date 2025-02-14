<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="mypage-container">
		<h2>마이페이지</h2>
		<p>
			이름:
			<%=session.getAttribute("userName")%></p>
		<p>
			이메일:
			<%=session.getAttribute("userEmail")%></p>
	</div>

</body>
</html>