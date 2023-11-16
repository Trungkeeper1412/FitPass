package com.ks.fitpass.department.repository.impl;
import com.ks.fitpass.department.entity.DepartmentAmenities;
import com.ks.fitpass.department.mapper.DepartmentAmenitiesMapper;
import com.ks.fitpass.department.repository.DepartmentAmenitiesRepository;

import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DepartmentAmenitiesRepositoryImpl implements DepartmentAmenitiesRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentAmenitiesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DepartmentAmenities> getAllDepartmentAmenitiesActivate(int gymDepartmentId) {
        return jdbcTemplate.query(GET_DEPARTMENT_AMENITIES_DEPARTMENT_ID, new DepartmentAmenitiesMapper(), gymDepartmentId);
    }
}
