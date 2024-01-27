let stompClient = null;
let globalCurrentPage = 1;
let defaultPageSize = 4;

// ******************** Create WS client & methods ************************ //
document.addEventListener('DOMContentLoaded', function () {
    connect();
    (async () => {
        try {
            const totalPages = await getTotalPages();
            renderPaginationLinks(globalCurrentPage, totalPages);
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
            insertWebSocketNotification(message);
            insertWebSocketNavbarNotification(message);
        }, function (error) {
            console.error("Error occurred while subscribing:", error);
        });

        stompClient.subscribe('/all/global-notifications', function () {
            notificationCount = notificationCount + 1;
            updateNotificationBellDisplay(notificationCount);
        });

        stompClient.subscribe('/user/specific/private-notifications', function () {
            console.log(notificationCount)
            notificationCount = notificationCount + 1;
            updateNotificationBellDisplay(notificationCount);
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

function getTotalUnseenNotificationNumber() {
    return fetch(`/notification/user/get-total-unseen`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .catch(error => {
            console.error('Error fetching total notifications number:', error);
            throw error;
        });
}

function updateNotificationBellDisplay(notificationCount) {
    notificationCountElement.textContent = notificationCount;
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
            updateNotificationBellDisplay(notificationCount)
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
        notificationNavCards[notificationNavCards.length - 1].remove();
    }

    const navItem = document.createElement('li');
    navItem.className = 'notification-dropdown-card';
    navItem.setAttribute('nav-notification-id', notification.notificationId);
    navItem.innerHTML = `
            <a href="/profile/my-notifications" class="notification-card-nav">
                <img src="/user-homepage-assets/assets/img/new-message.png" alt="Alert">
                <div>  
                    <div class="notification-card-nav-time">${new Date(notification.timeSend).toLocaleString()}</div>
                    <div class="notification-card-nav-title">${notification.messageType}</div>
                </div>
            </a>
        `;
    notificationsContainer.prepend(navItem);
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
    // Get the "View All" button
    const viewAllButton = document.querySelector('.view-all-notification-link');

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
        notificationsContainer.insertBefore(listItem, viewAllButton);
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
function makePageLink(page, isActive = false) {
    const pageLink = document.createElement('a');
    pageLink.href = '#';
    pageLink.textContent = page;

    if (isActive) {
        pageLink.classList.add('active');
    }

    pageLink.addEventListener('click', (event) => {
        event.preventDefault();
        paginationClick(page);
    });

    return pageLink;
}

function renderPaginationLinks(currentPage, totalPages) {
    const paginationContainer = document.getElementById('paginationNotification');
    paginationContainer.innerHTML = '';

    // Determine the range of page links to be displayed (we want 5 at most)
    let startPage = Math.max(currentPage - 2, 1);
    let endPage = Math.min(startPage + 4, totalPages);

    // Adjust if we're at the end of the page range
    if (endPage === totalPages) {
        startPage = Math.max(endPage - 4, 1);
    }

    // Add a previous page link
    if (currentPage > 1) {
        const prevLink = makePageLink(currentPage - 1);
        prevLink.textContent = 'Previous';
        paginationContainer.appendChild(prevLink);
    }

    // Add a "..." link for hidden lower pages
    if (startPage > 1) {
        const dotsLink = document.createElement('span');
        dotsLink.textContent = '...';
        paginationContainer.appendChild(dotsLink);
    }

    for (let i = startPage; i <= endPage; i++) {
        const pageLink = makePageLink(i, i === currentPage);
        paginationContainer.appendChild(pageLink);
    }

    // Add a "..." link for hidden higher pages
    if (endPage < totalPages) {
        const dotsLink = document.createElement('span');
        dotsLink.textContent = '...';
        paginationContainer.appendChild(dotsLink);
    }

    // Add a next page link
    if (currentPage < totalPages) {
        const nextLink = makePageLink(currentPage + 1);
        nextLink.textContent = 'Next';
        paginationContainer.appendChild(nextLink);
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

    if (notifications.length === 0) {
        $(".my-notifications").html('' +
            '<div class="empty-notification">\n' +
            '            <img src="/user-homepage-assets/assets/img/no-notification.png" alt="Image">\n' +
            '                <h2>Không có thông báo nào</h2>\n' +
            '                <p>Hãy quay lại sau nhé</p>\n' +
            '        </div>');
        if (pagination) {
            pagination.style.boxShadow = "none";
        }
    } else {
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
            html: `
                <p class="fw-bold">Phòng tập: <span class="fw-normal">${notification.departmentName}</span></p>
                `,
            showCancelButton: true,
            confirmButtonText: 'Xác nhận',
            cancelButtonText: 'Không',
            reverseButtons: true,
            allowEscapeKey: false,
            allowOutsideClick: false
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
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "newestOnTop": false,
                    "progressBar": true,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": true,
                    "onclick": null,
                    "showDuration": "300",
                    "hideDuration": "1000",
                    "timeOut": "5000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };

                toastr.success("Đã gửi thông báo check in thành công đến nhân viên");
                // add seen class to the notification list
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");
                // read it
                deleteNavNotificationItem(notification.notificationId);
            },
            error: function () {
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "newestOnTop": false,
                    "progressBar": true,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": true,
                    "onclick": null,
                    "showDuration": "300",
                    "hideDuration": "1000",
                    "timeOut": "3000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };

                toastr.error("Đã xảy ra lỗi gửi check in thành công");
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
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "newestOnTop": false,
                    "progressBar": true,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": true,
                    "onclick": null,
                    "showDuration": "300",
                    "hideDuration": "1000",
                    "timeOut": "4000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };

                toastr.warning("Đã gửi thông báo hủy xác nhận check in thành công");
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");
                deleteNavNotificationItem(notification.notificationId);
            },
            error: function () {
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "newestOnTop": false,
                    "progressBar": true,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": true,
                    "onclick": null,
                    "showDuration": "300",
                    "hideDuration": "1000",
                    "timeOut": "3000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };

                toastr.error("Đã xảy ra lỗi khi gửi yêu cầu hủy check in")
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
        '<span class="fw-bold">' + notification.departmentName + ' - </span>' +
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
        let userBalance = parseFloat(document.getElementById('user-balance').textContent);

        if (orderDetailConfirmCheckOut.durationHavePractice > 0) {
            if (userBalance < orderDetailConfirmCheckOut.creditNeedToPay) {
                Swal.fire({
                    title: `Bạn không đủ số dư credit để thanh toán`,
                    icon: 'error',
                    html: `
                <p class="fw-bold">Phòng tập: <span class="fw-normal">${orderDetailConfirmCheckOut.departmentName}</span></p>
                <p  class="fw-bold">Gói tập: <span class="fw-normal">${orderDetailConfirmCheckOut.gymPlanName}</span></p>
                <p  class="fw-bold">Giá gói: <span class="fw-normal">${orderDetailConfirmCheckOut.pricePerHours} credit/giờ</span></p>
                <p  class="fw-bold">Đã tập: <span class="fw-normal">${orderDetailConfirmCheckOut.durationHavePractice} phút</span></p>
                <p  class="fw-bold">Số dư credit trong ví: <span class="fw-normal">${userBalance} credit</span></p>
                <p  class="fw-bold">Số credit cần trả: <span class="fw-normal">${orderDetailConfirmCheckOut.creditNeedToPay} credit</span></p>
                `,
                    showCancelButton: true,
                    confirmButtonText: '<a href="deposit">Nạp thêm credit</a>',
                    cancelButtonText: 'Không',
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
                <p  class="fw-bold">Số dư credit trong ví: <span class="fw-normal">${userBalance} credit</span></p>
                <p  class="fw-bold">Số credit cần trả: <span class="fw-normal">${orderDetailConfirmCheckOut.creditNeedToPay} credit</span></p>
                <p  class="fw-bold">Số dư credit còn lại: <span class="fw-normal">${userBalance - orderDetailConfirmCheckOut.creditNeedToPay} credit</span></p>
                `,
                    showCancelButton: true,
                    confirmButtonText: 'Xác nhận',
                    cancelButtonText: 'Không',
                    reverseButtons: true,
                    allowEscapeKey: false,
                    allowOutsideClick: false
                }).then((result) => {
                    // Handle user's choice based on result.isConfirmed
                    if (result.isConfirmed) {
                        handleCheckOutConfirmation(notification, userBalance);
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
    function handleCheckOutConfirmation(notification, userBalance) {
        sendSeenNotification(notification.notificationId);
        let [orderDetailConfirmCheckOutJson, dataSendCheckOutFlexibleJson] = notification.message.split('|');
        let orderDetailConfirmCheckOut = JSON.parse(orderDetailConfirmCheckOutJson);
        let dataSendCheckOutFlexible = JSON.parse(dataSendCheckOutFlexibleJson);

        const dataToSend = {
            orderDetailId: orderDetailConfirmCheckOut.orderDetailId,
            checkInHistoryId: orderDetailConfirmCheckOut.historyCheckInId,
            checkOutTime: orderDetailConfirmCheckOut.checkOutTime,
            totalCredit: orderDetailConfirmCheckOut.creditNeedToPay,
            creditAfterPay: userBalance - orderDetailConfirmCheckOut.creditNeedToPay,
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
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "newestOnTop": false,
                    "progressBar": false, // Turn off progress bar
                    "positionClass": "toast-bottom-right", // Change position to bottom right
                    "preventDuplicates": true,
                    "onclick": null,
                    "showDuration": "500", // Increase show duration
                    "hideDuration": "2000", // Increase hide duration
                    "timeOut": "7000", // Show for longer
                    "extendedTimeOut": "2000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "slideDown", // Change show animation
                    "hideMethod": "slideUp" // Change hide animation
                };

                toastr.success("Đã thanh toán thành công");
                $(".my-notifications").find(`[data-notification-id="${notification.notificationId}"]`)
                    .removeClass("unseen-notification").addClass("seen-notification");
                deleteNavNotificationItem(notification.notificationId);
                $(".text-money").text(parseFloat(dataToSend.creditAfterPay).toFixed(1));
                $("#user-balance").text(parseFloat(dataToSend.creditAfterPay).toFixed(1));
            },
            error: function () {
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "newestOnTop": false,
                    "progressBar": true,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": true,
                    "onclick": null,
                    "showDuration": "300",
                    "hideDuration": "1000",
                    "timeOut": "3000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };

                toastr.error("Đã xảy ra lỗi trong quá trình xác nhận check out");
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
            error: function (data) {
                console.log("Đã xảy ra lỗi trong quá trình hủy check out", data)
            }
        });
    }
}




