package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.entity.DepartmentAmenitie;
import com.ks.fitpass.department.repository.DepartmentAmenitieRepository;
import com.ks.fitpass.department.service.DepartmentAmenitieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentAmenitieServiceImpl implements DepartmentAmenitieService {
    private final DepartmentAmenitieRepository departmentAmenitieRepository;

    @Autowired
    public DepartmentAmenitieServiceImpl(DepartmentAmenitieRepository departmentAmenitieRepository) {
        this.departmentAmenitieRepository = departmentAmenitieRepository;
    }

    @Override
    public List<DepartmentAmenitie> getAllByDepartmentID(int departmentID) throws DataAccessException {
        return departmentAmenitieRepository.getAllByDepartmentID(departmentID);
    }
}