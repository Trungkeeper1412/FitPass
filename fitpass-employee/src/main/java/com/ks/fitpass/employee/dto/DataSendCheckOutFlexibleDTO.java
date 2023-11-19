package com.ks.fitpass.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DataSendCheckOutFlexibleDTO {
    private int orderDetailId;
    private int duration;
    private Timestamp checkInTime;
    private long checkOutTime;
    private double totalCredit;
    private String employeeMessage;
}

