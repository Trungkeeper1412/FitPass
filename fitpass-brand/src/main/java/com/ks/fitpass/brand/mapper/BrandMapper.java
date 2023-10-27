package com.ks.fitpass.brand.mapper;

import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements RowMapper<Brand> {
    @Override
    public Brand mapRow(ResultSet resultSet, int i) throws SQLException {
        return Brand.builder()
                .brandId(resultSet.getInt("brand_id"))
                .userId(resultSet.getInt("user_id"))
                .brandName(resultSet.getString("name"))
                .brandLogoUrl(resultSet.getString("logo_url"))
                .brandWallpaperUrl(resultSet.getString("wallpaper_url"))
                .brandDescription(resultSet.getString("description"))
                .rating(resultSet.getDouble("rating"))
                .brandContactNumber(resultSet.getString("contact_number"))
                .brandEmail(resultSet.getString("contact_email"))

                .brandStatus(BrandStatus.builder()
                        .brandStatusCd(resultSet.getInt("brand_status_key"))
                        .brandStatusName(resultSet.getString("brand_status_name"))
                        .build())
                .build();
    }
}
