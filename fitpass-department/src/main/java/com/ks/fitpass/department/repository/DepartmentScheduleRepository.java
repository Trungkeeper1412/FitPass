package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.DepartmentSchedule;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentScheduleRepository {
    List<DepartmentSchedule> getAllByDepartmentID(int departmentID) throws DataAccessException;
}
