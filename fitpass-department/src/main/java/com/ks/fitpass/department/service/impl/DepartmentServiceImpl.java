package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.dto.*;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import com.ks.fitpass.department.entity.UserFeedback;
import com.ks.fitpass.department.repository.DepartmentRepository;
import com.ks.fitpass.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    DepartmentDTO departmentDTOMapper(Department department) {
        return DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .departmentAddress(department.getDepartmentAddress())
                .departmentPhone(department.getDepartmentContactNumber())

                .departmentWallpaperUrl(department.getDepartmentWallpaperUrl())
                .departmentThumbnailUrl(department.getDepartmentThumbnailUrl())
                .rating(department.getRating())
                .capacity(department.getCapacity())
                .area(department.getArea())
                .maxPrice(department.getMaxPrice())
                .minPrice(department.getMinPrice())
                .feedbackCount(department.getFeedbackCount())
                .build();
    }

    @Override
    public List<DepartmentDTO> getAllDepartmentByBrandId(int brandID, int pageIndex, int pageSize) throws DataAccessException {
        List<Department> departments = departmentRepository.getAllByBrandId(brandID,1);
        return departments.stream().map(this::departmentDTOMapper).collect(Collectors.toList());
    }

    @Override
    public List<Department> getAllDepartmentByNearbyLocation(int page, int size,
                                                                       double userLatitude, double userLongitude,
                                                                    String city, String sortPrice, String sortRating, String belowDistance) {

        return departmentRepository.getAllByStatus(1, page, size, city, sortPrice, sortRating, userLatitude, userLongitude, belowDistance);
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
    public List<UserFeedback> getDepartmentFeedback(int departmentId, int page, int size, String sortRating) {
        return departmentRepository.getDepartmentFeedbackPagnition(departmentId, page, size, sortRating);
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

    @Override
    public List<DepartmentListByBrandDTO> getAllDepartmentListOfBrand(int brandId) {
        return departmentRepository.getAllDepartmentListOfBrand(brandId);
    }

    @Override
    public int updateDepartmentStatus(int status, int departmentId) {
        return departmentRepository.updateDepartmentStatus(status, departmentId);
    }

    @Override
    public int createDepartmentWithBrandId(int brandId, String name) {
        return departmentRepository.createDepartmentWithBrandId(brandId, name);
    }

    @Override
    public int countAllDepartment(int status , String city, String sortPrice, String sortRating, double userLatitude, double userLongitude, String belowDistance) {
        return departmentRepository.countAllDepartment(status, city, sortPrice, sortRating, userLatitude, userLongitude, belowDistance);
    }

    @Override
    public int updateDepartmentGymOwner(int departmentId, int userId) {
        return departmentRepository.updateDepartmentGymOwner(departmentId, userId);
    }

    @Override
    public List<UserFeedbackOfBrandOwner> getAllDepartmentFeedbackOfBrandOwner(int departmentId) {
        return departmentRepository.getAllDepartmentFeedbackOfBrandOwner(departmentId);
    }

    @Override
    public List<ListBrandDepartmentFeedback> getDepartmentFeedbackOfBrandOwner(int brandId) {
        return departmentRepository.getDepartmentFeedbackOfBrandOwner(brandId);
    }

    @Override
    public int updateGymOwnerDepartmentInfo(Department department) {
        return departmentRepository.updateGymOwnerDepartmentInfo(department);
    }

    @Override
    public int updateGymOwnerDepartmentInfoDetails(Department department) {
        return departmentRepository.updateGymOwnerDepartmentInfoDetails(department);
    }

    @Override
    public int updateDepartmentImage(int departmentId, String imageLogoUrl, String imageThumbnailUrl, String imageWallpaperUrl) {
        return departmentRepository.updateDepartmentImage(departmentId, imageLogoUrl, imageThumbnailUrl, imageWallpaperUrl);
    }

    @Override
    public int updateLongitudeLatitude(int departmentId, double longitude, double latitude) {
        return departmentRepository.updateLongitudeLatitude(departmentId, longitude, latitude);
    }

    @Override
    public boolean checkFirstTimeDepartmentCreated(int departmentId) {
        return departmentRepository.checkFirstTimeDepartmentCreated(departmentId);
    }

    @Override
    public int updateFirstTimeDepartmentCreated(int departmentId) {
        return departmentRepository.updateFirstTimeDepartmentCreated(departmentId);
    }

    @Override
    public int countAllFeedback(int departmentId, String sortRating) {
        Integer count = departmentRepository.countAllFeedback(departmentId, sortRating);
        return (count != null) ? count : 0;
    }

    @Override
    public DepartmentNotificationDTO getDepartmentNotificationDtoById(int departmentId) {
        return departmentRepository.getDepartmentNotificationDtoById(departmentId);
    }


    @Override
    public int countAllDepartment() {
        Integer count = departmentRepository.countAllDepartment();
        return (count != null) ? count : 0;
    }

    @Override
    public List<DepartmentStatBrandOwner> getDepartmentStatBrandOwner(int brandId) {
        List<DepartmentStatBrandOwner> list = departmentRepository.getDepartmentStatBrandOwner(brandId);
        return (list != null) ? list : new ArrayList<>();
    }

    @Override
    public List<DepartmentRatingStatBrandOwner> getDepartmentRatingStatBrandOwner(int brandId) {
        List<DepartmentRatingStatBrandOwner> list = departmentRepository.getDepartmentRatingStatBrandOwner(brandId);
        return (list != null) ? list : new ArrayList<>();
    }

    @Override
    public int getTotalNumberRatingByDepartmentId(int departmentId) {
        Integer count = departmentRepository.getTotalNumberRatingByDepartmentId(departmentId);
        return (count != null) ? count : 0;
    }
}


