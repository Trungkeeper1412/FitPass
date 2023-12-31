package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {


    private int departmentId;

    private String userName;

    private DepartmentStatus departmentStatus;

    private int brandId;

    private String departmentName;

    private String departmentAddress;

    private String departmentContactNumber;

    private String departmentLogoUrl;

    private String departmentWallpaperUrl;

    private String departmentThumbnailUrl;

    private String departmentDescription;

    private Double latitude;

    private Double longitude;

    private Double rating;

    private int capacity;

    private Double area;

    private int minPrice;
    private int maxPrice;

    private String city;

    private double distance;

    private int feedbackCount;

}
