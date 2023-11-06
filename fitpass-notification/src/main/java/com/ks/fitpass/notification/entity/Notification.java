package com.ks.fitpass.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Notification {
    private int notificationId;
    private int userIdSend;
    private int userIdReceive;
    private String message;
    private Timestamp timeSend;
    private int departmentId;
    private String messageType;
    private int status;
}
