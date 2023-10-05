package com.ks.fitpass.department.service;

import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DepartmentService {

    Department getByUserId(int userId) throws DataAccessException;

    List<DepartmentDTO> getAllDepartmentForHome(int pageIndex, int pageSize) throws DataAccessException;

    List<Department> getAllByStatus(int status) throws DataAccessException;

    Department getOne(int id) throws DataAccessException;

    boolean update(Department department) throws DataAccessException;

    boolean updateStatusDepartment(Department department, DepartmentStatus departmentStatus) throws DataAccessException;

}
