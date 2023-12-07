package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.repository.DepartmentScheduleRepository;
import com.ks.fitpass.department.service.DepartmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentScheduleServiceImpl implements DepartmentScheduleService {

    private final DepartmentScheduleRepository departmentScheduleRepository;

    @Autowired
    public DepartmentScheduleServiceImpl(DepartmentScheduleRepository departmentScheduleRepository) {
        this.departmentScheduleRepository = departmentScheduleRepository;
    }

    @Override
    public List<DepartmentSchedule> getAllByDepartmentID(int departmentID) throws DataAccessException {
        return departmentScheduleRepository.getAllByDepartmentID(departmentID);
    }

    @Override
    public int[] addDepartmentSchedule(Map<String, String> dayToTimeMap, int departmentID) {
        return departmentScheduleRepository.addDepartmentSchedule(dayToTimeMap, departmentID);
    }

    @Override
    public int deleteAllDepartmentSchedule(int departmentId) {
        return departmentScheduleRepository.deleteAllDepartmentSchedule(departmentId);
    }
}
