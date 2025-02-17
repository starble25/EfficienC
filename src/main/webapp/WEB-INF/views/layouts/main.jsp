<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
</head>
<body>

<div class="container">
    <!-- 왼쪽 메뉴 -->
    <div class="sidebar">
        <jsp:include page="/WEB-INF/views/layouts/sidebar.jsp" />
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="content">
        <jsp:include page="/WEB-INF/views/layouts/content.jsp" />
    </div>
</div>

</body>
</html>
