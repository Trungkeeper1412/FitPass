let stompClient = null;
let notificationCount = 0;
$(document).ready(function () {
    connect();

    $("#notificationCounter").click(function () {
        notificationCount = 0;
        updateNotificationDisplay();
    });
});

function updateNotificationDisplay() {
    document.getElementById("notificationCounter").innerText = notificationCount;
}

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected to " + frame);
        stompClient.subscribe('/all/messages', function (message) {
            showNotificationMessage(message);
        });
        stompClient.subscribe('/user/specific/private-response', function (message) {
            if (message.body) {
                showNotificationMessage(message);
            } else {
                alert("Received empty message");
            }

        }, function (error){
            console.error("Error occurred while subscribing:", error);
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
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
    console.log("Received message:", message);
    let notification = JSON.parse(message.body);
    if (notification.messageType != null) {
        switch (notification.messageType) {
            case "Thông báo checkin thành công tới employee":
                toastr["success"](notification.message, "Xác nhận check in");
                addNotificationToDropdown(1,notification.timeSend, notification.message, "#"); // Adjust the link as needed
                break;
            case "Thông báo hủy checkin tới employee":
                toastr["error"](notification.message, "Xác nhận hủy check in");
                addNotificationToDropdown(2,notification.timeSend, notification.message, "#");
                break;
            case "Thông báo checkout thành công tới employee":
                toastr["success"](notification.message, "Xác nhận check out");
                addNotificationToDropdown(3,notification.timeSend, notification.message, "#");
                break;
            case "Thông báo hủy checkout tới employee":
                toastr["warning"](notification.message, "Xác nhận hủy check out");
                addNotificationToDropdown(4,notification.timeSend, notification.message, "#");
                break;
            default:
                console.warn("Unknown messageType:", notification.messageType);
                toastr["info"](notification.message, "Unknown Message Type");
                break;
        }
    } else {
        console.warn("messageType is undefined or null, or might be ");
    }
}

// Function to add an <a> tag to the dropdown
function addNotificationToDropdown(notificationType, timeSend, message, link) {
    //Type for notification generation
    let successCheckIn = 1;
    let cancelCheckIn = 2;
    let successCheckOut = 3;
    let cancelCheckOut = 4;

    const dropdown = document.getElementById("employee-notification-dropdown");
    // Create an <a> element
    const notificationLink = document.createElement("a");
    notificationLink.className = "dropdown-item d-flex align-items-center";
    notificationLink.href = link;

    // Declare innerHTML before the switch statement
    let innerHTML = null;

    // Create the content for the <a> element based on notificationType
    switch (notificationType) {
        case successCheckIn:
            innerHTML = `
                    <div class="mr-3">
                        <div class="icon-circle bg-success">
                            <i class="fas fa-solid fa-check"></i>
                        </div>
                    </div>
                    <div>
                        <div class="small text-gray-500">${timeSend}</div>
                        ${message}
                    </div>
                    `;
            break;
        case cancelCheckIn:
            innerHTML = `
                    <div class="mr-3">
                        <div class="icon-circle bg-warning">
                            <i class="fas fa-solid fa-circle-info"></i>
                        </div>
                    </div>
                    <div>
                        <div class="small text-gray-500">${timeSend}</div>
                        ${message}
                    </div>
                    `;
            break;
        case successCheckOut:
            innerHTML = `
                    <div class="mr-3">
                        <div class="icon-circle bg-success">
                            <i class="fas fa-donate text-white"></i>
                        </div>
                    </div>
                    <div>
                        <div class="small text-gray-500">${timeSend}</div>
                        ${message}
                    </div>
                    `;
            break;
        case cancelCheckOut:
            innerHTML = `
                    <div class="mr-3">
                        <div class="icon-circle bg-warning">
                            <i class="fas fa-solid fa-circle-info"></i>
                        </div>
                    </div>
                    <div>
                        <div class="small text-gray-500">${timeSend}</div>
                        ${message}
                    </div>
                    `;
            break;
    }

    // Set the innerHTML of the notificationLink after the switch statement
    notificationLink.innerHTML = innerHTML;

    // Append the <a> element to the dropdown
    // Find the <h6> element with the dropdown-header class
    const headerElement = dropdown.querySelector("#employee-notification-label");
    console.log(notificationLink);
    // Insert the <a> element after the <h6> element
    dropdown.insertBefore(notificationLink, headerElement.nextSibling);
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

