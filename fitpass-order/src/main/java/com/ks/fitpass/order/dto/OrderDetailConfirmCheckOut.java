package com.ks.fitpass.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailConfirmCheckOut {
    private int orderDetailId;
    private int historyCheckInId;
    private int notificationId;
    private String departmentName;
    private String gymPlanName;
    private double pricePerHours;
    private int durationHavePractice;
    private double creditInWallet;
    private double creditNeedToPay;
    private double creditAfterPay;
    private Timestamp checkOutTime;
}
