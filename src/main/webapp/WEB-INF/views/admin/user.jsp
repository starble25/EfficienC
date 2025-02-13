<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자 상세 페이지</h2>
	<input type="hidden" name="id" value="${user.id}">
	<p>아이디 : ${user.email}</p>
	<p>이름 : ${user.name}</p>
	<p>전화번호 : ${user.tel}</p>
	<p>직급 : ${user.positionCode}</p>
	<p>부서 : ${user.deptCode}</p>
	
	<button type="button" onClick="location.href='/admin/usersList'">사용자 목록보기</button>

	<p>
		<button onClick="location.href='/admin/modifyUser/${user.email}'">수정하기</button>
	</p>
	
</body>
</html>