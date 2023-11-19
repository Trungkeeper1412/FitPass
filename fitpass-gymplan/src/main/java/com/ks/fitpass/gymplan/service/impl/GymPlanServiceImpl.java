package com.ks.fitpass.gymplan.service.impl;

import com.ks.fitpass.core.repository.KbnRepository;
import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.GymPlan;
import com.ks.fitpass.gymplan.dto.*;
import com.ks.fitpass.gymplan.repository.GymPlanRepository;
import com.ks.fitpass.gymplan.service.GymPlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GymPlanServiceImpl implements GymPlanService {
    private final GymPlanRepository gymPlanRepository;
    private final KbnRepository mstKbnRepository;

    @Override
    public List<GymPlanDto> getGymPlanDetailsByDepartmentId(int departmentId) {
        List<GymPlan> gymPlans = gymPlanRepository.getAllByDepartmentId(departmentId);
        List<GymPlanDto> gymPlanDtos = new ArrayList<>();

        for (GymPlan gymPlan : gymPlans) {
            GymPlanDto dto = new GymPlanDto();
            dto.setGymPlanId(gymPlan.getPlanId());
            dto.setGymPlanName(gymPlan.getGymPlanName());
            dto.setGymPlanDescription(gymPlan.getGymPlanDescription());
            dto.setPlanBeforeActiveValidity(gymPlan.getPlanBeforeActiveValidity());
            dto.setPlanAfterActiveValidity(gymPlan.getPlanAfterActiveValidity());
            dto.setDuration(gymPlan.getDuration());

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


    @Override
    public GymPlanDepartmentNameDto getGymPlanByGymPlanId(int gymPlanId, int departmentId) {
        GymPlan gymPlan = gymPlanRepository.getGymPlanByGymPlanId(gymPlanId,departmentId);
        GymPlanDepartmentNameDto dto = new GymPlanDepartmentNameDto();
        dto.setGymPlanId(gymPlan.getPlanId());
        dto.setGymPlanName(gymPlan.getGymPlanName());
        dto.setGymPlanKey(gymPlan.getGymPlanKey());
        dto.setGymDepartmentId(gymPlan.getGymDepartmentId());
        dto.setGymPlanDescription(gymPlan.getGymPlanDescription());

        String gymPlanType = mstKbnRepository.getGymPlanTypeByPlanKey(gymPlan.getGymPlanKey());
        if (gymPlanType.equalsIgnoreCase("Gói không theo giờ")) {
            dto.setPrice(gymPlan.getPrice());
        } else if (gymPlanType.equalsIgnoreCase("Gói theo giờ")) {
            dto.setPricePerHours(gymPlan.getPricePerHours());
        }
        dto.setPlanBeforeActiveValidity(gymPlan.getPlanBeforeActiveValidity());
        dto.setPlanAfterActiveValidity(gymPlan.getPlanAfterActiveValidity());
        dto.setDuration(gymPlan.getDuration());
        dto.setGymDepartmentName(gymPlan.getGymDepartmentName());
        return dto;
    }

    @Override
    public List<BrandGymPlanFlexDTO> getAllGymPlanFlexByBrandId(int brandId) {
        return gymPlanRepository.getAllGymPlanFlexByBrandId(brandId);
    }

    @Override
    public int createGymPlanFlex(BrandCreateGymPlanFlexDTO brandCreateGymPlanFlexDTO) {
        return gymPlanRepository.createGymPlanFlex(brandCreateGymPlanFlexDTO);
    }

    @Override
    public BrandUpdateGymPlanFlexDTO getGymPlanFlexDetail(int gymPlanId) {
        return gymPlanRepository.getGymPlanFlexDetail(gymPlanId);
    }

    @Override
    public int updateGymPlanFlex(BrandUpdateGymPlanFlexDTO brandUpdateGymPlanFlexDTO) {
        return gymPlanRepository.updateGymPlanFlex(brandUpdateGymPlanFlexDTO);
    }

    @Override
    public List<BrandGymPlanFixedDTO> getAllGymPlanFixedByBrandId(int brandId) {
        return gymPlanRepository.getAllGymPlanFixedByBrandId(brandId);
    }

    @Override
    public int createGymPlanFixed(BrandCreateGymPlanFixedDTO brandCreateGymPlanFixedDTO) {
        return gymPlanRepository.createGymPlanFixed(brandCreateGymPlanFixedDTO);
    }

    @Override
    public BrandUpdateGymPlanFixedDTO getGymPlanFixedDetail(int gymPlanId) {
        return gymPlanRepository.getGymPlanFixedDetail(gymPlanId);
    }

    @Override
    public int updateGymPlanFixed(BrandUpdateGymPlanFixedDTO brandUpdateGymPlanFixedDTO) {
        return gymPlanRepository.updateGymPlanFixed(brandUpdateGymPlanFixedDTO);
    }
}