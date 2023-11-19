package com.ks.fitpass.gymplan.dto;

import lombok.Data;

@Data
public class BrandGymPlanFixedDTO {
    private int gymPlanId;
    private String gymPlanName;
    private double price;
    private int duration;
    private int planBeforeActive;
    private int planAfterActive;
    private int status;
}
