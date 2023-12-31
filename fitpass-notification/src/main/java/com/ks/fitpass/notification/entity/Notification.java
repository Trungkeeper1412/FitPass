package com.ks.fitpass.notification.entity;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Notification {
    private int notificationId;
    private int orderDetailId;
    private int userIdSend;
    private int userIdReceive;
    private String message;
    private Timestamp timeSend;
    private int departmentId;
    private String departmentName;
    private String departmentLogoUrl;
    private String employeeName;
    private String messageType;
    private int status;
}
