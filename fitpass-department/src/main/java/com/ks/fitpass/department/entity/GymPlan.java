package com.ks.fitpass.department.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GymPlan {
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

}
