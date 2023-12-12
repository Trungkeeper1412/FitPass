package com.ks.fitpass.brand.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class BrandStatus implements Serializable {
    private int brandStatusCd;

    private String brandStatusName;
}
