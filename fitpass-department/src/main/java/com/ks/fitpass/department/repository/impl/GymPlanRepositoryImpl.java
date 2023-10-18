package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.GymPlan;
import com.ks.fitpass.department.mapper.GymPlanMapper;
import com.ks.fitpass.department.mapper.GymPlanWithDepartmentNameMapper;
import com.ks.fitpass.department.repository.GymPlanRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GymPlanRepositoryImpl implements GymPlanRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GymPlanRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GymPlan> getAllByDepartmentId(int departmentId) throws DataAccessException {
        return jdbcTemplate.query(IRepositoryQuery.GET_ALL_GYM_PLANS_BY_DEPARTMENT_ID, new GymPlanMapper(), departmentId);
    }

    @Override
    public GymPlan getGymPlanByGymPlanId(int gymPlanId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_GYM_PLAN_BY_GYM_PLAN_ID, new Object[]{gymPlanId}, new GymPlanWithDepartmentNameMapper());
    }
}