package com.ks.fitpass.core.repository;

import com.ks.fitpass.core.entity.User;
import org.springframework.dao.DataAccessException;

public interface UserRepository {

    User findByAccount(String email);

    boolean updatePassword(String newPassword, int userId) throws DataAccessException;
}
