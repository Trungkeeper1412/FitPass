package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.GymPlan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GymPlanWithDepartmentNameMapper extends GymPlanMapper{
    @Override
    public GymPlan mapRow(ResultSet resultSet, int i) throws SQLException {
        GymPlan a = super.mapRow(resultSet, i);
        a.setGymDepartmentName(resultSet.getString("name_department"));
        return a;
    }
}
