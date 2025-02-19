<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Main Page</title>
  <!-- /resources/css/style.css 경로를 JSTL로 로드 -->
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>" />
</head>
<body>
  <!-- 공통 사이드바 include -->
  <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

  <div class="content">
    <jsp:include page="/WEB-INF/views/common/MainContent.jsp" />
    
  </div>
</body>
</html>
