package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapperWithUserName extends DepartmentMapper{
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = super.mapRow(resultSet, i);
        department.setUserName(resultSet.getString("user_name"));
        return department;
    }
}
