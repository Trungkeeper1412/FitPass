package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.GymPlan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GymPlanMapper implements RowMapper<GymPlan> {
    @Override
    public GymPlan mapRow(ResultSet resultSet, int i) throws SQLException {
        return GymPlan.builder()
                .planId(resultSet.getInt("plan_id"))
                .brandId(resultSet.getInt("brand_id"))
                .gymPlanStatusKey(resultSet.getInt("gym_plan_status_key"))
                .gymPlanTypeKey(resultSet.getInt("gym_plan_type_key"))
                .gymPlanName(resultSet.getString("name"))
                .gymPlanDescription(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .pricePerHours(resultSet.getDouble("price_per_hours"))
                .duration(resultSet.getInt("duration"))
                .planBeforeActiveValidity(resultSet.getInt("plan_before_active_validity"))
                .planAfterActiveValidity(resultSet.getInt("plan_after_active_validity"))
                .gymDepartmentId(resultSet.getInt("gym_department_id"))
                .build();
    }
}