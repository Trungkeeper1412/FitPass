package com.ks.fitpass.request_withdrawal_history.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequestHistoryBrandAdmin {
    private String brandName;
    private int totalRequest;
    private Long amountCredit;
    private Long actualMoney;
}
