package com.ks.fitpass.department.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymPlanDepartmentNameDto implements Serializable {
    private int gymPlanId;
    private int gymDepartmentId;
    private int gymPlanTypeKey;
    private String gymPlanName;
    private String gymPlanDescription;
    private double price;
    private double pricePerHours;
    private int duration;
    private int planBeforeActiveValidity;
    private int planAfterActiveValidity;
    private String gymDepartmentName;
    private String gymDepartmentLogoUrl;
}
