<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Main Page</title>
  <!-- (1) JSTL 방식으로 CSS 로드 -->
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>" />
</head>
<body>
  <!-- 공통 사이드바 포함 -->
  <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

  <div class="content">
    <h1>메인 페이지</h1>
    <p>이곳에 메인 콘텐츠를 작성하세요.</p>
    <button onclick="location.href = '/logout'">로그아웃</button>
  </div>
</body>
</html>
