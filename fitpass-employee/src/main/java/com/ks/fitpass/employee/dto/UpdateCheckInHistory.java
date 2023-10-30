package com.ks.fitpass.employee.dto;

import com.ks.fitpass.notification.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCheckInHistory {
    private int orderDetailId;
    private int checkInHistoryId;
    private Timestamp checkOutTime;
    private double totalCredit;
    private double creditAfterPay;
    private Notification notification;
    private String cancel;
}
