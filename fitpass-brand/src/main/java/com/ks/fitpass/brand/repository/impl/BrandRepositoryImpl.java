package com.ks.fitpass.brand.repository.impl;

import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.repository.IRepositoryQuery;
import com.ks.fitpass.brand.repository.BrandRepository;
import com.ks.fitpass.brand.mapper.BrandMapper;
import com.ks.fitpass.department.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepositoryImpl implements BrandRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BrandRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Brand> getAllByStatus(int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_BRAND_BY_STATUS, new BrandMapper(), status);
    }

    @Override
    public List<Brand> getAllByTopRating(int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_BRAND_ORDER_BY_RATING, new BrandMapper(), status);
    }

    @Override
    public Brand getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(GET_BRAND_BY_ID, new BrandMapper(), id);
    }
}
