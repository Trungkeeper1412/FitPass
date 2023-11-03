package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.Feature;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentFeatureMapper implements RowMapper<DepartmentFeature> {
    @Override
    public DepartmentFeature mapRow(ResultSet resultSet, int i) throws SQLException {
        return DepartmentFeature.builder()
                .departmentFeatureId(resultSet.getInt("gym_department_feature_id"))
                .feature(Feature.builder()
                        .featureID(resultSet.getInt("feature_id"))
                        .featureIcon(resultSet.getString("feature_icon"))
                        .featureName(resultSet.getString("feature_name"))
                        .featureStatus(resultSet.getInt("feature_status"))
                        .build())
                .gymDepartmentId(resultSet.getInt("gym_department_id"))
                .departmentFeatureStatus(resultSet.getInt("feature_status"))
                .build();
    }
}