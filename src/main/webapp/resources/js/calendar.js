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
                let calendarCells = document.querySelectorAll("#calendar-body td");

                events.forEach(event => {
                    let eventDate = new Date(event.startDate);
                    let eventYear = eventDate.getFullYear();
                    let eventMonth = eventDate.getMonth();
                    let eventDay = eventDate.getDate();

                    calendarCells.forEach(cell => {
                        let cellDate = cell.getAttribute("data-date");

                        if (cellDate === `${eventYear}-${String(eventMonth + 1).padStart(2, '0')}-${String(eventDay).padStart(2, '0')}`) {
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

    document.getElementById("openModalBtn").addEventListener("click", function () {
        document.getElementById("eventModal").style.display = "block";
    });

    document.querySelector(".close").addEventListener("click", function () {
        document.getElementById("eventModal").style.display = "none";
    });

    updateCalendar();
});
