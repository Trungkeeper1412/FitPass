package com.ks.fitpass.department.service;

import com.ks.fitpass.department.entity.DepartmentSchedule;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface DepartmentScheduleService {
    List<DepartmentSchedule> getAllByDepartmentID(int departmentID) throws DataAccessException;

    int[] addDepartmentSchedule(Map<String, String> dayToTimeMap, int departmentID);

    int deleteAllDepartmentSchedule(int departmentId);
}
