<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/newPassword.css" rel="stylesheet">


<title>Insert title here</title>
</head>
<body>
	<h2 class="formTitle">인증이 완료되어 비밀번호 재설정을 진행합니다.</h2>
	<h4 class="formSubTitle">변경할 비밀번호를 입력하세요</h4>
	<form action="/newPassword" method="post" class="newPasswordForm">
		<div class="form">
			<div class="formSection">
				<div class="itemContainer">
					<input type="text" id="pw" name="pw" class="item" placeholder="변경할 비밀번호를 입력하세요"
						autofocus>
				</div>
				<div class="itemContainer">
					<button type="submit" class="item">변경</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>