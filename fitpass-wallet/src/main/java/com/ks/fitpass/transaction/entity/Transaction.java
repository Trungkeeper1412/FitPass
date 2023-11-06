package com.ks.fitpass.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    private int transactionId;
    private int walletId;
    private int amount;
    private Timestamp transactionDate;
    private String status;
}
