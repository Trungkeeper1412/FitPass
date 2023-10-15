package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentFeature {
    private int featureId;

    private int gymDepartmentId;

    private String featureIcon;

    private String featureName;

    private boolean isSelected;
}