<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Project Main Page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"> <!-- ✅ CSS 파일 적용 -->
</head>

<body>

    <!-- 📌 공통 헤더 -->
    <jsp:include page="/WEB-INF/views/layouts/header.jsp" />

    <div class="container">
        <!-- 📌 공통 왼쪽 사이드바 -->
        <jsp:include page="/WEB-INF/views/layouts/sidebar.jsp" />

        <!-- 📌 본문 (각 페이지에서 변경되는 부분) -->
        <div class="content">
            <h1>Project Main Page</h1>
            <button onclick="location.href='/logout'">로그아웃</button>

            <!-- ✅ 각 페이지 내용이 삽입되는 부분 -->
            <jsp:include page="${contentPage}" />
        </div>
    </div>

</body>

</html>
