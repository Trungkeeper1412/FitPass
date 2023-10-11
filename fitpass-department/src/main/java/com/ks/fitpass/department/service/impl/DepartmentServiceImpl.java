package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import com.ks.fitpass.department.repository.DepartmentRepository;
import com.ks.fitpass.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getByUserId(int userId) throws DataAccessException {
        return departmentRepository.getByUserId(userId);
    }

    @Override
    public List<DepartmentDTO> getAllDepartmentForHome(int pageIndex, int pageSize) throws DataAccessException {
        // to do : implement paging
        List<Department> departments = departmentRepository.getAllByStatus(1);
        return departments.stream().map(this::departmentDTOMapper).collect(Collectors.toList());

    }

    @Override
    public List<DepartmentDTO> getAllDepartmentTopRatingForHome(int pageIndex, int pageSize) throws DataAccessException {
        // to do : implement paging
        List<Department> departments = departmentRepository.getAllByTopRating(1);
        return departments.stream().map(this::departmentDTOMapper).collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDTO> getAllDepartmentByNearbyLocation(int pageIndex, int pageSize, double userLatitude, double userLongitude, double radiusInMeters) {
        // To do: Implement paging
        List<Department> allDepartments = departmentRepository.getAllByStatus(1);

        // Sorting and mapping to DTO
        return allDepartments.stream()
                .sorted(Comparator.comparingDouble(department ->
                        calculateDistance(userLatitude, userLongitude,
                                department.getLatitude(), department.getLongitude())))
                .map(this::departmentDTOMapper)
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        // Haversine formula
        double dLat = radLat2 - radLat1;
        double dLon = radLon2 - radLon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(radLat1) * Math.cos(radLat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance in kilometers
        return 6371 * c;
    }

    DepartmentDTO departmentDTOMapper(Department department) {
        return DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .departmentAddress(department.getDepartmentAddress())
                .departmentImageUrl(department.getDepartmentImageUrl())
                .build();
    }

    @Override
    public List<Department> getAllByStatus(int status) throws DataAccessException {
        return departmentRepository.getAllByStatus(status);
    }

    @Override
    public Department getOne(int id) throws DataAccessException {
        return departmentRepository.getOne(id);
    }

    @Override
    public boolean update(Department department) throws DataAccessException {
        return departmentRepository.update(department);
    }

    @Override
    public boolean updateStatusDepartment(Department department, DepartmentStatus departmentStatus) throws DataAccessException {
        department.setDepartmentStatus(DepartmentStatus.builder()
                .departmentStatusCd(departmentStatus.getDepartmentStatusCd())
                .departmentStatusName(departmentStatus.getDepartmentStatusName())
                .build()
        );
        return departmentRepository.update(department);
    }


}
