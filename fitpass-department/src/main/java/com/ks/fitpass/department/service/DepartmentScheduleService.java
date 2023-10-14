package com.ks.fitpass.department.service;

import com.ks.fitpass.department.entity.DepartmentSchedule;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentScheduleService {
    List<DepartmentSchedule> getAllByDepartmentID(int departmentID) throws DataAccessException;

}
