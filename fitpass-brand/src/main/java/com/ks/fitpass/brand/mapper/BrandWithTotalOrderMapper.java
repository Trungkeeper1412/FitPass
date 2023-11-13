package com.ks.fitpass.brand.mapper;

import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandWithTotalOrderMapper extends BrandMapper{
    @Override
    public Brand mapRow(ResultSet resultSet, int i) throws SQLException {
        Brand brand = super.mapRow(resultSet, i);
        brand.setNumberOfOrderDetailSale(resultSet.getInt("total_order_details"));
        return brand;
    }
}
