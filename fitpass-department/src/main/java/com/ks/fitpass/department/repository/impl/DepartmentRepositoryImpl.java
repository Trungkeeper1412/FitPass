package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.UserFeedback;
import com.ks.fitpass.department.mapper.DepartmentMapper;
import com.ks.fitpass.department.mapper.UserFeedbackMapper;
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
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_ORDER_BY_RATING, new DepartmentMapper(), status);
    }

    @Override
    public List<Department> getAllByBrandId(int brandId, int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_BY_BRAND_ID, new DepartmentMapper(), brandId, status);
    }

    @Override
    public Department getOne(int id) throws DataAccessException {
    return jdbcTemplate.queryForObject(GET_DEPARTMENT_BY_ID, new DepartmentMapper(), id);
    }
    @Override
    public boolean update(Department department) throws DataAccessException {
        return jdbcTemplate.update(
                UPDATE_DEPARTMENT,
                department.getBrandId(),
                department.getDepartmentName(),
                department.getDepartmentAddress(),
                department.getDepartmentContactNumber(),
                department.getDepartmentLogoUrl(),
                department.getDepartmentWallpaperUrl(),
                department.getDepartmentDescription(),
                department.getDepartmentStatus().getDepartmentStatusCd(),
                department.getDepartmentId()
        ) > 0;
    }

    @Override
    public List<Department> findByRatingBetween(double from, double to) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_ORDER_BY_RATING, new DepartmentMapper(), from, to);
    }

    @Override
    public List<UserFeedback> getDepartmentFeedback(int departmentId) {
        return jdbcTemplate.query(GET_DEPARTMENT_FEEDBACK, new UserFeedbackMapper(), departmentId);
    }

    @Override
    public List<Department> getDepartmentByBrandID(int status, int brandID) throws DataAccessException {
        return jdbcTemplate.query(GET_DEPARTMENT_BY_BRAND_ID, new DepartmentMapper(), status,brandID);
    }


}
