package com.ks.fitpass.department.service;

import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.Feature;

import java.util.List;

public interface DepartmentFeatureService {
    List<DepartmentFeature> getDepartmentFeatures(int departmentId);

    List<DepartmentFeature> getDepartmentFeaturesByStatusAndDepartmentID(int departmentId, int status);

    List<Feature> getAllFeatures();
    int[] insertDepartmentFeature(int gymDepartmentId, List<Integer> featureId);
    int deleteAllDepartmentFeatures(int gymDepartmentId);
}
