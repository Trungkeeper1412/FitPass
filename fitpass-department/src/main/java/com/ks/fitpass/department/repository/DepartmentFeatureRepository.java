package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.DepartmentFeature;

import java.util.List;

public interface DepartmentFeatureRepository {
    List<DepartmentFeature> getDepartmentFeatures(int departmentId);
}
