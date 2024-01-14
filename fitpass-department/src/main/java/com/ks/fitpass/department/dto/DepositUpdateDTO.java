package com.ks.fitpass.department.dto;

import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositUpdateDTO {

    @NotNull(message = "Vui lòng nhập credit !")
    private int credit;

    @NotNull(message = "Vui lòng nhập mệnh giá !")
    private int money;

    private int depositDenominationId;
}
