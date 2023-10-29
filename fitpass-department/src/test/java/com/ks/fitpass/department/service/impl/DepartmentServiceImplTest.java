package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Test For DepartmentService Class")
public class DepartmentServiceImplTest {

    private DepartmentServiceImpl departmentService;
    private DepartmentRepository departmentRepository;


    @BeforeEach
    void setUp() {
        // Create a mock for the DepartmentRepository
        departmentRepository = mock(DepartmentRepository.class);
        // Initialize the DepartmentServiceImpl with the mock repository
        departmentService = new DepartmentServiceImpl(departmentRepository);
    }

    @Test
    void getAllDepartmentByNearbyLocation() {
        //Arrange
        int pageIndex = 1;
        int pageSize = 10;
        double userLatitude = 21.00418734116798;
        double userLongitude = 105.52004332513577;
        double radiusInMeters = 5000;

        Department superGymHoaLac = Department.builder()
                .latitude(20.99485364)
                .longitude(105.52473891)
                .build();
        Department GymHoaLac = Department.builder()
                .latitude(20.98475659)
                .longitude(105.53040374)
                .build();
        Department FitWayKickboxing = Department.builder()
                .latitude(21.01320288)
                .longitude(105.51898826)
                .build();
        //Arrange: mock the behavior
        List<Department> testDepartments = Arrays.asList(superGymHoaLac, GymHoaLac, FitWayKickboxing);
        when(departmentRepository.getAllByStatus(1)).thenReturn(testDepartments);

        //Act
        Map<DepartmentDTO,Double> nearbyDepartments = departmentService
                .getAllDepartmentByNearbyLocation(pageIndex, pageSize, userLatitude, userLongitude, radiusInMeters);

        // Assertions
        // Verify that the departmentRepository method was called with the correct arguments
        verify(departmentRepository).getAllByStatus(1);
        assertEquals(3, nearbyDepartments.size()); // Check that the list is empty in this example
    }
}