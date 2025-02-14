document.addEventListener("DOMContentLoaded", function () {
    let currentDate = new Date();

    function updateCalendar() {
        const year = currentDate.getFullYear();
        const month = currentDate.getMonth();
        document.getElementById("current-year-month").innerText = `${year}년 ${month + 1}월`;

        let calendarBody = document.getElementById("calendar-body");
        calendarBody.innerHTML = "";
        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();
        let today = new Date().toISOString().split('T')[0];

        let row = document.createElement("tr");
        for (let i = 0; i < firstDay; i++) {
            row.appendChild(document.createElement("td"));
        }

        for (let date = 1; date <= lastDate; date++) {
            let cell = document.createElement("td");
            let formattedDate = `${year}-${String(month + 1).padStart(2, '0')}-${String(date).padStart(2, '0')}`;
            cell.setAttribute("data-date", formattedDate);
            cell.innerHTML = `<strong>${date}</strong> <ul class="event-list"></ul>`;

            if (formattedDate === today) {
                cell.classList.add("today");
            }

            row.appendChild(cell);

            if ((firstDay + date) % 7 === 0 || date === lastDate) {
                calendarBody.appendChild(row);
                row = document.createElement("tr");
            }
        }

        loadEvents();
    }

    function loadEvents() {
        fetch("/calendar/events")
            .then(response => response.json())
            .then(events => {
                let calendarCells = document.querySelectorAll("#calendar-body td");

                events.forEach(event => {
                    let eventDate = new Date(event.startDate);
                    let formattedDate = eventDate.toISOString().split('T')[0];

                    calendarCells.forEach(cell => {
                        if (cell.getAttribute("data-date") === formattedDate) {
                            let eventList = cell.querySelector(".event-list");
                            let eventItem = document.createElement("li");
                            eventItem.innerText = `${event.startTime} ${event.title}`;
                            eventItem.classList.add(event.category.toLowerCase().replace(/ /g, "-"));
                            eventList.appendChild(eventItem);
                        }
                    });
                });
            })
            .catch(error => console.error("Error loading events:", error));
    }

    window.changeMonth = function (change) {
        currentDate.setMonth(currentDate.getMonth() + change);
        updateCalendar();
    };

    // 📌 모달창 제어
    document.getElementById("openModalBtn").addEventListener("click", function () {
        document.getElementById("eventModal").style.display = "block";
    });

    document.querySelector(".close").addEventListener("click", function () {
        document.getElementById("eventModal").style.display = "none";
    });

    document.getElementById("eventForm").addEventListener("submit", function (e) {
        e.preventDefault();

        let title = document.getElementById("title").value;
        let startDate = document.getElementById("startDate").value;
        let endDate = document.getElementById("endDate").value;
        let category = document.getElementById("category").value;

        fetch("/calendar/add", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `title=${encodeURIComponent(title)}&startDate=${encodeURIComponent(startDate)}&endDate=${encodeURIComponent(endDate)}&category=${encodeURIComponent(category)}`
        })
        .then(response => response.text())
        .then(result => {
            if (result === "success") {
                alert("일정이 등록되었습니다!");
                document.getElementById("eventModal").style.display = "none";
                window.location.reload();
            } else {
                alert("등록 실패! 다시 시도해주세요.");
            }
        })
        .catch(error => console.error("Error:", error));
    });

    updateCalendar();
});
