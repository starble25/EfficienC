<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>캘린더</title>

    <link rel="stylesheet" href="<c:url value='/css/calendar.css'/>">
    <script src="<c:url value='/js/calendar.js'/>" defer></script>
</head>
<body>

    <!-- 📌 왼쪽 사이드바 -->
    <div class="sidebar">
        <h3>📅 캘린더</h3>
        <button id="openModalBtn">일정 등록</button>

        <h4 class="calendar-category">내 캘린더</h4>
        <ul class="calendar-list">
            <li><span class="color-dot blue"></span> 내 일정(기본)</li>
            <li><span class="color-dot green"></span> 중요</li>
            <li><span class="color-dot red"></span> 연차</li>
        </ul>

        <span class="add-calendar">+ 내 캘린더 추가</span>
    </div>

    <!-- 📌 캘린더 컨테이너 -->
    <div class="calendar-container">
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
            </tbody>
        </table>
    </div>

</body>
</html>
