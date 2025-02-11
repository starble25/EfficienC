<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>캘린더</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        .calendar-container { width: 80%; margin: auto; }
        .calendar-header { display: flex; justify-content: center; align-items: center; margin-bottom: 20px; }
        .calendar-header button { margin: 0 10px; padding: 5px 10px; }
        .calendar-table { width: 100%; border-collapse: collapse; }
        .calendar-table th, .calendar-table td { width: 14.28%; border: 1px solid #ccc; padding: 15px; text-align: center; height: 80px; }
        .calendar-table th { background: #f0f0f0; }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            let currentDate = new Date();
            renderCalendar(currentDate);

            document.getElementById("prevMonth").addEventListener("click", function() {
                currentDate.setMonth(currentDate.getMonth() - 1);
                renderCalendar(currentDate);
            });

            document.getElementById("nextMonth").addEventListener("click", function() {
                currentDate.setMonth(currentDate.getMonth() + 1);
                renderCalendar(currentDate);
            });
        });

        function renderCalendar(date) {
            const year = date.getFullYear();
            const month = date.getMonth();
            const firstDay = new Date(year, month, 1).getDay();
            const lastDate = new Date(year, month + 1, 0).getDate();
            
            document.getElementById("calendarTitle").innerText = `${year}년 ${month + 1}월`;

            let tableBody = document.getElementById("calendarBody");
            tableBody.innerHTML = "";

            let row = document.createElement("tr");
            for (let i = 0; i < firstDay; i++) {
                row.appendChild(document.createElement("td"));
            }

            for (let day = 1; day <= lastDate; day++) {
                if (row.children.length === 7) {
                    tableBody.appendChild(row);
                    row = document.createElement("tr");
                }
                let cell = document.createElement("td");
                cell.innerText = day;
                row.appendChild(cell);
            }
            tableBody.appendChild(row);
        }
    </script>
</head>
<body>
    <div class="calendar-container">
        <h2>캘린더</h2>
        <div class="calendar-header">
            <button id="prevMonth">이전</button>
            <h3 id="calendarTitle"></h3>
            <button id="nextMonth">다음</button>
        </div>
        <table class="calendar-table">
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
            <tbody id="calendarBody"></tbody>
        </table>
    </div>
</body>
</html>
