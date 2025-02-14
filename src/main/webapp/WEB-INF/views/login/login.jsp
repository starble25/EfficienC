<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
>>>>>>> ft-calendar
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>

<h1>로그인</h1>

<!-- 📌 로그인 폼 (name 속성 추가) -->
<form action="login" method="post">
    아이디 (이메일) <br>
    <input type="text" name="email" required><br>  <!-- ✅ name="email" 추가 -->
    비밀번호 <br>
    <input type="password" name="pw" required><br><br>  <!-- ✅ name="pw" 추가 -->

    <button type="submit">로그인</button>
</form>

<!-- 📌 회원가입 / 아이디 찾기 / 비밀번호 찾기 -->
<button onclick="location.href='register'">회원가입</button>
<button onclick="location.href='findId'">아이디 찾기</button>
<button onclick="location.href='findPassword'">비밀번호 찾기</button>

<!-- 📌 로그인 실패 시 메시지 표시 -->
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

</body>
</html>
