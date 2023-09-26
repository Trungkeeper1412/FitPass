package com.ks.fitpass.core.repository.impl;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.mapper.UserMapper;
import com.ks.fitpass.core.repository.IRepositoryQuery;
import com.ks.fitpass.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByAccount(String account) {
        return jdbcTemplate.queryForObject(GET_USER_BY_USER_ACCOUNT, new UserMapper(), account);
    }

    @Override
    public boolean updatePassword(String newPassword, int userId) throws DataAccessException {
        return jdbcTemplate.update(
                UPDATE_USER_PASSWORD,
                newPassword,
                userId
        ) > 0;
    }
}
