package com.ks.fitpass.order.entity.wallet;

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
