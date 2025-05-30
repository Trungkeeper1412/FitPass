package com.ks.fitpass.core.mapper;

import com.ks.fitpass.core.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return User.builder()
                .userId(resultSet.getInt("user_id"))
                .userAccount(resultSet.getString("user_account"))
                .userPassword(resultSet.getString("user_password"))
                .userCreateTime(resultSet.getLong("user_create_time"))
                .userDeleted(resultSet.getBoolean("user_deleted"))
                .build();
    }
}
