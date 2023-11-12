package com.ks.fitpass.department.service;

import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentListByBrandDTO;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import com.ks.fitpass.department.entity.UserFeedback;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;


public interface DepartmentService {

    Department getByUserId(int userId) throws DataAccessException;

    List<DepartmentDTO> getAllDepartmentForHome(int pageIndex, int pageSize) throws DataAccessException;

    List<DepartmentDTO> getAllDepartmentTopRatingForHome(int pageIndex, int pageSize) throws DataAccessException;

    List<DepartmentDTO> getAllDepartmentByBrandId(int brandId, int pageIndex,int pageSize) throws DataAccessException;

//    List<Department> getAllByStatus(int status) throws DataAccessException;

    Department getOne(int id) throws DataAccessException;

    boolean update(Department department) throws DataAccessException;

    boolean updateStatusDepartment(Department department, DepartmentStatus departmentStatus) throws DataAccessException;

    List<Department> getAllDepartmentByNearbyLocation(int pageIndex, int pageSize,
                                                                double userLatitude, double userLongitude,
                                                                String city, String sortPrice, String sortRating, String belowDistance);
    List<Department> findByRatingBetween(double from, double to);


    List<UserFeedback> getDepartmentFeedback(int departmentId, int page, int size);


    DepartmentDTO filterDepartmentFeedbacks(int departmentId);

    List<DepartmentDTO> getDepartmentByBrandID(int brandID) throws DataAccessException;

    List<DepartmentListByBrandDTO> getAllDepartmentListOfBrand(int brandId);

    int updateDepartmentStatus(int status, int departmentId);

    int createDepartmentWithBrandId(int brandId, String name);

    int countAllDepartment(int status , String city, String sortPrice, String sortRating, double userLatitude, double userLongitude, String belowDistance);
}
