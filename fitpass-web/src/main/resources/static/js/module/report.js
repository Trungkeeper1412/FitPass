
function confirmOutputReport(month) {
    document.getElementById("btn-confirm-output-report-order").href = "/report/order?month=" + month;
    openModal("confirmOutputReportModal");
}
