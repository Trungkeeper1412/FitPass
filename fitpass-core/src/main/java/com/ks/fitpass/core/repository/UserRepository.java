package com.ks.fitpass.core.repository;

import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDTO;
import com.ks.fitpass.core.entity.UserDetail;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserRepository {
    List<UserDTO> getAllAccountUser();

    User findByAccount(String email);

    boolean updatePassword(String newPassword, int userId) throws DataAccessException;

    int insertIntoUserDetail(UserDetail userDetail);

    int insertIntoUserDetailRegister(UserDetail userDetail);

    Integer getLastInsertUserDetailId(UserDetail userDetail);

    Integer getLastInsertUserDetailIdRegister(UserDetail userDetail);


    int insertIntoUser(User user);

    Integer getLastUserInsertId(User user);

    int insertIntoUserRole(int userId, int roleId);

    Integer getNumberOfAccountCreatedByBrandId(int brandId);

    List<GymOwnerListDTO> getAllAccountByBrandId(int brandId);

    int updateUserDetail(UserDetail userDetail);

    int updateUserStatusByUserId(int userId, int status);

    UserDetail getUserDetailByUserDetailId(int userId);

    UserDetail getUserDetailByUserId(int userId);

    boolean checkEmailExist(String email);

    boolean checkUsernameExist(String username);

    boolean checkAccountFirstTimeLogin(int userId);

    int updateFirstTimeLoginStatus(int userId, int status);

    List<GymOwnerListDTO> getAllAccountByDepartmentId(int departmentId);

    Integer getNumberOfAccountCreatedByDepartmentId(int departmentId);

    Integer getDepartmentIdByEmployeeId(int employeeId);

    int createEmployee(User user);

    Integer countAllUsersAccount();

    int resetPassword(String email, String hashedPassword);
}
