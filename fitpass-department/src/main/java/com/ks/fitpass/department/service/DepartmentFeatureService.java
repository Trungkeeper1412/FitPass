package com.ks.fitpass.department.service;

import com.ks.fitpass.department.entity.DepartmentFeature;

import java.util.List;

public interface DepartmentFeatureService {
    List<DepartmentFeature> getDepartmentFeatures(int departmentId);

    List<DepartmentFeature> getDepartmentFeaturesByStatusAndDepartmentID(int departmentId, int status);

}