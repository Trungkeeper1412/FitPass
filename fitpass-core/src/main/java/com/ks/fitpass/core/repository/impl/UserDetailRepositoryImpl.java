package com.ks.fitpass.core.repository.impl;

import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.mapper.UserDetailMapper;
import com.ks.fitpass.core.repository.IRepositoryQuery;
import com.ks.fitpass.core.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailRepositoryImpl implements UserDetailRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDetailRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetail findByUserId(int userId) {
        return jdbcTemplate.queryForObject(GET_USER_DETAIL_BY_USER_ID, new UserDetailMapper(), userId);
    }

    @Override
    public boolean updateUserDetail(UserDetail userDetail) throws DataAccessException {
        return jdbcTemplate.update(
                UPDATE_USER_DETAIL,
                userDetail.getFirstName(),
                userDetail.getLastName(),
                userDetail.getEmail(),
                userDetail.getPhoneNumber(),
                userDetail.getAddress(),
                userDetail.getGender(),
                userDetail.getUser().getUserId()
        ) > 0;
    }
}