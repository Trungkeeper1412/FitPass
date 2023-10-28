package com.ks.fitpass.department.repository;


import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.UserFeedback;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentRepository {

    Department getByUserId(int userId) throws DataAccessException;

    List<Department> getAllByStatus(int status) throws DataAccessException;

    List<Department> getAllByTopRating(int status) throws DataAccessException;

    Department getOne(int id) throws DataAccessException;

    boolean update(Department department) throws DataAccessException;

    List<Department> findByRatingBetween(double from, double to);

    List<UserFeedback> getDepartmentFeedback(int departmentId);

    List<Department> getDepartmentByBrandID(int status, int brandID) throws DataAccessException;

}
