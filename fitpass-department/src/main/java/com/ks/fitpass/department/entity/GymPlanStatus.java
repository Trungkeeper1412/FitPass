package com.ks.fitpass.department.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GymPlanStatus {
    private int gymPlanStatusCd;
    private String gymPlanStatusName;
}