package com.ks.fitpass.brand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandAdminList {
    private int brandId;
    private String brandName;
    private String brandContactNumber;
    private String brandEmail;
    private int brandStatus;
    private int moneyPercent;
}
