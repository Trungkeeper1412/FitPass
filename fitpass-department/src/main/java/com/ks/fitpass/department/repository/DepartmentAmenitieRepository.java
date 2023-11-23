package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.dto.DepartmentAmenitie;
import com.ks.fitpass.department.entity.DepartmentAmenities;

import java.util.List;

public interface DepartmentAmenitieRepository {
    List<DepartmentAmenities> getAllDepartmentAmenitiesActivate(int gymDepartmentId);

    List<DepartmentAmenitie> getAllAmenitieOfDepartment(int departmentId);

    int[] insertDepartmentAmenitie(int gymDepartmentId, List<Integer> amenitieId);

    int deleteAllDepartmentAmenitie(int gymDepartmentId);
}
