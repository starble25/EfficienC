<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>

	<form action="" method="post">
		아이디<br> <input type="text" name="email"><br> 비밀번호<br>
		<input type="password" name="pw"><br>
		<br>

		<button type=submit>로그인</button>
	</form>

	<button onclick="location.href = '/signup'">회원가입</button>
	<button onclick="location.href = '/findId'">아이디 찾기</button>
	<button onclick="location.href = '/findPassword'">비밀번호 찾기</button>


</body>
</html>