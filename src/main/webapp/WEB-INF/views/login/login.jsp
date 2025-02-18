<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/login.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<h2 class="formTitle">로그인</h2>

	<form action="" method="post" class="loginForm">
		<div class=form>
			<div class="formSection">
				<div class="itemContainer">
					<div>
						<input type="text" name="email" placeholder="이메일" class="item">
					</div>
					<div class="itemContainer">
						<input type="password" name="pw" placeholder="비밀번호" class="item"><br>
					</div>
					<div class="itemContainer">
						<button type=submit class="item btn">로그인</button>
					</div>
				</div>
				</div>
				<div class="formSection">
					<div class="flexWrap" >
						<div class="itemContainer flexItemWidth">
							<input type="button" onclick="location.href = '/signup'" class="item" value="회원가입"></button>
						</div>
						<div class="itemContainer flexItemWidth">
							<input type="button" onclick="location.href = '/findPassword'" class="item" value="비밀번호 찾기"></button>
						</div>
					</div>
				</div>
				
			</div>
	
	</form>




</body>
</html>