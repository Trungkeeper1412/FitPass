package com.ks.fitpass.department.repository;


import com.ks.fitpass.department.dto.DepartmentListByBrandDTO;
import com.ks.fitpass.department.dto.ListBrandDepartmentFeedback;
import com.ks.fitpass.department.dto.UserFeedbackOfBrandOwner;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.UserFeedback;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentRepository {

    Department getByUserId(int userId) throws DataAccessException;

    List<Department> getAllByStatus(int status, int page, int size, String city, String sortPrice, String sortRating, double userLatitude, double userLongitude, String belowDistance) throws DataAccessException;

    List<Department> getAllByTopRating(int status) throws DataAccessException;

    List<Department> getAllByBrandId(int brandId, int status) throws DataAccessException;

    Department getOne(int id) throws DataAccessException;

    boolean update(Department department) throws DataAccessException;

    List<Department> findByRatingBetween(double from, double to);

    List<UserFeedback> getDepartmentFeedback(int departmentId);

    List<Department> getDepartmentByBrandID(int status, int brandID) throws DataAccessException;

    List<UserFeedback> getDepartmentFeedbackPagnition(int departmentId, int page, int size);

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
}
