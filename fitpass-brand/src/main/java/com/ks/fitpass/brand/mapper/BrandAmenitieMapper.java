package com.ks.fitpass.brand.mapper;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandAmenitieMapper implements RowMapper<BrandAmenitie> {
    @Override
    public BrandAmenitie mapRow(ResultSet resultSet, int i) throws SQLException {
        return BrandAmenitie.builder()
                .amenitieId(resultSet.getInt("amenitie_id"))
                .brandId(resultSet.getInt("brand_id"))
                .amenitieName(resultSet.getString("amenitie_name"))
                .photoUrl(resultSet.getString("photo_url"))
                .description(resultSet.getString("description"))
                .build();
    }
}
