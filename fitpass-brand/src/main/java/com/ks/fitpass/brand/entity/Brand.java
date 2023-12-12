package com.ks.fitpass.brand.entity;

import lombok.*;

import java.io.Serializable;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {

    private int brandId;

    private int userId;

    private String brandName;

    private String brandLogoUrl;

    private String brandWallpaperUrl;

    private String brandThumbnailUrl;

    private String brandDescription;

    private Double rating;

    private String brandContactNumber;

    private String brandEmail;

    private BrandStatus brandStatus;

    private int numberOfOrderDetailSale;

}
