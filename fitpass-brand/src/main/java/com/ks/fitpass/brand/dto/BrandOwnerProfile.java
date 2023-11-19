package com.ks.fitpass.brand.dto;

import com.ks.fitpass.brand.entity.BrandStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BrandOwnerProfile {
    private int brandId;

    private int userId;

    private String brandName;

    private String brandLogoUrl;

    private String brandWallpaperUrl;

    private String brandThumbnailUrl;

    private String brandDescription;

    private String brandContactNumber;

    private String brandEmail;

    private BrandStatus brandStatus;

}
