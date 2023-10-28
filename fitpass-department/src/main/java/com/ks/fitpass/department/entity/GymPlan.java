package com.ks.fitpass.department.entity;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GymPlan implements Serializable {
    private int planId;
    private int gymDepartmentId;
    private int gymPlanKey;
    private int gymPlanStatusKey;
    private int gymPlanTypeKey;
    private String gymPlanName;
    private String gymPlanDescription;
    private double price;
    private double pricePerHours;
    private int planSold;
    private int duration;
    private int planBeforeActiveValidity;
    private int planAfterActiveValidity;
    private String gymDepartmentName;

}
