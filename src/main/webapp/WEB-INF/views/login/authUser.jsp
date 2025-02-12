<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>비밀번호 재설정을 위해 사용자 확인을 진행합니다</h2>
	<h4>입력하신 이메일로 발송된 인증번호를 입력하세요.</h4>
	<form action="/authUser" method="post">
		<input class="form-control" placeholder="인증 코드 6자리를 입력해주세요."
			maxlength="6" name="inputCode" id="inputCode"
			type="text" autofocus>

		<div>
			<button type="submit" id="inputAuthCode" class="btn btn-primary">확인</button>
			<br>
		</div>
		<span id="emailAuthWarn"></span>
	</form>
</body>
</html>