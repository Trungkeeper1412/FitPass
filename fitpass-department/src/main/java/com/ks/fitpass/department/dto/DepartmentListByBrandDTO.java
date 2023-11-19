package com.ks.fitpass.department.dto;

import com.ks.fitpass.department.entity.DepartmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DepartmentListByBrandDTO {
    private int departmentId;
    private String departmentName;
    private String userName;
    private String departmentThumbnailUrl;
    private String departmentContactNumber;
    private DepartmentStatus departmentStatus;
}
