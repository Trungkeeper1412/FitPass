package com.ks.fitpass.gymplan.repository;

import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.department.entity.GymPlan;
import com.ks.fitpass.gymplan.dto.*;

import java.util.List;

public interface GymPlanRepository {

    List<GymPlan> getAllByDepartmentId(int departmentId);

    GymPlan getGymPlanByGymPlanId(int gymPlanId, int departmentId);

    List<BrandGymPlanFlexDTO> getAllGymPlanFlexByBrandId(int brandId);

    List<BrandGymPlanFlexDTO> getAllGymPlanFlexByBrandIdActive(int brandId);

    int createGymPlanFlex(BrandCreateGymPlanFlexDTO brandCreateGymPlanFlexDTO);

    BrandUpdateGymPlanFlexDTO getGymPlanFlexDetail(int gymPlanId);

    int updateGymPlanFlex(BrandUpdateGymPlanFlexDTO brandUpdateGymPlanFlexDTO);

    List<BrandGymPlanFixedDTO> getAllGymPlanFixedByBrandId(int brandId);

    List<BrandGymPlanFixedDTO> getAllGymPlanFixedByBrandIdActive(int brandId);

    int createGymPlanFixed(BrandCreateGymPlanFixedDTO brandCreateGymPlanFixedDTO);

    BrandUpdateGymPlanFixedDTO getGymPlanFixedDetail(int gymPlanId);

    int updateGymPlanFixed(BrandUpdateGymPlanFixedDTO brandUpdateGymPlanFixedDTO);

    int[] insertGymPlanDepartment(int departmentId, List<Integer> gymPlanId);

    List<GymPlanDepartmentNameDto> getGymPlanDepartmentFixedByDepartmentId(int departmentId);

    List<GymPlanDepartmentNameDto> getGymPlanDepartmentFlexByDepartmentId(int departmentId);

    int deleteAllGymPlanByDepartmentId(int departmentId);

    Integer checkGymPlanInDepartmentUse(int gymPlanId);

    Integer getNumberOfGymPlan(int brandId);

    Integer getTotalGymPlanDepartment(int departmentId);

    List<GymPlanBuyStat> getGymPlanBuyStat(int brandId);

    List<GymPlanBuyStat> getGymPlanBuyStatByDepartmentId(int departmentId);
}
