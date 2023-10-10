package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    private Double latitude;

    private Double longitude;

    private Double rating;
}
