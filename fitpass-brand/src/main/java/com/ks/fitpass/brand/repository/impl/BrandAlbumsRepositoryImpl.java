package com.ks.fitpass.brand.repository.impl;

import com.ks.fitpass.brand.entity.BrandAlbums;
import com.ks.fitpass.brand.mapper.BrandAlbumsMapper;
import com.ks.fitpass.brand.repository.BrandAlbumsRepository;
import com.ks.fitpass.brand.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandAlbumsRepositoryImpl implements BrandAlbumsRepository, IRepositoryQuery {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BrandAlbumsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<BrandAlbums> getAllByBrandID(int brandID) throws DataAccessException {
        return jdbcTemplate.query(GET_BRAND_ALBUMS_BY_ID_BRAND, new BrandAlbumsMapper(), brandID);
    }
}