package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.DepartmentAmenitie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentAmenitieMapper implements RowMapper<DepartmentAmenitie> {
    @Override
    public DepartmentAmenitie mapRow(ResultSet resultSet, int i) throws SQLException {
        return DepartmentAmenitie.builder()
                .amenitieId(resultSet.getInt("amenitie_id"))
                .gymDepartmentId(resultSet.getInt("gym_department_id"))
                .amenitieName(resultSet.getString("amenitie_name"))
                .photoUrl(resultSet.getString("photo_url"))
                .description(resultSet.getString("description"))
                .build();
    }
}
