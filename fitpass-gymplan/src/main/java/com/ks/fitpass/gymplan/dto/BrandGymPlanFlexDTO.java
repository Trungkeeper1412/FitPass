package com.ks.fitpass.gymplan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BrandGymPlanFlexDTO {
    private int gymPlanId;
    private String gymPlanName;
    private double pricePerHours;
    private int planBeforeActive;
    private int planAfterActive;
    private int status;
    private String description;
}
