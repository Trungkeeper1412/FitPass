package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import com.ks.fitpass.department.entity.UserFeedback;
import com.ks.fitpass.department.repository.DepartmentRepository;
import com.ks.fitpass.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    DepartmentDTO departmentDTOMapper(Department department) {
        return DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .departmentAddress(department.getDepartmentAddress())
                .departmentPhone(department.getDepartmentContactNumber())

                .departmentWallpaperUrl(department.getDepartmentWallpaperUrl())
                .rating(department.getRating())
                .capacity(department.getCapacity())
                .area(department.getArea())

                .build();
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
    public List<DepartmentDTO> getAllDepartmentByBrandId(int brandID, int pageIndex, int pageSize) throws DataAccessException {
        List<Department> departments = departmentRepository.getAllByBrandId(brandID,1);
        return departments.stream().map(this::departmentDTOMapper).collect(Collectors.toList());
    }

    @Override
    public Map<DepartmentDTO, Double> getAllDepartmentByNearbyLocation(int pageIndex, int pageSize,
                                                                       double userLatitude, double userLongitude,
                                                                       double radiusInKm) {
        // To do: Implement paging
        List<Department> allDepartments = departmentRepository.getAllByStatus(1);

        // Create a map to associate DepartmentDTO with its distance
        Map<DepartmentDTO, Double> departmentDistanceMap = new LinkedHashMap<>();

        // Calculate and add distance for each department
        for (Department department : allDepartments) {
            double distance = calculateDistance(userLatitude, userLongitude, department.getLatitude(), department.getLongitude());
            if (distance <= radiusInKm) {
                DepartmentDTO departmentDTO = departmentDTOMapper(department);
                departmentDistanceMap.put(departmentDTO, distance);
            }
        }

        // Sort the map by distance
        departmentDistanceMap = departmentDistanceMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return departmentDistanceMap;
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

    @Override
    public List<Department> getAllByStatus(int status) throws DataAccessException {
        return departmentRepository.getAllByStatus(status);
    }

    @Override
    public Department getOne(int id) throws DataAccessException {
        Department department =  departmentRepository.getOne(id);
        // tinh 5 bien
        int total =0;


        //setter vao department
//        department.setTotal(total);

        return department;

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


    @Override
    public List<Department> findByRatingBetween(double from, double to) {
        return departmentRepository.findByRatingBetween(from, to);
    }

    @Override
    public List<UserFeedback> getDepartmentFeedback(int departmentId) {
        return departmentRepository.getDepartmentFeedback(departmentId);
    }

    @Override
    public DepartmentDTO filterDepartmentFeedbacks(int departmentId) {
        List<UserFeedback> feedbacks = departmentRepository.getDepartmentFeedback(departmentId);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setTotal(feedbacks.size());

        int total5 = 0;
        int total4 = 0;
        int total3 = 0;
        int total2 = 0;
        int total1 = 0;
        int total = 0;

        for (UserFeedback fb : feedbacks) {
            int rating = fb.getRating();
            total += rating;

            switch (rating) {
                case 5:
                    total5++;
                    break;
                case 4:
                    total4++;
                    break;
                case 3:
                    total3++;
                    break;
                case 2:
                    total2++;
                    break;
                case 1:
                    total1++;
                    break;
            }
        }

        departmentDTO.setTotal5(total5);
        departmentDTO.setTotal4(total4);
        departmentDTO.setTotal3(total3);
        departmentDTO.setTotal2(total2);
        departmentDTO.setTotal1(total1);

        double avgRating = feedbacks.isEmpty() ? 0 : (double) total / feedbacks.size();
        departmentDTO.setAvgRating(avgRating);

        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> getDepartmentByBrandID(int brandID) throws DataAccessException {
        List<Department> departments = departmentRepository.getDepartmentByBrandID(1, brandID);
        return departments.stream().map(this::departmentDTOMapper).collect(Collectors.toList());
    }

}


