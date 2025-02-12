<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>계정의 비밀번호를 재설정합니다.</h2>
	<h4>비밀번호를 재설정 하기위한 정보를 입력해 주세요</h4>

	<form action="/findPassword" method="post">

		<input placeholder="이메일" name="email" id="email"
			type="email" autofocus> <br>

		<p>이름</p>
		<input type="text" name="name" id="name"><br>
		<p>주민번호</p>

		<input type="text" name="jumin" id="jumin"><br>

		<button type="submit">다음</button>



		

	</form>
</body>

</html>