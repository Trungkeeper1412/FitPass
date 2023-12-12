// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Admin
var ctx2 = document.getElementById("adminPieChart");
let totalFlex = document.getElementById("totalFlex").value;
let totalFixed = document.getElementById("totalFixed").value;
var adminPieChart = new Chart(ctx2, {
    type: 'pie', // Change type to 'pie'
    data: {
        labels: ["Gói linh hoạt", "Gói cố định"],
        datasets: [{
            data: [totalFlex, totalFixed],
            backgroundColor: ['#0AC429', '#FA9A1B'],
            hoverBackgroundColor: ['#FFCE56', '#FFCE56'],
            hoverBorderColor: "rgba(234, 236, 244, 1)",
        }],
    },
    options: {
        maintainAspectRatio: false,
        tooltips: {
            backgroundColor: "rgb(255,255,255)",
            bodyFontColor: "#858796",
            borderColor: '#dddfeb',
            borderWidth: 1,
            xPadding: 15,
            yPadding: 15,
            displayColors: false,
            caretPadding: 10,
        },
        legend: {
            display: false
        },
    },
});