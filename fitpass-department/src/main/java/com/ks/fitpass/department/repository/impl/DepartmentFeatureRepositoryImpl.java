package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.department.mapper.DepartmentFeatureMapper;
import com.ks.fitpass.department.repository.DepartmentFeatureRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository
public class DepartmentFeatureRepositoryImpl implements DepartmentFeatureRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentFeatureRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<DepartmentFeature> getDepartmentFeatures(int departmentId) {
        return jdbcTemplate.query(GET_DEPARTMENT_FEATURES, new DepartmentFeatureMapper(), departmentId);
    }

    @Override
    public List<DepartmentFeature> getDepartmentFeaturesByStatusAndDepartmentID(int departmentId, int status) {
        return jdbcTemplate.query(GET_DEPARTMENT_FEATURES_BY_STATUS_AND_DEPARTMENT_ID, new DepartmentFeatureMapper(), departmentId, status);
    }

    @Override
    public List<Feature> getAllFeatures() {
        return jdbcTemplate.query(GET_ALL_FEATURES_ACTIVE, (rs, rowNum) -> {
            Feature feature = new Feature();
            feature.setFeatureID(rs.getInt("feature_id"));
            feature.setFeatureName(rs.getString("feature_name"));
            feature.setFeatureIcon(rs.getString("feature_icon"));
            feature.setFeatureStatus(rs.getInt("feature_status"));
            return feature;
        });
    }

    @Override
    public int[] insertDepartmentFeature(int gymDepartmentId, List<Integer> featureId) {
        return jdbcTemplate.batchUpdate(INSERT_DEPARTMENT_FEATURE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,featureId.get(i));
                ps.setInt(2,gymDepartmentId);
            }

            @Override
            public int getBatchSize() {
                return featureId.size();
            }
        });
    }

    @Override
    public int deleteAllDepartmentFeatures(int gymDepartmentId) {
        return jdbcTemplate.update(DELETE_ALL_DEPARTMENT_FEATURES, gymDepartmentId);
    }

    @Override
    public Feature getByFeatureId(int featureId) {
        return jdbcTemplate.queryForObject(GET_FEATURE_BY_FEATURE_ID, new Object[]{featureId}, (rs, rowNum) -> {
            Feature feature = new Feature();
            feature.setFeatureID(rs.getInt("feature_id"));
            feature.setFeatureName(rs.getString("feature_name"));
            feature.setFeatureIcon(rs.getString("feature_icon"));
            feature.setFeatureStatus(rs.getInt("feature_status"));
            return feature;
        });
    }

    @Override
    public int insertFeature(Feature feature) {
        return jdbcTemplate.update(INSERT_FEATURE, feature.getFeatureName(), feature.getFeatureIcon(), feature.getFeatureStatus());
    }

    @Override
    public int updateFeature(Feature feature) {
        return jdbcTemplate.update(UPDATE_FEATURE, feature.getFeatureName(), feature.getFeatureIcon(), feature.getFeatureID())  ;
    }

    @Override
    public int updateFeatureStatus(int featureId, int status) {
        return jdbcTemplate.update(UPDATE_FEATURE_STATUS, status, featureId);
    }

    @Override
    public List<Feature> getAllFeatureNoStatus() {
        return jdbcTemplate.query(GET_ALL_FEATURES, (rs, rowNum) -> {
            Feature feature = new Feature();
            feature.setFeatureID(rs.getInt("feature_id"));
            feature.setFeatureName(rs.getString("feature_name"));
            feature.setFeatureIcon(rs.getString("feature_icon"));
            feature.setFeatureStatus(rs.getInt("feature_status"));
            return feature;
        });
    }
}
