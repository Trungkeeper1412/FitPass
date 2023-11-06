package com.ks.fitpass.brand.repository.impl;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.mapper.BrandAmenitieMapper;
import com.ks.fitpass.brand.repository.BrandAmenitieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ks.fitpass.brand.repository.IRepositoryQuery;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class BrandAmenitieRepositoryImpl implements BrandAmenitieRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BrandAmenitieRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BrandAmenitie> getAllByBrandID(int brandID) throws DataAccessException {
        return jdbcTemplate.query(GET_BRAND_AMENITIES_BRAND_ID, new BrandAmenitieMapper(), brandID);

    }
}