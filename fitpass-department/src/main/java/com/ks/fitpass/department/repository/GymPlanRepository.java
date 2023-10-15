package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.GymPlan;

import java.util.List;

public interface GymPlanRepository {

    List<GymPlan> getAllByDepartmentId(int departmentId);



}