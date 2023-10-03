package com.ks.fitpass.core.entity;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Wallet implements Serializable {
    private int walletId;
    private double balance;
    private User user;
}
