package com.ks.fitpass.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferCreditHistory {
    private int transferCreditHistoryId;
    private int senderUserId;
    private int receiverUserId;
    private double amount;
    private int orderDetailId;
    private Timestamp transferDate;
}
