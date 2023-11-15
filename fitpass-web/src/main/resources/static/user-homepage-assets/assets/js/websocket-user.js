let stompClient = null;
let notificationCount = 0;
$(document).ready(function () {
    connect();

    $(".notification-badge").click(function () {
        notificationCount = 0;
        updateNotificationDisplay();
    });
});

function updateNotificationDisplay() {
    let notificationNum = document.querySelector(".notification-badge");
    notificationNum.textContent = notificationCount;
}

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected to " + frame);
        stompClient.subscribe('/all/messages', function (message) {
            showNotificationMessage(message);
        });
        stompClient.subscribe('/user/specific/private-messages', function (message) {
            showNotificationMessage(message);
        });

        stompClient.subscribe('/all/global-notifications', function (message) {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });

        stompClient.subscribe('/user/specific/private-notifications', function (message) {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });
    });
}



function showNotificationMessage(message) {
    console.log("Received message:", message);
    let notification = null;

    try {
        // Parse the JSON message
        const notification = JSON.parse(message.body);
        // Create a new notification div
        const notiDiv = $("<div>").addClass("noti-card col-12 mb-4");

        // Add the image to the notification div
        notiDiv.append('<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWXo8tBKTv61IkzWgar_hGTXyBlPwxG1A2bFsOPrswPHT74xV2kac_fNtMMCnhpDC9pMI&usqp=CAU">');

        // Create the noti-content div
        const notiContentDiv = $("<div>").addClass("noti-content");

        // Create and append the first part of the content (icon, location, message type)
        notiContentDiv.append(
            '<div>' +
            '<span><img style="width: 18px; height: 18px;" src="https://cdn-icons-png.flaticon.com/128/891/891012.png"></span>' +
            '<span class="fw-bold">' + notification.departmentId + ' -</span>' +
            '<span>' + notification.messageType + '</span>' +
            '</div>'
        );

        // Add the description to the noti-content div
        notiContentDiv.append('<div class="noti-description">' + notification.message + '</div>');

        // Add the time to the noti-content div
        notiContentDiv.append('<div class="noti-time">' + notification.timeSend + '</div>');

        // Append the noti-content div to the notification div
        notiDiv.append(notiContentDiv);

        // Append the notification div to the messages container
        $(".my-notifications").append(notiDiv);

        // Add a click event listener to the notification div
        notiDiv.click(function () {
            handleNotificationClick(notification);
        });

        // ... rest of the function
    } catch (error) {
        console.error("Error parsing JSON:", error);
    }
}

function handleNotificationClick(notification) {
    Swal.fire({
        title: `Xác nhận check in`,
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        reverseButtons: true,
    }).then((result) => {
        // Handle user's choice based on result.isConfirmed
        if (result.isConfirmed) {
            // User clicked "Yes," handle accordingly
            handleCheckInConfirmation(notification);
        } else {
            handleCheckInCancellation(notification);
        }
    });
}

// Function to handle check-in confirmation when user clicks "Yes" in Swal dialog
function handleCheckInConfirmation(notification) {
    sendSeenNotification(notification.notificationId);
    $.ajax({
        type: "GET",
        url: "/employee/flexible/checkin",
        data: {
            id: notification.orderDetailId,
            uis: notification.userIdReceive, //id của người dùng để xác nhận check in vào db
            uir: notification.userIdSend, //id của người nhận (là nhân viên gửi vừa nãy)
            di: notification.departmentId,
            cancel: "no"
        },
        success: function (data) {
            console.log("Đã gửi thông báo check in thành công đến nhân viên");
        },
        error: function () {
            alert("Đã xảy ra lỗi gửi check in thành công");
        }
    });
}

function handleCheckInCancellation(notification){
    sendSeenNotification(notification.notificationId);
    $.ajax({
        type: "GET",
        url: "/employee/flexible/checkin",
        data: {
            id: notification.orderDetailId,
            uis: notification.userIdReceive,
            uir: notification.userIdSend,
            di: notification.departmentId,
            cancel: "yes"
        },
        success: function (data) {
            console.log("Đã gửi thông báo hủy xác nhận check in thành công")
        },
        error: function () {
            alert("Đã xảy ra lỗi khi gửi yêu cầu hủy check in");
        }
    });
}

function sendSeenNotification(id) {
    $.ajax({
        type: "GET",
        url: `/notification/seen?id=${id}`,
        success: function (data) {
            console.log("Trạng thái update status seen notification: ", data)
        },
        error: function () {
            alert("Đã xảy ra lỗi trong quá trình update status seen notification");
        }
    });
}

