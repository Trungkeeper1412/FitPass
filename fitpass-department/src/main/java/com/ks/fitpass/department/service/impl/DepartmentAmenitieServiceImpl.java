package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.dto.DepartmentAmenitie;
import com.ks.fitpass.department.entity.DepartmentAmenities;
import com.ks.fitpass.department.repository.DepartmentAmenitieRepository;
import com.ks.fitpass.department.service.DepartmentAmenitieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentAmenitieServiceImpl implements DepartmentAmenitieService {
    private final DepartmentAmenitieRepository departmentAmenitieRepository;

    @Override
    public List<DepartmentAmenitie> getAllAmenitieOfDepartment(int departmentId) {
        return departmentAmenitieRepository.getAllAmenitieOfDepartment(departmentId);
    }

    @Override
    public List<DepartmentAmenities> getAllDepartmentAmenitiesActivate(int gymDepartmentId) {
        return departmentAmenitieRepository.getAllDepartmentAmenitiesActivate(gymDepartmentId);
    }

    @Override
    public int[] insertDepartmentAmenitie(int gymDepartmentId, List<Integer> amenitieId) {
        return departmentAmenitieRepository.insertDepartmentAmenitie(gymDepartmentId, amenitieId);
    }

    @Override
    public int deleteAllDepartmentAmenitie(int gymDepartmentId) {
        return departmentAmenitieRepository.deleteAllDepartmentAmenitie(gymDepartmentId);
    }
}
