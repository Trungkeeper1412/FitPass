package com.ks.fitpass.department.service;

import com.ks.fitpass.department.dto.GymPlanDto;

import java.util.List;

public interface GymPlanService {
    List<GymPlanDto> getGymPlanDetailsByDepartmentId(int departmentId);
}