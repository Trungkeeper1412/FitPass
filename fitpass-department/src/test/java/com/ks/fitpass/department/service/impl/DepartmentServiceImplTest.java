package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        // Define test data
        int pageIndex = 1;
        int pageSize = 10;
        double userLatitude = 21.00418734116798;
        double userLongitude = 105.52004332513577;
        double radiusInMeters = 5000;

        // Create a list of departments for testing
        Department superGymHoaLac = new Department();
        superGymHoaLac.setLatitude(20.99485364);
        superGymHoaLac.setLongitude(105.52473891);

        Department GymHoaLac = new Department();
        GymHoaLac.setLatitude(20.98475659);
        GymHoaLac.setLongitude(105.53040374);

        Department FitWayKickboxing = new Department();
        FitWayKickboxing.setLatitude(21.01320288);
        FitWayKickboxing.setLongitude(105.51898826);

        List<Department> testDepartments = Arrays.asList(superGymHoaLac, GymHoaLac, FitWayKickboxing);
        when(departmentRepository.getAllByStatus(1)).thenReturn(testDepartments);

        // Call the method under test
        List<DepartmentDTO> nearbyDepartments = departmentService.getAllDepartmentByNearbyLocation(pageIndex, pageSize, userLatitude, userLongitude, radiusInMeters);

        // Assertions
        // Verify that the departmentRepository method was called with the correct arguments
        verify(departmentRepository).getAllByStatus(1);

        // Verify that the returned list of nearby departments is as expected
        // You can add assertions here to check if the nearbyDepartments list contains the expected DTOs.

        // Example assertion:
        assertEquals(3, nearbyDepartments.size()); // Check that the list is empty in this example
    }
}