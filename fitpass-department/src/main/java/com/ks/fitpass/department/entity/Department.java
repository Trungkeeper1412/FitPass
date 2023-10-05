package com.ks.fitpass.department.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Department {

    private int departmentId;

    private DepartmentStatus departmentStatus;

    private int userId;

    private String departmentName;

    private String departmentAddress;

    private String departmentContactNumber;

    private String departmentLogoUrl;

    private String departmentOpeningHours;

    private String departmentImageUrl;

    private String departmentDescription;
}
