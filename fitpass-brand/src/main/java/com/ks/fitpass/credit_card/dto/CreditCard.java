package com.ks.fitpass.credit_card.dto;

import lombok.Data;

@Data
public class CreditCard {
    private int creditCardId;
    private int userId;
    private String cardOwnerName;
    private String cardNumber;
    private String status;
    private String bankName;
}
