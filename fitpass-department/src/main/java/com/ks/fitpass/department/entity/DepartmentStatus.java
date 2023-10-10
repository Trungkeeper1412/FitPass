package com.ks.fitpass.department.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DepartmentStatus {

    private int departmentStatusCd;

    private String departmentStatusName;

}
