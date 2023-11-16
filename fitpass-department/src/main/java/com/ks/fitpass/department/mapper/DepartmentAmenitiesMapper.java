package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.DepartmentAmenities;
import com.ks.fitpass.department.entity.BrandAmenities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentAmenitiesMapper  implements RowMapper<DepartmentAmenities> {
    @Override
    public DepartmentAmenities mapRow(ResultSet resultSet, int i) throws SQLException {
        return DepartmentAmenities.builder()
                .gymDepartmentId(resultSet.getInt("gym_department_id"))
                .brandAmenitieId(BrandAmenities.builder()
                        .amenitieId(resultSet.getInt("amenitie_id"))
                        .brandId(resultSet.getInt("brand_id"))
                        .photoUrl(resultSet.getString("photo_url"))
                        .amenitieName(resultSet.getString("amenitie_name"))
                        .description(resultSet.getString("description"))
                        .status(resultSet.getInt("amenitie_status"))
                        .build())
                .build();
    }

}
