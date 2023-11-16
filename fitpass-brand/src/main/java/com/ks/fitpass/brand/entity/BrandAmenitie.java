package com.ks.fitpass.brand.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandAmenitie {
    private int amenitieId;

    private int brandId;

    private String photoUrl;

    private String amenitieName;

    private String description;
    private int status;
}
