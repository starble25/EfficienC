<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/authUser.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<h2 class="formTitle">비밀번호 재설정을 위해 사용자 확인을 진행합니다</h2>
	<h4 class="formSubTitle">입력하신 이메일로 발송된 인증번호를 입력하세요.</h4>
	<form action="/authUser" method="post" class="authUserForm">
		<div class="form">
			<div class="formSection">
				<div class="itemContainer">
					<input type="text" name="inputCode" id="inputCode" class="item"
						 placeholder="인증 코드 6자리를 입력해주세요." maxlength="6" autofocus>
				</div>

				<div class="itemContainer">
					<button type="submit" id="inputAuthCode" class="item">확인</button>
				</div>
				<span id="emailAuthWarn"></span>
			</div>
		</div>
	</form>
</body>
</html>