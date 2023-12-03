package com.ks.fitpass.checkInHistory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CheckInHistoryFlexible {
    private String username;
    private String userImageUrl;
    private String phoneNumber;
    private String empName;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private double pricePerHours;
    private double totalCredit;
    private String duration;
}
