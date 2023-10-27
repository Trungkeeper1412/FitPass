package com.ks.fitpass.brand.mapper;

import com.ks.fitpass.brand.entity.BrandAlbums;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandAlbumsMapper implements RowMapper<BrandAlbums> {
    @Override
    public BrandAlbums mapRow(ResultSet resultSet, int i) throws SQLException {
        return BrandAlbums.builder()
                .albumId(resultSet.getInt("id"))
                .brandId(resultSet.getInt("brand_id"))
                .photoUrl(resultSet.getString("photo_url"))
                .description(resultSet.getString("description"))
                .build();
    }
}
