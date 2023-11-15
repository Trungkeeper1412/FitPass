package com.ks.fitpass.brand.repository.impl;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.mapper.BrandAmenitieMapper;
import com.ks.fitpass.brand.repository.BrandAmenitieRepository;
import com.ks.fitpass.brand.service.BrandService;
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

    @Override
    public List<BrandAmenitie> getAllByBrandIDActivate(int brandID) throws DataAccessException {
        return jdbcTemplate.query(GET_BRAND_AMENITIES_BRAND_ID_ACTIVATE, new BrandAmenitieMapper(), brandID);

    }

    @Override
    public BrandAmenitie getAmenitieDetail(int amenitieId) {
        return jdbcTemplate.queryForObject(GET_BRAND_AMENITIES_DETAIL, (rs, rowNum) -> {
                BrandAmenitie brandAmenitie = new BrandAmenitie();
                brandAmenitie.setAmenitieId(rs.getInt("amenitie_id"));
                brandAmenitie.setBrandId(rs.getInt("brand_id"));
                brandAmenitie.setPhotoUrl(rs.getString("photo_url"));
                brandAmenitie.setAmenitieName(rs.getString("amenitie_name"));
                brandAmenitie.setStatus(rs.getInt("amenitie_status"));
                brandAmenitie.setDescription(rs.getString("description"));
                return brandAmenitie;
            }, amenitieId);
    }

    @Override
    public int createBrandAmenitie(BrandAmenitie amenitie) {
        return jdbcTemplate.update(CREATE_BRAND_AMENITIES, amenitie.getBrandId(), amenitie.getPhotoUrl(),
                amenitie.getAmenitieName(), amenitie.getDescription(), amenitie.getStatus());
    }

    @Override
    public int updateBrandAmenitie(BrandAmenitie amenitie) {
        return jdbcTemplate.update(UPDATE_BRAND_AMENITIES, amenitie.getPhotoUrl(),
                amenitie.getAmenitieName(), amenitie.getDescription(), amenitie.getStatus(), amenitie.getAmenitieId());
    }

    @Override
    public int updateStatusBrandAmenitie(int status) {
        return jdbcTemplate.update(UPDATE_BRAND_AMENITIES_STATUS, status);
    }
}
