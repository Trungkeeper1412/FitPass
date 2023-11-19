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
    let notificationNum = document.querySelector(".notification-badge")
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
    let notification = JSON.parse(message.body);
    try {
        if (notification.messageType != null) {
            switch (notification.messageType) {
                case "Xác nhận check in":
                    insertCheckInNotificationDiv(notification);
                    break;
                case "Xác nhận check out":
                    insertCheckOutNotificationDiv(notification);
                    break;
                default:
                    console.log("Unknown message type")
                    break;
            }
        } else {
            console.log("Null message type")
        }
    } catch (error) {
        console.error("Error parsing JSON:", error);
    }
}

// ********************Function to handle check in notification************************ //
function insertCheckInNotificationDiv(notification) {
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
        handleCheckInNotificationClick(notification);
    });
}

function handleCheckInNotificationClick(notification) {
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

// Function to handle check-in confirmation when user clicks "No" in Swal dialog
function handleCheckInCancellation(notification) {
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

// ********************Function to handle check out notification************************ //
function insertCheckOutNotificationDiv(notification) {
    // Parse the JSON strings from 2 object in message field
    let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
    let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
    let dataSendCheckOutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);

    console.log(orderDetailConfirmCheckOut);
    console.log(dataSendCheckOutFlexible);

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
    notiContentDiv.append('<div class="noti-description">' + dataSendCheckOutFlexible.employeeMessage + '</div>');

    // Add the time to the noti-content div
    notiContentDiv.append('<div class="noti-time">' + notification.timeSend + '</div>');

    // Append the noti-content div to the notification div
    notiDiv.append(notiContentDiv);

    // Append the notification div to the messages container
    $(".my-notifications").append(notiDiv);

    // Add a click event listener to the notification div
    notiDiv.click(function () {
        handleCheckOutNotificationClick(notification);
    });
}

function handleCheckOutNotificationClick(notification) {
    let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
    let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
    let dataSendCheck
    OutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);

    if (orderDetailConfirmCheckOut.durationHavePractice > 0) {
        if (orderDetailConfirmCheckOut.creditAfterPay < 0) {
            Swal.fire({
                title: `Bạn không đủ số dư credit để thanh toán`,
                icon: 'error',
                html: `
                <p class="fw-bold">Phòng tập: <span class="fw-normal">${orderDetailConfirmCheckOut.departmentName}</span></p>
                <p  class="fw-bold">Gói tập: <span class="fw-normal">${orderDetailConfirmCheckOut.gymPlanName}</span></p>
                <p  class="fw-bold">Giá gói: <span class="fw-normal">${orderDetailConfirmCheckOut.pricePerHours} credit/giờ</span></p>
                <p  class="fw-bold">Đã tập: <span class="fw-normal">${orderDetailConfirmCheckOut.durationHavePractice} phút</span></p>
                <p  class="fw-bold">Số dư credit trong ví: <span class="fw-normal">${orderDetailConfirmCheckOut.creditInWallet} credit</span></p>
                <p  class="fw-bold">Số credit cần trả: <span class="fw-normal">${orderDetailConfirmCheckOut.creditNeedToPay} credit</span></p>
                <p  class="fw-bold">Số dư credit còn lại: <span class="fw-normal">${orderDetailConfirmCheckOut.creditAfterPay} credit</span></p>
                `,
                showCancelButton: true,
                confirmButtonText: 'Nạp thêm credit',
                cancelButtonText: 'No',
                reverseButtons: true,
            })
        } else {
            Swal.fire({
                title: `Xác nhận thanh toán`,
                icon: 'question',
                html: `
                <p class="fw-bold">Phòng tập: <span class="fw-normal">${orderDetailConfirmCheckOut.departmentName}</span></p>
                <p  class="fw-bold">Gói tập: <span class="fw-normal">${orderDetailConfirmCheckOut.gymPlanName}</span></p>
                <p  class="fw-bold">Giá gói: <span class="fw-normal">${orderDetailConfirmCheckOut.pricePerHours} credit/giờ</span></p>
                <p  class="fw-bold">Đã tập: <span class="fw-normal">${orderDetailConfirmCheckOut.durationHavePractice} phút</span></p>
                <p  class="fw-bold">Số dư credit trong ví: <span class="fw-normal">${orderDetailConfirmCheckOut.creditInWallet} credit</span></p>
                <p  class="fw-bold">Số credit cần trả: <span class="fw-normal">${orderDetailConfirmCheckOut.creditNeedToPay} credit</span></p>
                <p  class="fw-bold">Số dư credit còn lại: <span class="fw-normal">${orderDetailConfirmCheckOut.creditAfterPay} credit</span></p>
                `,
                showCancelButton: true,
                confirmButtonText: 'Yes',
                cancelButtonText: 'No',
                reverseButtons: true,
            }).then((result) => {
                // Handle user's choice based on result.isConfirmed
                if (result.isConfirmed) {
                    handleCheckOutConfirmation(notification);
                } else {
                    handleCheckOutCancellation(notification);
                }
            });
        }
    }

    // Function to handle check-in confirmation when user clicks "Yes" in Swal dialog
    function handleCheckOutConfirmation(notification){
        let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
        let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
        let dataSendCheckOutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);

        const dataToSend = {
            orderDetailId: orderDetailConfirmCheckOut.orderDetailId,
            checkInHistoryId: orderDetailConfirmCheckOut.historyCheckInId,
            checkOutTime: orderDetailConfirmCheckOut.checkOutTime,
            totalCredit: orderDetailConfirmCheckOut.creditNeedToPay,
            creditAfterPay: orderDetailConfirmCheckOut.creditAfterPay,
            notification : {
                orderDetailId: notification.orderDetailId,
                userIdSend: notification.userIdReceive, //id của người dùng để xác nhận check in vào db
                userIdReceive: notification.userIdSend, //id của người nhận (là nhân viên gửi vừa nãy)
                departmentId: notification.departmentId,
            },
            cancel: "No"
        };
        $.ajax({
            type: "POST",
            url: `/employee/flexible/checkout`,
            headers: {
                "Content-Type": "application/json",
            },
            data: JSON.stringify(dataToSend),
            success: function (data) {
                console.log("Update check out time vào db check in history thành công", data)
                sendSeenNotification(notification.notificationId);
            },
            error: function () {
                alert("Đã xảy ra lỗi trong quá trình xác nhận check out");
            }
        });
    }
}
// Function to handle check-in confirmation when user clicks "No" in Swal dialog
function handleCheckOutCancellation(notification) {
        let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
        let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
        let dataSendCheckOutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);
        const dataToSend = {
            orderDetailId: orderDetailConfirmCheckOut.orderDetailId,
            checkInHistoryId: orderDetailConfirmCheckOut.historyCheckInId,
            checkOutTime: orderDetailConfirmCheckOut.checkOutTime,
            totalCredit: orderDetailConfirmCheckOut.creditNeedToPay,
            creditAfterPay: orderDetailConfirmCheckOut.creditAfterPay,
            notification : {
                orderDetailId: notification.orderDetailId,
                userIdSend: notification.userIdReceive, //id của người dùng để xác nhận check in vào db
                userIdReceive: notification.userIdSend, //id của người nhận (là nhân viên gửi vừa nãy)
                departmentId: notification.departmentId,
            },
            cancel: "Yes"
        };
        $.ajax({
            type: "POST",
            url: `/employee/flexible/checkout`,
            headers: {
                "Content-Type": "application/json",
            },
            data: JSON.stringify(dataToSend),
            success: function (data) {
                console.log("Update hủy check out thành công", data)
                sendSeenNotification(notification.notificationId);
            },
            error: function () {
                alert("Đã xảy ra lỗi trong quá trình hủy check out");
            }
        });
    }

    function sendSeenNotification(id) {
        $.ajax({
            type: "GET",
            url: `/notification/seen?id=${id}`,
            success: function (data) {
                notificationCount = notificationCount - 1
                updateNotificationDisplay()
                console.log("Trạng thái update status seen notification: ", data)
            },
            error: function () {
                alert("Đã xảy ra lỗi trong quá trình update status seen notification");
            }
        });
    }

