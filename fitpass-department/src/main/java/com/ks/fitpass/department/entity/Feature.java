package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    private int featureID;
    private String  featureIcon;
    private String  featureName;
    private int featureStatus;
}
