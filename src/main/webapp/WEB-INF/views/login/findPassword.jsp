<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/findPassword.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<h2 class="formTitle">계정의 비밀번호를 재설정합니다.</h2>
	<h4 class="formSubTitle">비밀번호를 재설정 하기위한 정보를 입력해 주세요</h4>

	<form action="/findPassword" method="post" class="findPasswordForm">
		<div class="form">
			<div class="formSection">
				<div class="itemContainer">
					<input name="email" id="email" placeholder="이메일" type="email"
						class="item" autofocus> <br>
				</div>
				<div class="itemContainer">
					<input type="text" name="name" id="name" class="item"
						placeholder="이름"><br>
				</div>
				<div class="itemContainer">
					<input type="text" name="jumin" id="jumin" class="item"
						placeholder="주민등록번호"><br>
				</div>
				<div class="itemContainer">
					<button type="submit" class="item">다음</button>
				</div>



			</div>
		</div>
	</form>
</body>

</html>