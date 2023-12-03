let stompClient = null;
let notificationCount = 0;
let globalCurrentPage = 1;
let defaultPageSize = 4;

// ******************** Create WS client & methods ************************ //
document.addEventListener('DOMContentLoaded', function () {
    connect();
    (async () => {
        try {
            const totalPages = await getTotalPages();
            renderPaginationLinks(globalCurrentPage, totalPages);

            notificationCount = await getTotalUnseenNotificationNumber();
        } catch (error) {
            console.error('Error:', error);
        }
    })();
    getNewestUnseenNotifications();
});

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected to " + frame);
        stompClient.subscribe('/all/messages', function (message) {
            insertWebSocketNotification(message);
            insertWebSocketNavbarNotification(message);
        });
        stompClient.subscribe('/user/specific/private-messages', function (message) {
            if (message.body) {
                insertWebSocketNotification(message);
                insertWebSocketNavbarNotification(message);
            } else {
                alert("Received empty message");
            }
        }, function (error) {
            console.error("Error occurred while subscribing:", error);
        });

        stompClient.subscribe('/all/global-notifications', function () {
            notificationCount = notificationCount + 1;
            updateNotificationBellDisplay();
        });

        stompClient.subscribe('/user/specific/private-notifications', function () {
            notificationCount = notificationCount + 1;
            updateNotificationBellDisplay();
        });
    });
}

async function getTotalPages() {
    try {
        const response = await fetch(`/notification/user/get-total-page?size=${defaultPageSize}`);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error('Error fetching total pages:', error);
        throw error; // Re-throw the error to be caught by the caller if needed
    }
}

async function getTotalUnseenNotificationNumber() {
    try {
        const response = await fetch(`/notification/user/get-total-unseen`);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error('Error fetching total notifications number:', error);
        throw error;
    }
}

function updateNotificationBellDisplay() {
    let notificationNum = document.querySelector(".notification-badge")
    notificationNum.textContent = notificationCount;
    if (notificationCount === 0) {
        const noNotificationElement = document.createElement('li');
        noNotificationElement.classList.add('no-notification');

        const iconElement = document.createElement('img');
        iconElement.classList.add('notification-icon');
        iconElement.setAttribute('src', '/user-homepage-assets/assets/img/smile-bell.png');
        iconElement.setAttribute('alt', 'Nothing');

        const titleElement = document.createElement('p');
        titleElement.classList.add('notification-title');
        titleElement.textContent = 'Bạn không có thông báo mới nào';

        const descriptionElement = document.createElement('p');
        descriptionElement.classList.add('notification-description');
        descriptionElement.textContent = 'Hãy quay lại sau nhé';

        noNotificationElement.appendChild(iconElement);
        noNotificationElement.appendChild(titleElement);
        noNotificationElement.appendChild(descriptionElement);

        $("#notification-dropdown").prepend(noNotificationElement);
    }
}

function updateCurrentPageNumber(page) {
    return globalCurrentPage = page;
}

function sendSeenNotification(id) {
    $.ajax({
        type: "GET",
        url: `/notification/seen?id=${id}`,
        success: function (data) {
            notificationCount = notificationCount - 1
            updateNotificationBellDisplay()
            console.log("Trạng thái update status seen notification: ", data)
        },
        error: function () {
            alert("Đã xảy ra lỗi trong quá trình update status seen notification");
        }
    });
}

// ******************** WebSocket Notification Handle ************************ //

function insertWebSocketNotification(message) {
    let notification;
    try {
        notification = JSON.parse(message.body);
        if (notification.messageType != null) {
            switch (notification.messageType) {
                // type prepend = insert to the top (for ws new notification)
                case "Xác nhận check in":
                    // Fetch total pages
                    (async () => {
                        try {
                            const totalPages = await getTotalPages();

                            // If the page number is 1, remove the oldest notification
                            if (globalCurrentPage === 1) {
                                removeOldestNotificationFromPage();
                                insertCheckInNotificationDiv(notification, "prepend");
                            }

                            // Render pagination links
                            renderPaginationLinks(globalCurrentPage, totalPages);
                        } catch (error) {
                            console.error('Error:', error);
                        }
                    })();
                    break;
                case "Xác nhận check out":
                    (async () => {
                        try {
                            const totalPages = await getTotalPages();

                            // If the page number is 1, remove the oldest notification
                            if (globalCurrentPage === 1) {
                                removeOldestNotificationFromPage();
                                insertCheckOutNotificationDiv(notification, "prepend");
                            }

                            // Render pagination links
                            renderPaginationLinks(globalCurrentPage, totalPages);
                        } catch (error) {
                            console.error('Error:', error);
                        }
                    })();

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

function removeOldestNotificationFromPage() {
    const notificationsContainer = document.querySelector('.my-notifications');

    // Check if the page is 1
    if (globalCurrentPage === 1) {
        // Select all notification cards on the first page
        const notificationCards = notificationsContainer.querySelectorAll('.noti-card');
        // If there are more than 4 notifications, remove the oldest one
        if (notificationCards.length >= 4) {
            // Remove the last notification (oldest) from the DOM
            notificationsContainer.removeChild(notificationCards[notificationCards.length - 1]);
        }
    }
}

function insertNotificationNavDiv(notification) {
    const noNotificationElement = document.querySelector('#notification-dropdown .no-notification');
    if (noNotificationElement) {
        noNotificationElement.remove();
    }
    const notificationsContainer = document.querySelector('#notification-dropdown');
    const notificationNavCards = notificationsContainer.querySelectorAll('.notification-card-nav');
    // If there are more than 3 notifications, remove the oldest one
    if (notificationNavCards.length >= 3) {
        // Remove the last notification (oldest) from the DOM
        notificationsContainer.removeChild(notificationNavCards[notificationNavCards.length - 1]);
    }

    const navItem = document.createElement('li');
    navItem.className = 'notification-dropdown-card';
    navItem.setAttribute('nav-notification-id', notification.notificationId);
    navItem.innerHTML = `
            <a href="/profile/my-notifications" class="notification-card-nav">
                <img src="/user-homepage-assets/assets/img/new-message.png" alt="Alert">
                <div>  
                    <div class="notification-card-nav-time">${notification.timeSend}</div>
                    <div class="notification-card-nav-title">${notification.messageType}</div>
                </div>
            </a>
        `;
    notificationsContainer.prepend(navItem);
}

function insertWebSocketNavbarNotification(message) {
    let notification;

    try {
        notification = JSON.parse(message.body);
        if (notification.messageType != null) {
            try {
                insertNotificationNavDiv(notification);
            } catch (error) {
                console.error('Error inserting notification nav div:', error);
            }
        } else {
            console.log("Null message type");
        }
    } catch (error) {
        console.error("Error parsing JSON:", error);
    }
}

// ******************** Load newest notification for navbar bell ************************ //
function getNewestUnseenNotifications() {
    // Make an AJAX request to the backend endpoint
    fetch('/notification/user/newest-unseen')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const notifications = data.notifications;
            const size = data.size;

            if (size !== 0) {
                // Handle the received notifications
                const noNotificationElement = document.querySelector('#notification-dropdown .no-notification');
                if (noNotificationElement) {
                    noNotificationElement.remove();
                }
                loadNotificationDropdown(notifications);
            } else {
                const noNotificationElement = document.createElement('li');
                noNotificationElement.classList.add('no-notification');

                const iconElement = document.createElement('img');
                iconElement.classList.add('notification-icon');
                iconElement.setAttribute('src', '/user-homepage-assets/assets/img/smile-bell.png');
                iconElement.setAttribute('alt', 'Nothing');

                const titleElement = document.createElement('p');
                titleElement.classList.add('notification-title');
                titleElement.textContent = 'Bạn không có thông báo mới nào';

                const descriptionElement = document.createElement('p');
                descriptionElement.classList.add('notification-description');
                descriptionElement.textContent = 'Hãy quay lại sau nhé';

                noNotificationElement.appendChild(iconElement);
                noNotificationElement.appendChild(titleElement);
                noNotificationElement.appendChild(descriptionElement);

                $("#notification-dropdown").prepend(noNotificationElement);

            }
        })
        .catch(error => {
            console.error('Error fetching newest unseen notifications:', error);
        });
}

function loadNotificationDropdown(unseenNotifications) {
    //Empty notification dropdown
    const notificationsContainer = document.querySelector('#notification-dropdown');
    const notificationNavCards = notificationsContainer.querySelectorAll('.notification-card-nav');

    notificationNavCards.forEach((card) => {
        card.remove();
    });

    // Iterate over notifications and append them to the dropdown
    unseenNotifications.forEach(notification => {
        const listItem = document.createElement('li');
        listItem.className = 'notification-dropdown-card';
        listItem.setAttribute('nav-notification-id', notification.notificationId);
        listItem.innerHTML = `
            <a href="/profile/my-notifications" class="notification-card-nav">
                <img src="/user-homepage-assets/assets/img/new-message.png" alt="Alert">
                <div>  
                    <div class="notification-card-nav-time">${new Date(notification.timeSend).toLocaleString()}</div>
                    <div class="notification-card-nav-title">${notification.messageType}</div>
                </div>
            </a>
        `;
        $("#notification-dropdown").prepend(listItem);
    });
}

function deleteNavNotificationItem(notificationId) {
    const notificationItem = $(`.notification-dropdown-card[nav-notification-id="${notificationId}"]`);
    notificationItem.remove();
}

// ******************** Load List of notifications for Notification Page ************************ //
if (window.location.pathname === '/profile/my-notifications') {
    fetch(`/notification/user/all?page=${globalCurrentPage}&size=${defaultPageSize}`)
        .then(response => response.json())
        .then(data => {
            renderPaginationLinks(data.currentPage, data.totalPages); // Render pagination links
            loadNotificationsFromDB(data.currentPage, defaultPageSize)
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
    updateCurrentPageNumber(page)
    loadNotificationsFromDB(page, defaultPageSize);
}

function loadNotificationsFromDB(page, size) {
    fetch(`/notification/user/all?page=${page}&size=${size}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
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
    const pagination = document.querySelector("#notification-list .pagination");

    if(notifications.length === 0 ) {
        $(".my-notifications").html('' +
            '<div class="empty-notification">\n' +
            '            <img src="/user-homepage-assets/assets/img/no-notification.png" alt="Image">\n' +
            '                <h2>Không có thông báo nào</h2>\n' +
            '                <p>Hãy quay lại sau nhé</p>\n' +
            '        </div>');
        if (pagination) {
            pagination.style.boxShadow = "none";
        }
    } else{
        $(".my-notifications").html('');
        if (pagination) {
            pagination.style.boxShadow = "0 2px 5px rgba(0, 0, 0, 0.2)";
        }
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
}

// ******************** Function to handle check in notification ************************ //
function insertCheckInNotificationDiv(notification, order) {
    // Create a new notification div with data-notification-id attribute
    const notiDiv = $("<div>")
        .addClass(`noti-card col-12 mb-4 ${notification.status === 0 ? 'unseen-notification' : 'seen-notification'}`)
        .attr("data-notification-id", notification.notificationId);

    // Add the image to the notification div
    notiDiv.append(`<img src=${notification.departmentLogoUrl} alt="Gym Logo">`);

    // Create the noti-content div
    const notiContentDiv = $("<div>").addClass("noti-content");

    // Create and append the first part of the content (icon, location, message type)
    notiContentDiv.append(
        '<div>' +
        '<span><img style="width: 18px; height: 18px;" src="/user-homepage-assets/assets/img/small-bell.png" alt="Bell icon"></span>' +
        '<span class="fw-bold">' + notification.departmentName + ' - </span>' +
        '<span class="noti-message-type">' + notification.messageType + '</span>' +
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
            url: "/confirm/checkin",
            data: {
                id: notification.orderDetailId,
                uis: notification.userIdReceive, //id của người dùng để xác nhận check in vào db
                uir: notification.userIdSend, //id của người nhận (là nhân viên gửi vừa nãy)
                di: notification.departmentId,
                cancel: "no"
            },
            success: function () {
                console.log("Đã gửi thông báo check in thành công đến nhân viên");
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");
                deleteNavNotificationItem(notification.notificationId);
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
            url: "/confirm/checkin",
            data: {
                id: notification.orderDetailId,
                uis: notification.userIdReceive,
                uir: notification.userIdSend,
                di: notification.departmentId,
                cancel: "yes"
            },
            success: function () {
                console.log("Đã gửi thông báo hủy xác nhận check in thành công")
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");
                deleteNavNotificationItem(notification.notificationId);
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
    notiDiv.append(`<img src=${notification.departmentLogoUrl} alt="Gym Logo">`);

    // Create the noti-content div
    const notiContentDiv = $("<div>").addClass("noti-content");

    // Create and append the first part of the content (icon, location, message type)
    notiContentDiv.append(
        '<div>' +
        '<span><img style="width: 18px; height: 18px;" src="/user-homepage-assets/assets/img/small-bell.png" alt="Bell icon"></span>' +
        '<span class="fw-bold">' + notification.departmentName + ' -</span>' +
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
        $(".my-notifications").append(notiDiv);
    } else if (order === "prepend") {
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
                    confirmButtonText: '<a href="profile/deposit/">Nạp thêm credit</a>',
                    cancelButtonText: 'No',
                    reverseButtons: true,
                    allowEscapeKey: false,
                    allowOutsideClick: false
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
                    allowEscapeKey: false,
                    allowOutsideClick: false
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
            url: `/confirm/checkout`,
            headers: {
                "Content-Type": "application/json",
            },
            data: JSON.stringify(dataToSend),
            success: function (data) {
                console.log("Update check out time vào db check in history thành công", data)
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");
                deleteNavNotificationItem(notification.notificationId);

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
            url: `/confirm/checkout`,
            headers: {
                "Content-Type": "application/json",
            },
            data: JSON.stringify(dataToSend),
            success: function (data) {
                console.log("Update hủy check out thành công", data)
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");
                deleteNavNotificationItem(notification.notificationId);
            },
            error: function () {
                alert("Đã xảy ra lỗi trong quá trình hủy check out");
            }
        });
    }
}




