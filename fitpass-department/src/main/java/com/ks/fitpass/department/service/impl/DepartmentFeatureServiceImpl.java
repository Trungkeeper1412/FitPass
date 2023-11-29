package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.department.repository.DepartmentFeatureRepository;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentFeatureServiceImpl implements DepartmentFeatureService {
    private final DepartmentFeatureRepository departmentFeatureRepository;

    @Autowired
    public DepartmentFeatureServiceImpl(DepartmentFeatureRepository departmentFeatureRepository) {
        this.departmentFeatureRepository = departmentFeatureRepository;
    }

    @Override
    public List<DepartmentFeature> getDepartmentFeatures(int departmentId) {
        return departmentFeatureRepository.getDepartmentFeatures(departmentId);
    }

    @Override
    public List<DepartmentFeature> getDepartmentFeaturesByStatusAndDepartmentID(int departmentId, int status) {
        return departmentFeatureRepository.getDepartmentFeaturesByStatusAndDepartmentID(departmentId, status);
    }

    @Override
    public List<Feature> getAllFeatures() {
        return departmentFeatureRepository.getAllFeatures();
    }

    @Override
    public int[] insertDepartmentFeature(int gymDepartmentId, List<Integer> featureId) {
        return departmentFeatureRepository.insertDepartmentFeature(gymDepartmentId, featureId);
    }

    @Override
    public int deleteAllDepartmentFeatures(int gymDepartmentId) {
        return departmentFeatureRepository.deleteAllDepartmentFeatures(gymDepartmentId);
    }

    @Override
    public Feature getByFeatureId(int featureId) {
        return departmentFeatureRepository.getByFeatureId(featureId);
    }

    @Override
    public int insertFeature(Feature feature) {
        return departmentFeatureRepository.insertFeature(feature);
    }

    @Override
    public int updateFeature(Feature feature) {
        return departmentFeatureRepository.updateFeature(feature);
    }

    @Override
    public int updateFeatureStatus(int featureId, int status) {
        return departmentFeatureRepository.updateFeatureStatus(featureId, status);
    }

    @Override
    public List<Feature> getAllFeatureNoStatus() {
        return departmentFeatureRepository.getAllFeatureNoStatus();
    }
}
