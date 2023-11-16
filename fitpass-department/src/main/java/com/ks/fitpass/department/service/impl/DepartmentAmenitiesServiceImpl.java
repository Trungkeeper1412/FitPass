package com.ks.fitpass.department.service.impl;
import com.ks.fitpass.department.entity.DepartmentAmenities;
import com.ks.fitpass.department.repository.DepartmentAmenitiesRepository;
import com.ks.fitpass.department.service.DepartmentAmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentAmenitiesServiceImpl implements DepartmentAmenitiesService{


    private final DepartmentAmenitiesRepository departmentAmenitiesRepository;

    @Autowired
    public DepartmentAmenitiesServiceImpl(DepartmentAmenitiesRepository departmentAmenitiesRepository) {
        this.departmentAmenitiesRepository = departmentAmenitiesRepository;
    }

    @Override
    public List<DepartmentAmenities> getAllDepartmentAmenitiesActivate(int gymDepartmentId) {
        return departmentAmenitiesRepository.getAllDepartmentAmenitiesActivate(gymDepartmentId);
    }
}
