package com.ks.fitpass.department.dto;

import com.ks.fitpass.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentHomePagePagnition {
    List<Department> departmentList;
    private int totalPage;
    private int currentPage;
}
