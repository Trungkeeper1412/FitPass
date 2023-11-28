let stompClient = null;
let notificationCount = 0;

// ******************** Create WS client ************************ //
$(document).ready(function () {
    connect();
});

function updateNotificationDisplay() {
    let notificationNum = document.querySelector(".notification-badge")
    notificationNum.textContent = notificationCount;
}
// ******************** Create WS client ************************ //
function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected to " + frame);
        stompClient.subscribe('/all/messages', function (message) {
            insertWebSocketNotification(message);
        });
        stompClient.subscribe('/user/specific/private-messages', function (message) {
            insertWebSocketNotification(message);
        });

        stompClient.subscribe('/all/global-notifications', function () {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });

        stompClient.subscribe('/user/specific/private-notifications', function () {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });
    });
}

function insertWebSocketNotification(message) {
    console.log("Received message:", message);

    let notification;

    try {
        // Check if the message is already a JavaScript object
        if (typeof message === 'object') {
            notification = message; // Assuming the message is already a JavaScript object
        } else {
            // Parse the message as JSON
            notification = JSON.parse(message.body);
        }

        if (notification.messageType != null) {
            switch (notification.messageType) {
                // type prepend = insert to the top (for ws new notification)
                case "Xác nhận check in":
                    insertCheckInNotificationDiv(notification, "prepend");
                    break;
                case "Xác nhận check out":
                    insertCheckOutNotificationDiv(notification, "prepend");
                    break;
                default:
                    console.log("Unknown message type");
                    break;
            }
        } else {
            console.log("Null message type");
        }
    } catch (error) {
        console.error("Error parsing JSON:", error);
    }
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

// ******************** Load newest notification for navbar bell ************************ //
document.addEventListener('DOMContentLoaded', function () {
    // Lấy ra các notification unseen id = 0
    getNewestUnseenNotifications();
});

function getNewestUnseenNotifications() {
    // Make an AJAX request to the backend endpoint
    fetch('/notification/user/newest-unseen')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(notifications => {
            // Handle the received notifications
            updateNotificationDropdown(notifications);
        })
        .catch(error => {
            console.error('Error fetching newest unseen notifications:', error);
        });
}

function updateNotificationDropdown(unseenNotifications) {
    // Assuming 'notification-badge' is the element displaying the notification count
    const notificationBadge = document.getElementById('notification-badge');

    // Assuming 'notification-dropdown' is the container for notification items
    const notificationDropdown = document.getElementById('notification-dropdown');

    // Update the badge count
    notificationBadge.innerText = unseenNotifications.length;

    // Clear existing notification items
    notificationDropdown.innerHTML = '';

    // Iterate over notifications and append them to the dropdown
    unseenNotifications.forEach(notification => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <a href="/profile/my-notifications" class="noti">
                <img src="/user-homepage-assets/assets/img/new-message.png" alt="Alert">
                <div>  
                    <div class="noti-time">${notification.timeSend}</div>
                    <div class="noti-title">${notification.messageType}</div>
                </div>
            </a>
        `;
        notificationDropdown.appendChild(listItem);
    });

    // Add a "View All" button
    const viewAllItem = document.createElement('li');
    viewAllItem.className = 'view-all-noti';
    viewAllItem.innerHTML = '<button>Xem tất cả</button>';
    notificationDropdown.appendChild(viewAllItem);

    viewAllItem.addEventListener('click', function () {
        // Navigate to the "/my-notifications" URL
        window.location.href = '/profile/my-notifications';
    });
}

// ******************** Load List of notifications for Notification Page ************************ //
if (window.location.pathname === '/profile/my-notifications') {
    fetch('/notification/user/all?page=1&size=4')
        .then(response => response.json())
        .then(data => {
            renderPaginationLinks(data.totalPages); // Render pagination links
            getAllNotification(1,4)
        })
        .catch(error => {
            console.error('Error fetching notifications:', error);
        });
}
// Function to render pagination links
function renderPaginationLinks(currentPage, totalPages) {
    const paginationContainer = document.getElementById('paginationNotification');
    paginationContainer.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('a');
        pageLink.href = '#';
        pageLink.textContent = i;

        // Add the 'active' class if the current page matches the loop variable
        if (i === currentPage) {
            pageLink.classList.add('active');
        }

        pageLink.addEventListener('click', () => paginationClick(i));
        paginationContainer.appendChild(pageLink);
    }
}

// Function to handle pagination clicks
function paginationClick(page) {
    getAllNotification(page, 4);
}
function getAllNotification(page, size) {
    fetch(`/notification/user/all?page=${page}&size=${size}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data  => {
            const notifications = data.notifications; // Assuming the notifications are in the 'notifications' field
            const totalPages = data.totalPages;
            renderPaginationLinks(page, totalPages); // Pass current page and total pages to the render function
            renderPage(notifications);
        })
        .catch(error => {
            console.error('Error fetching notifications:', error);
        });
}
function renderPage(notifications) {
    $(".my-notifications").html('');
    notifications.forEach(notification => {
            if (notification.messageType != null) {
                switch (notification.messageType) {
                    // append = from top to bottom, for showing a list
                    case "Xác nhận check in":
                        insertCheckInNotificationDiv(notification, "append");
                        break;
                    case "Xác nhận check out":
                        insertCheckOutNotificationDiv(notification, "append");
                        break;
                    default:
                        console.log("Unknown message type");
                        break;
                }
            } else {
                console.log("Null message type");
            }
    })
}

// ******************** Function to handle check in notification ************************ //
function insertCheckInNotificationDiv(notification, order) {
    // Create a new notification div with data-notification-id attribute
    const notiDiv = $("<div>")
        .addClass(`noti-card col-12 mb-4 ${notification.status === 0 ? 'unseen-notification' : 'seen-notification'}`)
        .attr("data-notification-id", notification.notificationId);

    // Add the image to the notification div
    notiDiv.append('<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWXo8tBKTv61IkzWgar_hGTXyBlPwxG1A2bFsOPrswPHT74xV2kac_fNtMMCnhpDC9pMI&usqp=CAU"'
    +'alt="Gym Logo">');

    // Create the noti-content div
    const notiContentDiv = $("<div>").addClass("noti-content");

    // Create and append the first part of the content (icon, location, message type)
    notiContentDiv.append(
        '<div>' +
        '<span><img style="width: 18px; height: 18px;" src="/user-homepage-assets/assets/img/small-bell.png" alt="Bell icon"></span>' +
        '<span class="fw-bold">' + notification.departmentId + ' -</span>' +
        '<span>' + notification.messageType + '</span>' +
        '</div>'
    );

    // Add the description to the noti-content div
    notiContentDiv.append('<div class="noti-description">' + notification.message + '</div>');

    // Add the time to the noti-content div
    notiContentDiv.append('<div class="noti-time">' + new Date(notification.timeSend).toLocaleString() + '</div>');

    notiDiv.append(notiContentDiv);

    // Depending on the order parameter, append or prepend notiContentDiv
    if (order === "append") {
        $(".my-notifications").append(notiDiv);
    } else if (order === "prepend") {
        $(".my-notifications").prepend(notiDiv);
    } else {
        throw new Error("Invalid order parameter. Use 'append' or 'prepend'.");
    }

    // Add a click event listener to the notification div
    notiDiv.click(function () {
        handleCheckInNotificationClick(notification);
    });
}

function handleCheckInNotificationClick(notification) {
    // Check if the notification is already seen
    const isSeen = $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`).hasClass("seen-notification");

    if (!isSeen) {
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
    } else {
        // The notification is already seen, handle accordingly or do nothing
        console.log("Notification is already seen. No action taken.");
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
            success: function () {
                console.log("Đã gửi thông báo check in thành công đến nhân viên");
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`).removeClass("unseen-notification").addClass("seen-notification");
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
            success: function () {
                console.log("Đã gửi thông báo hủy xác nhận check in thành công")
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`).removeClass("unseen-notification").addClass("seen-notification");
            },
            error: function () {
                alert("Đã xảy ra lỗi khi gửi yêu cầu hủy check in");
            }
        });
    }
}

// ******************** Function to handle check out notification ************************ //
function insertCheckOutNotificationDiv(notification, order) {
    // Parse the JSON strings from 2 object in message field
    let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
    let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
    let dataSendCheckOutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);

    // Create a new notification div with data-notification-id attribute
    const notiDiv = $("<div>")
        .addClass(`noti-card col-12 mb-4 ${notification.status === 0 ? 'unseen-notification' : 'seen-notification'}`)
        .attr("data-notification-id", notification.notificationId);

    // Add the image to the notification div
    notiDiv.append('<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWXo8tBKTv61IkzWgar_hGTXyBlPwxG1A2bFsOPrswPHT74xV2kac_fNtMMCnhpDC9pMI&usqp=CAU"'
    + 'alt="Gym Logo">');

    // Create the noti-content div
    const notiContentDiv = $("<div>").addClass("noti-content");

    // Create and append the first part of the content (icon, location, message type)
    notiContentDiv.append(
        '<div>' +
        '<span><img style="width: 18px; height: 18px;" src="/user-homepage-assets/assets/img/small-bell.png" alt="Bell icon"></span>' +
        '<span class="fw-bold">' + notification.departmentId + ' -</span>' +
        '<span>' + notification.messageType + '</span>' +
        '</div>'
    );

    // Add the description to the noti-content div
    notiContentDiv.append('<div class="noti-description">' + dataSendCheckOutFlexible.employeeMessage + '</div>');

    // Add the time to the noti-content div
    notiContentDiv.append('<div class="noti-time">' + new Date(notification.timeSend).toLocaleString() + '</div>');

    notiDiv.append(notiContentDiv);

    // Depending on the order parameter, append or prepend notiContentDiv
    if (order === "append") {
        console.log("Append chose")
        $(".my-notifications").append(notiDiv);
    } else if (order === "prepend") {
        console.log("Prepend chose")
        $(".my-notifications").prepend(notiDiv);
    } else {
        throw new Error("Invalid order parameter. Use 'append' or 'prepend'.");
    }

    // Add a click event listener to the notification div
    notiDiv.click(function () {
        handleCheckOutNotificationClick(notification);
    });
}

function handleCheckOutNotificationClick(notification) {
    // Check if the notification is already seen
    const isSeen = $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`).hasClass("seen-notification");

    if (!isSeen) {
        let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
        let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
        let dataSendCheckOutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);

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
    } else {
        // The notification is already seen, handle accordingly or do nothing
        console.log("Notification is already seen. No action taken.");
    }


    // Function to handle check-in confirmation when user clicks "Yes" in Swal dialog
    function handleCheckOutConfirmation(notification) {
        sendSeenNotification(notification.notificationId);
        let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
        let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
        let dataSendCheckOutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);

        const dataToSend = {
            orderDetailId: orderDetailConfirmCheckOut.orderDetailId,
            checkInHistoryId: orderDetailConfirmCheckOut.historyCheckInId,
            checkOutTime: orderDetailConfirmCheckOut.checkOutTime,
            totalCredit: orderDetailConfirmCheckOut.creditNeedToPay,
            creditAfterPay: orderDetailConfirmCheckOut.creditAfterPay,
            notification: {
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
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");

            },
            error: function () {
                alert("Đã xảy ra lỗi trong quá trình xác nhận check out");
            }
        });
    }

    // Function to handle check-in confirmation when user clicks "No" in Swal dialog
    function handleCheckOutCancellation(notification) {
        sendSeenNotification(notification.notificationId);
        let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
        let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
        let dataSendCheckOutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);
        const dataToSend = {
            orderDetailId: orderDetailConfirmCheckOut.orderDetailId,
            checkInHistoryId: orderDetailConfirmCheckOut.historyCheckInId,
            checkOutTime: orderDetailConfirmCheckOut.checkOutTime,
            totalCredit: orderDetailConfirmCheckOut.creditNeedToPay,
            creditAfterPay: orderDetailConfirmCheckOut.creditAfterPay,
            notification: {
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
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");
            },
            error: function () {
                alert("Đã xảy ra lỗi trong quá trình hủy check out");
            }
        });
    }
}




