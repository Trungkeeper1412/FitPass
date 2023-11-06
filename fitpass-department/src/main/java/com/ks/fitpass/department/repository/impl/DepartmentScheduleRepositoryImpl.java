package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.mapper.DepartmentScheduleMapper;
import com.ks.fitpass.department.repository.DepartmentScheduleRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentScheduleRepositoryImpl implements DepartmentScheduleRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<DepartmentSchedule> getAllByDepartmentID(int departmentID) throws DataAccessException {
        return jdbcTemplate.query(GET_DEPARTMENT_SCHEDULE_BY_ID_DEPARTMENT, new DepartmentScheduleMapper(), departmentID);
    }

}
