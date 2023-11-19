package com.ks.fitpass.department.dto;

import lombok.Data;

@Data
public class DepartmentAmenitie {
    private int amenitieId;
    private int brandId;
    private String amenitieName;
    private String photoUrl;
    private String description;
}
