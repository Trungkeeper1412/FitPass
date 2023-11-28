package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.DepartmentAlbums;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentAlbumsMapper implements RowMapper<DepartmentAlbums> {
    @Override
    public DepartmentAlbums mapRow(ResultSet resultSet, int i) throws SQLException {
        return DepartmentAlbums.builder()
                .albumId(resultSet.getInt("id"))
                .departmentId(resultSet.getInt("gym_department_id"))
                .photoUrl(resultSet.getString("photo_url"))
                .build();
    }
}
