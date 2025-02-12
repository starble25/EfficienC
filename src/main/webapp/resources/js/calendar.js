document.addEventListener("DOMContentLoaded", function () {
    let currentDate = new Date();

    function updateCalendar() {
        const year = currentDate.getFullYear();
        const month = currentDate.getMonth();
        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();
        const today = new Date();

        document.getElementById("current-year-month").innerText = `${year}년 ${month + 1}월`;

        let calendarBody = document.getElementById("calendar-body");
        calendarBody.innerHTML = "";

        let row = document.createElement("tr");

        for (let i = 0; i < firstDay; i++) {
            row.appendChild(document.createElement("td"));
        }

        for (let date = 1; date <= lastDate; date++) {
            let cell = document.createElement("td");
            cell.innerText = date;
            cell.setAttribute("data-date", `${year}-${String(month + 1).padStart(2, '0')}-${String(date).padStart(2, '0')}`);

            if (year === today.getFullYear() && month === today.getMonth() && date === today.getDate()) {
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

    window.changeMonth = function (change) {
        currentDate.setMonth(currentDate.getMonth() + change);
        updateCalendar();
    };

    function loadEvents() {
        fetch("/calendar/events")
            .then(response => response.json())
            .then(events => {
                events.forEach(event => {
                    let eventDate = new Date(event.start);
                    let day = eventDate.getDate();
                    let cells = document.querySelectorAll("#calendar-body td");

                    cells.forEach(cell => {
                        if (parseInt(cell.innerText) === day) {
                            let eventDiv = document.createElement("div");
                            eventDiv.classList.add("event");
                            eventDiv.innerText = event.title;
                            cell.appendChild(eventDiv);
                        }
                    });
                });
            })
            .catch(error => console.error("Error loading events:", error));
    }

    updateCalendar();

    // ✅ 모달창 제어
    const modal = document.getElementById("eventModal");
    const openModalBtn = document.getElementById("openModalBtn");
    const closeModalBtn = document.querySelector(".close");
    const eventForm = document.getElementById("eventForm");

    openModalBtn.addEventListener("click", () => {
        modal.style.display = "block";
    });

    closeModalBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    };

    eventForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const title = document.getElementById("title").value;
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;
        const category = document.getElementById("category").value;

        fetch("/calendar/add", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams({ title, startDate, endDate, category })
        })
            .then(response => response.text())
            .then(result => {
                if (result === "success") {
                    alert("일정이 추가되었습니다!");
                    modal.style.display = "none";
                    updateCalendar();
                } else {
                    alert("일정 추가 실패!");
                }
            })
            .catch(error => console.error("Error adding event:", error));
    });
});
