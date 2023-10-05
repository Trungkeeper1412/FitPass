package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import com.ks.fitpass.department.repository.DepartmentRepository;
import com.ks.fitpass.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getByUserId(int userId) throws DataAccessException {
        return departmentRepository.getByUserId(userId);
    }

    @Override
    public List<Department> getAllByStatus(int status) throws DataAccessException {
        return departmentRepository.getAllByStatus(status);
    }

    @Override
    public Department getOne(int id) throws DataAccessException {
        return  departmentRepository.getOne(id);
    }

    @Override
    public boolean update(Department department) throws DataAccessException {
        return departmentRepository.update(department);
    }

    @Override
    public boolean updateStatusDepartment(Department department, DepartmentStatus departmentStatus) throws DataAccessException {
        department.setDepartmentStatus(DepartmentStatus.builder()
                .departmentStatusCd(departmentStatus.getDepartmentStatusCd())
                .departmentStatusName(departmentStatus.getDepartmentStatusName())
                .build()
        );
        return departmentRepository.update(department);
    }
}
