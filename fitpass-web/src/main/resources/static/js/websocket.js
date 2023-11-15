let stompClient = null;
let notificationCount = 0;
$(document).ready(function () {
    connect();

    $("#send").click(function () {
        console.log("Sending message")
        const messageContent = $("#message").val();

        $.ajax({
            url: "/send-message",
            type: "POST",
            data: JSON.stringify({"messageContent": messageContent}),
            contentType: "application/json",
            success: function (response) {
                console.log("Message sent successfully");
            },
            error: function (error) {
                console.error("Error sending message:", error);
            }
        });
    });

    $("#send-private").click(function () {
        console.log("sending private message");
        const privateMessageContent = $("#private-message").val();

        $.ajax({
            url: "/send-private-message/4",
            type: "POST",
            data: JSON.stringify({"messageContent": privateMessageContent}),
            contentType: "application/json",
            success: function (response) {
                console.log("Message sent successfully");
            },
            error: function (error) {
                console.error("Error sending message:", error);
            }
        });
    });

    $("#notifications").click(function () {
        notificationCount = 0;
        updateNotificationDisplay();
    });
});


function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected to " + frame);
        stompClient.subscribe('/all/messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });
        stompClient.subscribe('/user/all/private-messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/all/global-notifications', function (message) {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });

        stompClient.subscribe('/user/all/private-notifications', function (message) {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });
    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function updateNotificationDisplay() {
    if (notificationCount === 0) {
        $('#notifications').hide();
    } else {
        $('#notifications').show().text(notificationCount);
    }
}