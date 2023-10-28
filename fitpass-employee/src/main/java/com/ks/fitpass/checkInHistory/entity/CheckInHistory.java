package com.ks.fitpass.checkInHistory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckInHistory {
    private int checkInHistoryId;
    private int orderDetailId;
    private int statusKey;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private double totalCredit;
    private int empCheckinId;
}
