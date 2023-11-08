package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentHomePageMapper extends DepartmentMapper {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = super.mapRow(resultSet, i);
        department.setMinPrice(resultSet.getInt("min_price"));
        department.setMaxPrice(resultSet.getInt("max_price"));
        return department;
    }
}

