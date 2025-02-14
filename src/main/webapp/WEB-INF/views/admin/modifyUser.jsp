<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/modifyUser.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<h1>관리자 페이지</h1>
	<h2>사용자 정보 수정</h2>

	<form action="/admin/modifyUser" method="post">

		<input type="hidden" name="email" value="${user.email}"> 
		사용자
		아이디 : <input type="text" name="email" value="${user.email}" disabled placeholder="${user.email}"><br> 
		사용자 
		비밀번호 : <input type="password" name="pw"><br> 
		사용자 
		이름 : <input type="text" name="name" value="${user.name}"> <br><br> 
		
		사용자 직급 선택 <br>
		<label>사원<br>
		<input type="radio" name="positionCode" value="1" <c:if test="${user.positionCode == '사원'}"> </c:if> ></label><br> 
		<label>대리<br>
		<input type="radio" name="positionCode" value="2" <c:if test="${user.positionCode == '대리'}"> </c:if> ></label><br>
		사용자 부서 선택 <br>
		<label>인사<br>
		<input type="radio" name="deptCode" value="1" <c:if test="${user.deptCode == '인사'}">  </c:if> ></label><br>
		<label>생산<br>
		<input type="radio" name="deptCode" value="2" <c:if test="${user.deptCode == '생산'}">  </c:if> ></label><br>

		<button type="submit" "location.href='/admin/user/${user.email}'">수정하기</button>
	</form>

</body>
</html>