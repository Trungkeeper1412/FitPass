package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandAmenities {
    private int amenitieId;

    private int brandId;

    private String photoUrl;

    private String amenitieName;

    private String description;
    private int status;
}
