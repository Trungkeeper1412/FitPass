package com.ks.fitpass.department.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymPlanDto {
    private String gymPlanType;
    private String gymPlanName;
    private String gymPlanDescription;
    private double price;
    private double pricePerHours;
    private int planBeforeActiveValidity;
    private int planAfterActiveValidity;
}