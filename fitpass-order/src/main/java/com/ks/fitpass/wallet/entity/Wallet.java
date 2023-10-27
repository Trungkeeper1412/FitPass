package com.ks.fitpass.wallet.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Wallet {
    private int walletId;
    private int userId;
    private double balance;
}
