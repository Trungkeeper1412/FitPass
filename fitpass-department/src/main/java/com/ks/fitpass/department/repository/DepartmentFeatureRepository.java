package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.Feature;

import java.util.List;

public interface DepartmentFeatureRepository {
    List<DepartmentFeature> getDepartmentFeatures(int departmentId);

    List<DepartmentFeature> getDepartmentFeaturesByStatusAndDepartmentID(int departmentId, int status);

    List<Feature> getAllFeatures();

    int[] insertDepartmentFeature(int gymDepartmentId, List<Integer> featureId);

    int deleteAllDepartmentFeatures(int gymDepartmentId);

    Feature getByFeatureId(int featureId);

    int insertFeature(Feature feature);

    int updateFeature(Feature feature);

    int updateFeatureStatus(int featureId, int status);

    List<Feature> getAllFeatureNoStatus();
}
