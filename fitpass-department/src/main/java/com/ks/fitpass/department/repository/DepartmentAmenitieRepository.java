package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.DepartmentAmenitie;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentAmenitieRepository {
    List<DepartmentAmenitie> getAllByDepartmentID(int departmentID) throws DataAccessException;

}
