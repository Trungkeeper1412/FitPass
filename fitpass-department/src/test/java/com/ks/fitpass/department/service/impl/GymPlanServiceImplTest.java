package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.core.repository.KbnRepository;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.GymPlan;
import com.ks.fitpass.department.repository.DepartmentRepository;
import com.ks.fitpass.department.repository.GymPlanRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GymPlanServiceImplTest {
    private static GymPlanRepository gymPlanRepository;
    private static KbnRepository kbnRepository;
    private static GymPlanServiceImpl gymPlanService;

    @BeforeAll
    static void setUp(){
        // Create a mock for the DepartmentRepository and KbnRepository
        gymPlanRepository = mock(GymPlanRepository.class);
        kbnRepository = mock(KbnRepository.class);

        // Initialize the DepartmentServiceImpl with the mock repository
        gymPlanService = new GymPlanServiceImpl(gymPlanRepository,kbnRepository);
    }

    @DisplayName("Test Get Gym Plan Details by Department ID")
    @Test
    void testGetGymPlanDetailsByDepartmentId_WhenDepartmentExists_ShouldRetrieveGymPlanDetails() {
        // Arrange
        int departmentId = 1;
        GymPlan testGymPlan = GymPlan.builder()
                .planId(1)
                .gymPlanName("Sample Plan")
                .gymDepartmentId(departmentId)
                .gymPlanKey(1)
                .build();

        // Arrange: Set up mock behavior for gymPlanRepository
        when(gymPlanRepository.getAllByDepartmentId(departmentId)).
                thenReturn(Collections.singletonList(testGymPlan));

        // Arrange: Set up mock behavior for kbnRepository
        when(kbnRepository.getGymPlanTypeByPlanKey(testGymPlan.getGymPlanKey())).
                thenReturn("Gói theo giờ");

        // Act: Call the method to test
        List<GymPlanDto> result = gymPlanService.getGymPlanDetailsByDepartmentId(departmentId);

        // Assert
        assertEquals(1, result.size(), ()-> "No Gym Plan Was Found by the department id = " +departmentId);
        GymPlanDto dto = result.get(0);
        assertEquals(testGymPlan.getPlanId(), dto.getGymPlanId(),"DTO Gym Plan ID doesn't match");
        assertEquals("Sample Plan", dto.getGymPlanName(), "DTO Gym Plan Name doesn't match");
        assertEquals("Gói theo giờ", dto.getGymPlanType(), "DTO Gym Plan Type doesn't match");
    }

    @Test
    void testGetGymPlanByGymPlanId() {
    }

}