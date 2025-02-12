<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>인증이 완료되어 비밀번호 재설정을 진행합니다.</h2>
	<h4>변경할 비밀번호를 입력하세요</h4>
<form action="/newPassword" method="post">
<input type="text" id="pw" name="pw"  placeholder="변경할 비밀번호를 입력하세요" autofocus>
<button type="submit">변경</button>
</form>
</body>
</html>