package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentFeature {
    private int departmentFeatureId;

    private Feature feature;

    private int gymDepartmentId;

    private int departmentFeatureStatus;
}