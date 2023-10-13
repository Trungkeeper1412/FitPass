package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.core.repository.KbnRepository;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.GymPlan;
import com.ks.fitpass.department.repository.GymPlanRepository;
import com.ks.fitpass.department.service.GymPlanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GymPlanServiceImpl implements GymPlanService {

    private GymPlanRepository gymPlanRepository;
    private KbnRepository mstKbnRepository;

    public GymPlanServiceImpl(GymPlanRepository gymPlanRepository, KbnRepository mstKbnRepository) {
        this.gymPlanRepository = gymPlanRepository;
        this.mstKbnRepository = mstKbnRepository;
    }

    @Override
    public List<GymPlanDto> getGymPlanDetailsByDepartmentId(int departmentId) {
        List<GymPlan> gymPlans = gymPlanRepository.getAllByDepartmentId(departmentId);
        List<GymPlanDto> gymPlanDtos = new ArrayList<>();

        for (GymPlan gymPlan : gymPlans) {
            GymPlanDto dto = new GymPlanDto();
            dto.setGymPlanName(gymPlan.getGymPlanName());
            dto.setGymPlanDescription(gymPlan.getGymPlanDescription());
            dto.setPlanBeforeActiveValidity(gymPlan.getPlanBeforeActiveValidity());
            dto.setPlanAfterActiveValidity(gymPlan.getPlanAfterActiveValidity());

            String gymPlanType = mstKbnRepository.getGymPlanTypeByPlanKey(gymPlan.getGymPlanKey());
            dto.setGymPlanType(gymPlanType);

            if (gymPlanType.equalsIgnoreCase("Gói không theo giờ")) {
                dto.setPrice(gymPlan.getPrice());
            } else if (gymPlanType.equalsIgnoreCase("Gói theo giờ")) {
                dto.setPricePerHours(gymPlan.getPricePerHours());
            }

            gymPlanDtos.add(dto);
        }

        return gymPlanDtos;
    }
}