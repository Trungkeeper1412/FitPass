package com.ks.fitpass.core.service;

import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;

import java.util.List;

public interface UserService {
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
}
