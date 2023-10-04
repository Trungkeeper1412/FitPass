package com.ks.fitpass.core.repository;

import com.ks.fitpass.core.entity.UserDetail;
import org.springframework.dao.DataAccessException;

public interface UserDetailRepository {

    UserDetail findByUserId(int userId);

    boolean updateUserDetail(UserDetail userDetail) throws DataAccessException;
}