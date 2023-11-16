package com.ks.fitpass.core.repository;

import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserRepository {

    User findByAccount(String email);

    boolean updatePassword(String newPassword, int userId) throws DataAccessException;

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
