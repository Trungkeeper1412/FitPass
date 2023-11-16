// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Gói linh hoạt", "Gói 7 ngày", "Gói 3 tháng", "Gói 6 tháng", "Gói 12 tháng"],
    datasets: [{
      data: [55, 20, 15, 8, 2],
      backgroundColor: ['#FFCE56', '#36A2EB', '#4BC0C0', '#FF6384', '#FF9F40'],
      hoverBackgroundColor: ['#FFCE56', '#36A2EB', '#4BC0C0', '#FF6384', '#FF9F40'],
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
    cutoutPercentage: 80,
  },
});
