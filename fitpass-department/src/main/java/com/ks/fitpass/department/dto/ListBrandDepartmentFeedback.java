package com.ks.fitpass.department.dto;

import lombok.Data;

@Data
public class ListBrandDepartmentFeedback {
    private int departmentId;

    private int brandId;

    private String departmentName;

    private String departmentWallpaperUrl;

    private Double rating;

    private int feedbackCount;
}
