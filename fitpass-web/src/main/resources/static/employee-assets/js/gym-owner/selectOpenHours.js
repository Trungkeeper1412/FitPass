document.addEventListener("DOMContentLoaded", function () {
    var saveChangesButton = document.getElementById("saveChangesButton");
    var resultText = document.getElementById("resultText");

    function addTime() {
        var selectedTimes = [];
        var daysOfWeek = ["Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"];
        var timesSelected = false; // Flag to check if any time is selected

        // Loop through the table rows to get the selected times
        var tableRows = document.querySelectorAll("#selectOpenHoursModal .table tbody tr");
        tableRows.forEach(function (row, index) {
            var day = daysOfWeek[index];
            var startTime = row.querySelector("td:nth-child(2) input.start-time-input").value;
            var endTime = row.querySelector("td:nth-child(3) input.end-time-input").value;

            // Check if a time is selected for the current row
            if (startTime !== "" && endTime !== "") {
                timesSelected = true;
                selectedTimes.push(day + ": " + startTime + " - " + endTime);
            }
        });

        // Update the resultText with the selected times or default text
        resultText.innerHTML = timesSelected ? selectedTimes.join(", ") : "&#9679; Chưa chọn giờ mở cửa";
    }

    addTime();

    saveChangesButton.addEventListener("click", function () {
        addTime();

        var closeModalButton = document.querySelector("#selectOpenHoursModal [data-dismiss='modal']");
        closeModalButton.click();
    });

});