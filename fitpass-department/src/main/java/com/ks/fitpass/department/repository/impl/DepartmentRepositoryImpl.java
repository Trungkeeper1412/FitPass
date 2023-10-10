package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.mapper.DepartmentMapper;
import com.ks.fitpass.department.repository.DepartmentRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository, IRepositoryQuery {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Department getByUserId(int userId) throws DataAccessException {
        return jdbcTemplate.queryForObject(GET_DEPARTMENT_BY_USER, new DepartmentMapper(), userId);
    }

    @Override
    public List<Department> getAllByStatus(int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_BY_STATUS, new DepartmentMapper(), status);
    }
    @Override
    public List<Department> getAllByTopRating(int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_BY_RATING, new DepartmentMapper(), status);
    }

    @Override
    public Department getOne(int id) throws DataAccessException {
    return jdbcTemplate.queryForObject(GET_DEPARTMENT_BY_ID, new DepartmentMapper(), id);
    }
    @Override
    public boolean update(Department department) throws DataAccessException {
        return jdbcTemplate.update(
                UPDATE_DEPARTMENT,
                department.getUserId(),
                department.getDepartmentName(),
                department.getDepartmentAddress(),
                department.getDepartmentContactNumber(),
                department.getDepartmentLogoUrl(),
                department.getDepartmentOpeningHours(),
                department.getDepartmentImageUrl(),
                department.getDepartmentDescription(),
                department.getDepartmentStatus().getDepartmentStatusCd(),
                department.getDepartmentId()
        ) > 0;
    }
}
