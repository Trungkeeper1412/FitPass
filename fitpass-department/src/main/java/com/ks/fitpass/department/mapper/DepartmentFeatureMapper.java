package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.DepartmentFeature;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentFeatureMapper implements RowMapper<DepartmentFeature> {
    @Override
    public DepartmentFeature mapRow(ResultSet resultSet, int i) throws SQLException {
        return DepartmentFeature.builder()
                .featureId(resultSet.getInt("feature_id"))
                .gymDepartmentId(resultSet.getInt("gym_department_id"))
                .featureIcon(resultSet.getString("feature_icon"))
                .featureName(resultSet.getString("feature_name"))
                .isSelected(resultSet.getBoolean("isSelected"))
                .build();
    }
}