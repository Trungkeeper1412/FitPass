package com.ks.fitpass.department.service;

import com.ks.fitpass.department.dto.DepartmentAmenitie;
import com.ks.fitpass.department.entity.DepartmentAmenities;

import java.util.List;

public interface DepartmentAmenitieService {
    List<DepartmentAmenitie> getAllAmenitieOfDepartment(int departmentId);

    List<DepartmentAmenities> getAllDepartmentAmenitiesActivate(int gymDepartmentId);
}
