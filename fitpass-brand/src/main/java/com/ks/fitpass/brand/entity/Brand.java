package com.ks.fitpass.brand.entity;

import com.ks.fitpass.brand.entity.BrandStatus;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    private int brandId;

    private int userId;

    private String brandName;

    private String brandLogoUrl;

    private String brandWallpaperUrl;

    private String brandDescription;

    private Double rating;

    private String brandContactNumber;

    private String brandEmail;

    private BrandStatus brandStatus;

}
