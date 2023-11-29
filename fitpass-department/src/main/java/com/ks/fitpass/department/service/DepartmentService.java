package com.ks.fitpass.department.service;

import com.ks.fitpass.department.dto.*;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import com.ks.fitpass.department.entity.UserFeedback;
import org.springframework.dao.DataAccessException;

import java.util.List;


public interface DepartmentService {

    Department getByUserId(int userId) throws DataAccessException;

    List<DepartmentDTO> getAllDepartmentForHome(int pageIndex, int pageSize) throws DataAccessException;

    List<DepartmentDTO> getAllDepartmentTopRatingForHome(int pageIndex, int pageSize) throws DataAccessException;

    List<DepartmentDTO> getAllDepartmentByBrandId(int brandId, int pageIndex,int pageSize) throws DataAccessException;

    Department getOne(int id) throws DataAccessException;

    boolean update(Department department) throws DataAccessException;

    boolean updateStatusDepartment(Department department, DepartmentStatus departmentStatus) throws DataAccessException;

    List<Department> getAllDepartmentByNearbyLocation(int pageIndex, int pageSize,
                                                                double userLatitude, double userLongitude,
                                                                String city, String sortPrice, String sortRating, String belowDistance);
    List<Department> findByRatingBetween(double from, double to);


    List<UserFeedback> getDepartmentFeedback(int departmentId, int page, int size, String sortRating);


    DepartmentDTO filterDepartmentFeedbacks(int departmentId);

    List<DepartmentDTO> getDepartmentByBrandID(int brandID) throws DataAccessException;

    List<DepartmentListByBrandDTO> getAllDepartmentListOfBrand(int brandId);

    int updateDepartmentStatus(int status, int departmentId);

    int createDepartmentWithBrandId(int brandId, String name);

    int countAllDepartment(int status , String city, String sortPrice, String sortRating, double userLatitude, double userLongitude, String belowDistance);

    int updateDepartmentGymOwner(int departmentId, int userId);

    List<UserFeedbackOfBrandOwner> getAllDepartmentFeedbackOfBrandOwner(int departmentId);

    List<ListBrandDepartmentFeedback> getDepartmentFeedbackOfBrandOwner(int brandId);

    int updateGymOwnerDepartmentInfo(Department department);

    int updateGymOwnerDepartmentInfoDetails(Department department);

    int updateDepartmentImage(int departmentId, String imageLogoUrl, String imageThumbnailUrl, String imageWallpaperUrl);

    int updateLongitudeLatitude(int departmentId, double longitude, double latitude);

    boolean checkFirstTimeDepartmentCreated(int departmentId);

    int updateFirstTimeDepartmentCreated(int departmentId);

    int countAllFeedback(int departmentId, String sortRating);

    DepartmentNotificationDTO getDepartmentNotificationDtoById(int departmentId);
}
