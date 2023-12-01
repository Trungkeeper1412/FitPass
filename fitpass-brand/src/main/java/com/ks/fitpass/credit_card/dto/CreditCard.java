package com.ks.fitpass.credit_card.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreditCard {

    private int creditCardId;

    private int userId;

    @NotEmpty(message = "Department Address is required")
    private String cardOwnerName;

    @NotEmpty(message = "Department Address is required")
    private String cardNumber;

    private String status;

    @NotEmpty(message = "Department Address is required")
    private String bankName;

}
