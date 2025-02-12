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
            let emptyCell = document.createElement("td");
            row.appendChild(emptyCell);
        }

        for (let date = 1; date <= lastDate; date++) {
            let cell = document.createElement("td");
            let dateNumber = document.createElement("span");
            dateNumber.classList.add("date-number");
            dateNumber.innerText = date;

            cell.appendChild(dateNumber);
            cell.setAttribute("data-date", `${year}-${String(month + 1).padStart(2, '0')}-${String(date).padStart(2, '0')}`);

            // 📌 오늘 날짜 강조
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
                let calendarCells = document.querySelectorAll("#calendar-body td");

                events.forEach(event => {
                    let eventDate = new Date(event.start);
                    let formattedDate = `${eventDate.getFullYear()}-${String(eventDate.getMonth() + 1).padStart(2, '0')}-${String(eventDate.getDate()).padStart(2, '0')}`;

                    calendarCells.forEach(cell => {
                        if (cell.getAttribute("data-date") === formattedDate) {
                            let eventDiv = document.createElement("div");
                            eventDiv.classList.add("event");
                            eventDiv.innerText = event.title;

                            if (!cell.querySelector(".event")) {
                                cell.appendChild(eventDiv);
                            }
                        }
                    });
                });
            })
            .catch(error => console.error("Error loading events:", error));
    }

    updateCalendar();
});
