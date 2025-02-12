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
            cell.innerText = date;
            cell.setAttribute("data-date", formattedDate);
            cell.addEventListener("click", () => showEventDetails(formattedDate));

            // 📌 오늘 날짜 노란색 강조
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

    window.changeMonth = function (change) {
        currentDate.setMonth(currentDate.getMonth() + change);
        updateCalendar();
    };

    document.getElementById("openEventPopupBtn").addEventListener("click", function () {
        let popupUrl = "/calendar/event-form";
        let popupOptions = "width=500,height=600,top=100,left=200,scrollbars=yes";
        window.open(popupUrl, "EventPopup", popupOptions);
    });

    updateCalendar();
});
