package com.ks.fitpass.brand.repository.impl;

import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.mapper.BrandWithTotalOrderMapper;
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
    public List<Brand> getAllByStatus(int status, int page, int size, String sortPrice, String sortRating) throws DataAccessException {
        int offset = (page - 1) * size;
        String sql = GET_ALL_BRAND_BY_STATUS;

        if(sortRating != null && !sortRating.isEmpty()) {
            sql +=  " AND b.rating >  " + sortRating + " \n";
        }

        if(sortPrice != null && !sortPrice.isEmpty()) {
            if(sortPrice.equals("lowToHigh")) {
                sql += " ORDER BY (SELECT COUNT(*)\n" +
                        "                      FROM order_plan_detail opd\n" +
                        "                      JOIN gym_department gd ON opd.gym_department_id = gd.gym_department_id\n" +
                        "                      WHERE gd.brand_id = b.brand_id) asc \n";
            } else {
                sql += " ORDER BY (SELECT COUNT(*)\n" +
                        "                      FROM order_plan_detail opd\n" +
                        "                      JOIN gym_department gd ON opd.gym_department_id = gd.gym_department_id\n" +
                        "                      WHERE gd.brand_id = b.brand_id) desc \n";
            }
        }

        sql += "LIMIT "+size+" OFFSET " + offset;

        return jdbcTemplate.query(sql, new BrandWithTotalOrderMapper(), status);
    }

    @Override
    public List<Brand> getAllByTopRating(int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_BRAND_ORDER_BY_RATING, new BrandMapper(), status);
    }

    @Override
    public Brand getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(GET_BRAND_BY_ID, new BrandMapper(), id);
    }

    @Override
    public int countAllBrands(int status, String sortRating) {
        String sql = COUNT_ALL_BRAND_BY_STATUS;

        if(sortRating != null && !sortRating.isEmpty()) {
            sql +=  " AND b.rating >  " + sortRating + " \n";
        }

        return jdbcTemplate.queryForObject(sql, Integer.class, status);
    }
}
