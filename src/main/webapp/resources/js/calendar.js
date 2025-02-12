document.addEventListener("DOMContentLoaded", function () {
    let currentDate = new Date();

    function updateCalendar() {
        const year = currentDate.getFullYear();
        const month = currentDate.getMonth();
        document.getElementById("current-year-month").innerText = `${year}년 ${month + 1}월`;

        let firstDay = new Date(year, month, 1).getDay();
        let lastDate = new Date(year, month + 1, 0).getDate();
        let today = new Date();
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

    function loadEvents() {
        fetch("/calendar/events")
            .then(response => response.json())
            .then(events => {
                let calendarCells = document.querySelectorAll("#calendar-body td");
                events.forEach(event => {
                    let eventDate = new Date(event.startDate);
                    let eventDay = eventDate.getDate();

                    calendarCells.forEach(cell => {
                        if (parseInt(cell.innerText) === eventDay) {
                            let eventDiv = document.createElement("div");
                            eventDiv.classList.add("event");
                            eventDiv.innerText = event.title;
                            cell.appendChild(eventDiv);
                        }
                    });
                });
            });
    }

    document.getElementById("eventForm").addEventListener("submit", function (event) {
        event.preventDefault();
        let eventData = {
            title: document.getElementById("title").value,
            startDate: document.getElementById("startDate").value,
            endDate: document.getElementById("endDate").value,
            category: document.getElementById("category").value
        };

        fetch("/calendar/add", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams(eventData)
        }).then(() => {
            updateCalendar();
            document.getElementById("eventModal").style.display = "none";
        });
    });

    document.getElementById("openModalBtn").addEventListener("click", () => {
        document.getElementById("eventModal").style.display = "block";
    });

    document.querySelector(".close").addEventListener("click", () => {
        document.getElementById("eventModal").style.display = "none";
    });

    updateCalendar();
});
