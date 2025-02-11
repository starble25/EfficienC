<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>기업 캘린더</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }

        #calendar {
            width: 80%;
            margin: auto;
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 5px;
            padding: 10px;
        }

        .day {
            border: 1px solid #ddd;
            padding: 20px;
            min-height: 100px;
            position: relative;
            text-align: left;
        }

        .day-header {
            font-weight: bold;
            background: #f2f2f2;
            padding: 10px;
            text-align: center;
        }

        .event {
            background: #3498db;
            color: white;
            padding: 5px;
            border-radius: 3px;
            font-size: 12px;
            margin-top: 5px;
        }

        .controls {
            margin: 20px;
        }

        button {
            padding: 10px 15px;
            margin: 5px;
            font-size: 16px;
        }
    </style>
</head>
<body>

    <h1>캘린더</h1>

    <div class="controls">
        <button onclick="prevMonth()">이전</button>
        <span id="monthYear"></span>
        <button onclick="nextMonth()">다음</button>
    </div>

    <div id="calendar"></div>

    <script>
        let currentYear, currentMonth;
        const calendar = document.getElementById("calendar");

        function generateCalendar(year, month) {
            calendar.innerHTML = ""; // 기존 내용 초기화

            const firstDay = new Date(year, month, 1).getDay();
            const lastDate = new Date(year, month + 1, 0).getDate();
            document.getElementById("monthYear").innerText = `${year}년 ${month + 1}월`;

            // 요일 헤더 추가
            const days = ["일", "월", "화", "수", "목", "금", "토"];
            days.forEach(day => {
                const header = document.createElement("div");
                header.classList.add("day-header");
                header.innerText = day;
                calendar.appendChild(header);
            });

            // 빈 칸 추가 (첫 주 요일 맞추기)
            for (let i = 0; i < firstDay; i++) {
                const emptyCell = document.createElement("div");
                emptyCell.classList.add("day");
                calendar.appendChild(emptyCell);
            }

            // 날짜 채우기
            for (let date = 1; date <= lastDate; date++) {
                const dayCell = document.createElement("div");
                dayCell.classList.add("day");
                dayCell.innerHTML = `<strong>${date}</strong>`;
                dayCell.setAttribute("data-date", `${year}-${month + 1}-${date}`);

                calendar.appendChild(dayCell);
            }

            // 서버에서 일정 불러오기
            loadEvents();
        }

        function loadEvents() {
            fetch('/calendar/events')  // 서버에서 일정 데이터 가져오기
                .then(response => response.json())
                .then(events => {
                    events.forEach(event => {
                        const startDate = new Date(event.start);
                        const eventDay = document.querySelector(`[data-date="${startDate.getFullYear()}-${startDate.getMonth() + 1}-${startDate.getDate()}"]`);
                        if (eventDay) {
                            const eventElement = document.createElement("div");
                            eventElement.classList.add("event");
                            eventElement.innerText = event.title;
                            eventDay.appendChild(eventElement);
                        }
                    });
                })
                .catch(error => console.error("Error loading events:", error));
        }

        function prevMonth() {
            currentMonth--;
            if (currentMonth < 0) {
                currentMonth = 11;
                currentYear--;
            }
            generateCalendar(currentYear, currentMonth);
        }

        function nextMonth() {
            currentMonth++;
            if (currentMonth > 11) {
                currentMonth = 0;
                currentYear++;
            }
            generateCalendar(currentYear, currentMonth);
        }

        // 초기 실행
        document.addEventListener("DOMContentLoaded", function () {
            const today = new Date();
            currentYear = today.getFullYear();
            currentMonth = today.getMonth();
            generateCalendar(currentYear, currentMonth);
        });
    </script>

</body>
</html>
