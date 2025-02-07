<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>회원가입</h2>

<form action="" method="post">

<label for="userName">이름<br>
<input type="text" id="userName"><br>
</label>

<label for="userJumin">주민번호<br>
<input type="text" class="userJumin" name="frontJumin"> - 
<input type="password" class="userjumin" name="backJumin"><br>
</label>

<label for="userTel">전화번호<br>
<input type="text" class="tel">-<input type="text" class="tel">-
<input type="text" class="tel"><br>
</label>

<label for="userId">사용할 아이디<br>
<input type="text" id="willUseId">
<button type=submit>중복 확인</button><br>
</label>

<label for="userPassword">비밀번호<br>
<input type="passWord" id="willUsePw"><br>
비밀번호 확인<br>
<input type="passWord" id="checkPw"><br>
</label>

<label for="userEmail">
이메일<br>
<input type="text" id="email"> @ 
<select>
<option>naver.com</option>
<option>daum.net</option>
<option>gmail.com</option>
</select>
<button>인증번호 전송</button><br>
인증번호 확인<br>
<input type="text" id="checkemail"><button>인증번호 확인</button><br><br>
</label>

<button>회원가입 하기</button>
</form>

</body>
</html>