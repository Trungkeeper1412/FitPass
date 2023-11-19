package com.ks.fitpass.gymplan.service;

import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.gymplan.dto.*;

import java.util.List;

public interface GymPlanService {
    List<GymPlanDto> getGymPlanDetailsByDepartmentId(int departmentId);
    GymPlanDepartmentNameDto getGymPlanByGymPlanId(int gymPlanId, int departmentId);

    List<BrandGymPlanFlexDTO> getAllGymPlanFlexByBrandId(int brandId);
    int createGymPlanFlex(BrandCreateGymPlanFlexDTO brandCreateGymPlanFlexDTO);
    BrandUpdateGymPlanFlexDTO getGymPlanFlexDetail(int gymPlanId);
    int updateGymPlanFlex(BrandUpdateGymPlanFlexDTO brandUpdateGymPlanFlexDTO);


    // Fixed gym plan
    List<BrandGymPlanFixedDTO> getAllGymPlanFixedByBrandId(int brandId);
    int createGymPlanFixed(BrandCreateGymPlanFixedDTO brandCreateGymPlanFixedDTO);
    BrandUpdateGymPlanFixedDTO getGymPlanFixedDetail(int gymPlanId);
    int updateGymPlanFixed(BrandUpdateGymPlanFixedDTO brandUpdateGymPlanFixedDTO);

}