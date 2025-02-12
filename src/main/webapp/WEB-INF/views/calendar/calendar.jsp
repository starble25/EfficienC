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

    <!-- 📌 왼쪽 메뉴 -->
    <div class="sidebar">
        <h3>📅 캘린더</h3>
        <button id="openModalBtn">일정 등록</button>
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
                <!-- 📌 JS에서 동적으로 생성 -->
            </tbody>
        </table>
    </div>

    <!-- 📌 모달창 (일정 등록) -->
    <div id="eventModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h3>일정 등록</h3>
            <form id="eventForm">
                <label>제목:</label>
                <input type="text" id="title" required>

                <label>시작 날짜:</label>
                <input type="datetime-local" id="startDate" required>

                <label>종료 날짜:</label>
                <input type="datetime-local" id="endDate">

                <label>카테고리:</label>
                <select id="category">
                    <option value="기본">기본</option>
                    <option value="회의">회의</option>
                    <option value="마감일">마감일</option>
                    <option value="워크샵">워크샵</option>
                    <option value="외부 일정">외부 일정</option>
                </select>

                <button type="submit">등록</button>
            </form>
        </div>
    </div>

</body>
</html>