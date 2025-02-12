document.addEventListener("DOMContentLoaded", function () {
    let currentDate = new Date();

    function updateCalendar() {
        const year = currentDate.getFullYear();
        const month = currentDate.getMonth();
        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();

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
                    let eventDate = new Date(event.startDate);
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
            });
    }

    updateCalendar();
});
