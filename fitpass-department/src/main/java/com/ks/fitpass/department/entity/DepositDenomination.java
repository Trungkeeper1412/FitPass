package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositDenomination {
    private int depositDenominationId;
    private int credit;
    private int money;
    private int depositDenominationStatus;
}
