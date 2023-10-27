package com.ks.fitpass.brand.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BrandStatus {
    private int brandStatusCd;

    private String brandStatusName;
}
