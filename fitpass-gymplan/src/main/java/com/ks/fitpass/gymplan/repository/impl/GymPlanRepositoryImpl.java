package com.ks.fitpass.gymplan.repository.impl;

import com.ks.fitpass.department.entity.GymPlan;
import com.ks.fitpass.department.mapper.GymPlanMapper;
import com.ks.fitpass.department.mapper.GymPlanWithDepartmentNameMapper;
import com.ks.fitpass.gymplan.dto.*;
import com.ks.fitpass.gymplan.repository.GymPlanRepository;
import com.ks.fitpass.gymplan.repository.IRepositoryQuery;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class GymPlanRepositoryImpl implements GymPlanRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<GymPlan> getAllByDepartmentId(int departmentId) throws DataAccessException {
        return jdbcTemplate.query(com.ks.fitpass.gymplan.repository.IRepositoryQuery.GET_ALL_GYM_PLANS_BY_DEPARTMENT_ID, new GymPlanMapper(), departmentId);
    }

    @Override
    public GymPlan getGymPlanByGymPlanId(int gymPlanId, int departmentId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_GYM_PLAN_BY_GYM_PLAN_ID, new Object[]{gymPlanId,departmentId}, new GymPlanWithDepartmentNameMapper());
    }

    @Override
    public List<BrandGymPlanFlexDTO> getAllGymPlanFlexByBrandId(int brandId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_ALL_GYM_PLAN_FLEX_BY_BRAND_ID, (rs, rowNum) -> {
            BrandGymPlanFlexDTO brandGymPlanFlexDTO = new BrandGymPlanFlexDTO();
            brandGymPlanFlexDTO.setGymPlanId(rs.getInt("plan_id"));
            brandGymPlanFlexDTO.setGymPlanName(rs.getString("name"));
            brandGymPlanFlexDTO.setPricePerHours(rs.getDouble("price_per_hours"));
            brandGymPlanFlexDTO.setPlanBeforeActive(rs.getInt("plan_before_active_validity"));
            brandGymPlanFlexDTO.setPlanAfterActive(rs.getInt("plan_after_active_validity"));
            brandGymPlanFlexDTO.setStatus(rs.getInt("status"));
            return brandGymPlanFlexDTO;
        }, brandId);
    }

    @Override
    public int createGymPlanFlex(BrandCreateGymPlanFlexDTO brandCreateGymPlanFlexDTO) {
        return jdbcTemplate.update(IRepositoryQuery.CREATE_GYM_PLAN_FLEX,brandCreateGymPlanFlexDTO.getBrandId(),
                brandCreateGymPlanFlexDTO.getStatus(), 1, brandCreateGymPlanFlexDTO.getGymPlanName(),
                brandCreateGymPlanFlexDTO.getDescription(), brandCreateGymPlanFlexDTO.getPricePerHours(),
                brandCreateGymPlanFlexDTO.getPlanBeforeActive(), brandCreateGymPlanFlexDTO.getPlanAfterActive());
    }

    @Override
    public BrandUpdateGymPlanFlexDTO getGymPlanFlexDetail(int gymPlanId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_GYM_PLAN_FLEX_DETAIL_BY_GYM_PLAN_ID, (rs, rowNum) -> {
            BrandUpdateGymPlanFlexDTO brandUpdateGymPlanFlexDTO = new BrandUpdateGymPlanFlexDTO();
            brandUpdateGymPlanFlexDTO.setGymPlanId(rs.getInt("plan_id"));
            brandUpdateGymPlanFlexDTO.setGymPlanName(rs.getString("name"));
            brandUpdateGymPlanFlexDTO.setPricePerHours(rs.getDouble("price_per_hours"));
            brandUpdateGymPlanFlexDTO.setPlanBeforeActive(rs.getInt("plan_before_active_validity"));
            brandUpdateGymPlanFlexDTO.setPlanAfterActive(rs.getInt("plan_after_active_validity"));
            brandUpdateGymPlanFlexDTO.setStatus(rs.getInt("status"));
            brandUpdateGymPlanFlexDTO.setDescription(rs.getString("description"));
            return  brandUpdateGymPlanFlexDTO;
        }, gymPlanId);
    }

    @Override
    public int updateGymPlanFlex(BrandUpdateGymPlanFlexDTO brandUpdateGymPlanFlexDTO) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_GYM_PLAN_FLEX, brandUpdateGymPlanFlexDTO.getStatus(),
                brandUpdateGymPlanFlexDTO.getGymPlanName(), brandUpdateGymPlanFlexDTO.getDescription(),
                brandUpdateGymPlanFlexDTO.getPricePerHours(), brandUpdateGymPlanFlexDTO.getPlanBeforeActive(),
                brandUpdateGymPlanFlexDTO.getPlanAfterActive(), brandUpdateGymPlanFlexDTO.getGymPlanId());
    }

    @Override
    public List<BrandGymPlanFixedDTO> getAllGymPlanFixedByBrandId(int brandId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_ALL_GYM_PLAN_FIXED_BY_BRAND_ID, (rs, rowNum) -> {
            BrandGymPlanFixedDTO brandGymPlanFixedDTO = new BrandGymPlanFixedDTO();
            brandGymPlanFixedDTO.setGymPlanId(rs.getInt("plan_id"));
            brandGymPlanFixedDTO.setGymPlanName(rs.getString("name"));
            brandGymPlanFixedDTO.setPrice(rs.getDouble("price"));
            brandGymPlanFixedDTO.setDuration(rs.getInt("duration"));
            brandGymPlanFixedDTO.setPlanBeforeActive(rs.getInt("plan_before_active_validity"));
            brandGymPlanFixedDTO.setPlanAfterActive(rs.getInt("plan_after_active_validity"));
            brandGymPlanFixedDTO.setStatus(rs.getInt("status"));
            return brandGymPlanFixedDTO;
        }, brandId);
    }

    @Override
    public int createGymPlanFixed(BrandCreateGymPlanFixedDTO brandCreateGymPlanFixedDTO) {
        return jdbcTemplate.update(IRepositoryQuery.CREATE_GYM_PLAN_FIXED,brandCreateGymPlanFixedDTO.getBrandId(),
                brandCreateGymPlanFixedDTO.getStatus(), 2, brandCreateGymPlanFixedDTO.getGymPlanName(),
                brandCreateGymPlanFixedDTO.getDescription(), brandCreateGymPlanFixedDTO.getPrice(),
                brandCreateGymPlanFixedDTO.getDuration(),
                brandCreateGymPlanFixedDTO.getPlanBeforeActive(), brandCreateGymPlanFixedDTO.getPlanAfterActive());
    }

    @Override
    public BrandUpdateGymPlanFixedDTO getGymPlanFixedDetail(int gymPlanId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_GYM_PLAN_FIXED_DETAIL_BY_GYM_PLAN_ID, (rs, rowNum) -> {
            BrandUpdateGymPlanFixedDTO brandUpdateGymPlanFixedDTO = new BrandUpdateGymPlanFixedDTO();
            brandUpdateGymPlanFixedDTO.setGymPlanId(rs.getInt("plan_id"));
            brandUpdateGymPlanFixedDTO.setGymPlanName(rs.getString("name"));
            brandUpdateGymPlanFixedDTO.setPrice(rs.getDouble("price"));
            brandUpdateGymPlanFixedDTO.setDuration(rs.getInt("duration"));
            brandUpdateGymPlanFixedDTO.setPlanBeforeActive(rs.getInt("plan_before_active_validity"));
            brandUpdateGymPlanFixedDTO.setPlanAfterActive(rs.getInt("plan_after_active_validity"));
            brandUpdateGymPlanFixedDTO.setStatus(rs.getInt("status"));
            brandUpdateGymPlanFixedDTO.setDescription(rs.getString("description"));
            return  brandUpdateGymPlanFixedDTO;
        }, gymPlanId);
    }

    @Override
    public int updateGymPlanFixed(BrandUpdateGymPlanFixedDTO brandUpdateGymPlanFixedDTO) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_GYM_PLAN_FIXED, brandUpdateGymPlanFixedDTO.getStatus(),
                brandUpdateGymPlanFixedDTO.getGymPlanName(), brandUpdateGymPlanFixedDTO.getDescription(),
                brandUpdateGymPlanFixedDTO.getPrice(), brandUpdateGymPlanFixedDTO.getPlanBeforeActive(),
                brandUpdateGymPlanFixedDTO.getPlanAfterActive(), brandUpdateGymPlanFixedDTO.getDuration(), brandUpdateGymPlanFixedDTO.getGymPlanId()
                );
    }
}