package com.ks.fitpass.department.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor // Add this annotation
@AllArgsConstructor
public class DepartmentDTO {

    private int departmentId;

    private String departmentName;

    private String departmentAddress;

    private String departmentWallpaperUrl;

    private Double rating;

    private int capacity;

    private Double area;

    private int total;
    private int total5;
    private int total4;
    private int total3;
    private int total2;
    private int total1;
    private double avgRating;


}