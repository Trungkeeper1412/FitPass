package com.ks.fitpass.core.service;

import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDTO;
import com.ks.fitpass.core.entity.UserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDTO> getAllAccountUser();

    int insertIntoUserDetail(UserDetail userDetail);

    int getLastInsertUserDetailId(UserDetail userDetail);

    int insertIntoUser(User user);

    int getLastUserInsertId(User user);

    int insertIntoUserRole(int userId, int roleId);

    int getNumberOfAccountCreatedByBrandId(int brandId);

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
    int getNumberOfAccountCreatedByDepartmentId(int departmentId);

    User getUserByAccount(String username);
}
