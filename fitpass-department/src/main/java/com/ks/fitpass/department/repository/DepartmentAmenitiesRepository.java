package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.DepartmentAmenities;

import java.util.List;

public interface DepartmentAmenitiesRepository {

    List<DepartmentAmenities> getAllDepartmentAmenitiesActivate(int gymDepartmentId);

}
