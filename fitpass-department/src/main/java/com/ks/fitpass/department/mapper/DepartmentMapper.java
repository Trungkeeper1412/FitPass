package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = Department.builder()
                .departmentId(resultSet.getInt("gym_department_id"))
                .userId(resultSet.getInt("user_id"))
                .departmentName(resultSet.getString("name"))
                .departmentAddress(resultSet.getString("address"))
                .departmentContactNumber(resultSet.getString("number"))
                .departmentLogoUrl(resultSet.getString("logo_url"))
                .departmentOpeningHours(resultSet.getString("opening_hours"))
                .departmentOpeningHours(resultSet.getString("closing_hours"))
                .departmentImageUrl(resultSet.getString("image_url"))
                .departmentDescription(resultSet.getString("description"))

                .departmentStatus(DepartmentStatus.builder()
                        .departmentStatusCd(resultSet.getInt("gym_department_status_key"))
                        .departmentStatusName(resultSet.getString("gym_department_status_name"))
                        .build())
                .build();

        return department;
    }
}
