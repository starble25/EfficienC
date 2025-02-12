<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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

    <!-- 📌 왼쪽 사이드바 -->
    <div class="sidebar">
        <h3>📅 캘린더</h3>
        <button id="openEventPopupBtn">일정 등록</button>
        <h4>📌 일정 카테고리</h4>
        <ul class="category-list">
            <li><input type="checkbox" class="category-filter" value="전체" checked> 전체</li>
            <li><input type="checkbox" class="category-filter" value="회의" checked> 회의</li>
            <li><input type="checkbox" class="category-filter" value="마감일" checked> 마감일</li>
            <li><input type="checkbox" class="category-filter" value="워크샵" checked> 워크샵</li>
            <li><input type="checkbox" class="category-filter" value="외부 일정" checked> 외부 일정</li>
        </ul>
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

    <script>
        document.getElementById("openEventPopupBtn").addEventListener("click", function () {
            let popupUrl = "/calendar/event-form";
            let popupOptions = "width=500,height=600,top=100,left=200,scrollbars=yes";
            window.open(popupUrl, "EventPopup", popupOptions);
        });
    </script>

</body>
</html>
