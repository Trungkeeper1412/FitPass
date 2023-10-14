package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        return Department.builder()
                .departmentId(resultSet.getInt("gym_department_id"))
                .userId(resultSet.getInt("user_id"))
                .departmentName(resultSet.getString("name"))
                .departmentAddress(resultSet.getString("address"))
                .departmentContactNumber(resultSet.getString("contact_number"))
                .departmentLogoUrl(resultSet.getString("logo_url"))
                .departmentWallpaperUrl(resultSet.getString("wallpaper_url"))
                .departmentDescription(resultSet.getString("description"))
                .latitude(resultSet.getDouble("latitude"))
                .longitude(resultSet.getDouble("longitude"))
                .rating(resultSet.getDouble("rating"))
                .capacity(resultSet.getInt("capacity"))
                .area(resultSet.getDouble("area"))
                .departmentStatus(DepartmentStatus.builder()
                        .departmentStatusCd(resultSet.getInt("gym_department_status_key"))
                        .departmentStatusName(resultSet.getString("gym_department_status_name"))
                        .build())
                .build();
    }
}
