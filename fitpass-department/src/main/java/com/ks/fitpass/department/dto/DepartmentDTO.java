package com.ks.fitpass.department.dto;

import com.ks.fitpass.department.entity.DepartmentStatus;
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

    private String departmentImageUrl;

    private double rating;

}
