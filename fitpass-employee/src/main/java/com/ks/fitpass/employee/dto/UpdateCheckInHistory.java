package com.ks.fitpass.employee.dto;

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
}
