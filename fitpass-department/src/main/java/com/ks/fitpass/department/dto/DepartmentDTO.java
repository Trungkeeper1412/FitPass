package com.ks.fitpass.department.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DepartmentDTO {

    private int departmentId;

    private String departmentName;

    private String departmentAddress;

    private String departmentWallpaperUrl;

    private Double rating;

    private int capacity;

    private Double area;


}
