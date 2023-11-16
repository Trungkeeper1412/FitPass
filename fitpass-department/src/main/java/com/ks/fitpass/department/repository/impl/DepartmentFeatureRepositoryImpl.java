package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.mapper.DepartmentFeatureMapper;
import com.ks.fitpass.department.repository.DepartmentFeatureRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DepartmentFeatureRepositoryImpl implements DepartmentFeatureRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentFeatureRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<DepartmentFeature> getDepartmentFeatures(int departmentId) {
        return jdbcTemplate.query(GET_DEPARTMENT_FEATURES, new DepartmentFeatureMapper(), departmentId);
    }
}
