package com.ks.fitpass.request_withdrawal_history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class RequestWithdrawHistory {
    private int requestHistoryId;
    private int creditCardId;
    private String withdrawalCode;
    private Timestamp withdrawalTime;
    private Long amountCredit;
    private Long actualMoney;

    private String status;
}
