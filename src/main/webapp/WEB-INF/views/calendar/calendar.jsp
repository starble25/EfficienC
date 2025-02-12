<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>캘린더</title>

    <!-- 📌 JSTL을 활용한 정적 리소스 로드 -->
    <link rel="stylesheet" href="<c:url value='/css/calendar.css'/>">
    <script src="<c:url value='/js/calendar.js'/>" defer></script>
</head>
<body>
    <div class="calendar-container">
        <h2>📅 캘린더</h2>
        <div class="nav-buttons">
            <button onclick="changeMonth(-1)">이전</button>
            <span id="current-year-month"></span>
            <button onclick="changeMonth(1)">다음</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>일</th>
                    <th>월</th>
                    <th>화</th>
                    <th>수</th>
                    <th>목</th>
                    <th>금</th>
                    <th>토</th>
                </tr>
            </thead>
            <tbody id="calendar-body">
                <!-- 📌 JS에서 동적으로 삽입 -->
            </tbody>
        </table>
    </div>
</body>
</html>
