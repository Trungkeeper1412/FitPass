package com.ks.fitpass.checkInHistory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckInHistoryFixed {
    private String username;
    private String userImageUrl;
    private String phoneNumber;
    private String empName;
    private Timestamp checkInTime;
    private String gymPlanName;
    private double credit;
}
