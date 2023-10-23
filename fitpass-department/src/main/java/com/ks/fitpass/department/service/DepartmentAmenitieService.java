package com.ks.fitpass.department.service;

import com.ks.fitpass.department.entity.DepartmentAmenitie;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentAmenitieService {
    List<DepartmentAmenitie> getAllByDepartmentID(int departmentID) throws DataAccessException;
}
