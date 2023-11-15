package com.ks.fitpass.notification.entity;

import com.ks.fitpass.employee.dto.DataSendCheckOutFlexibleDTO;
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
    private String messageType;
    private DataSendCheckOutFlexibleDTO dataSendCheckOutFlexibleDTO;
    private int status;
}
