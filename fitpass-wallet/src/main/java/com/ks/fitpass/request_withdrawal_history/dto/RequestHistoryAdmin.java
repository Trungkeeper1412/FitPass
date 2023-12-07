package com.ks.fitpass.request_withdrawal_history.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class RequestHistoryAdmin {
    private int requestHistoryId;
    private Long amountCredit;
    private Long actualMoney;
    private String brandName;
    private String cardBank;
    private String cardNumber;
    private String cardHolder;
    private double brandBalance;
}
