<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>일정 등록</title>
</head>
<body>
    <h3>📌 일정 등록</h3>
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

        <button type="button" onclick="submitEvent()">등록</button>
    </form>

    <script>
        function submitEvent() {
            let title = document.getElementById("title").value;
            let startDate = document.getElementById("startDate").value;
            let endDate = document.getElementById("endDate").value;
            let category = document.getElementById("category").value;

            fetch("/calendar/add", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: `title=${title}&startDate=${startDate}&endDate=${endDate}&category=${category}`
            })
            .then(() => {
                window.close();
                window.opener.location.reload();
            });
        }
    </script>
</body>
</html>
