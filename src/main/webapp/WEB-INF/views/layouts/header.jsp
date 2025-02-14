<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공통 헤더</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"> <!-- ✅ CSS 파일 추가 -->
</head>
<body>

    <!-- 📌 공통 헤더 -->
    <div class="header-container">
        <div class="header-left">
            <a href="/main"><strong>🏠 홈</strong></a> <!-- ✅ 홈 버튼 추가 -->
        </div>
        
        <div class="header-center">
            <h2>기업 일정 및 할일 관리 시스템</h2>
        </div>

        <div class="header-right">
            <span>환영합니다, <strong>${sessionScope.userName}</strong> 님</span> <!-- ✅ 로그인한 사용자 이름 표시 -->
            <a href="/user/mypage">마이페이지</a> | 
            <a href="/logout">로그아웃</a>
        </div>
    </div>

</body>
</html>
