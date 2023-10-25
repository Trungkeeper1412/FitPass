package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.DepartmentAmenitie;
import com.ks.fitpass.department.mapper.DepartmentAmenitieMapper;
import com.ks.fitpass.department.repository.DepartmentAmenitieRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentAmenitieRepositoryImpl implements DepartmentAmenitieRepository, IRepositoryQuery {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentAmenitieRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<DepartmentAmenitie> getAllByDepartmentID(int departmentID) throws DataAccessException {
        return jdbcTemplate.query(GET_DEPARTMENT_AMENITIES_DEPARTMENT_ID, new DepartmentAmenitieMapper(), departmentID);
    }
}