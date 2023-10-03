package com.ks.fitpass.core.mapper;

import com.ks.fitpass.core.entity.Role;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailMapper implements RowMapper<UserDetail> {

    @Override
    public UserDetail mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = User.builder()
                .userId(resultSet.getInt("user_id"))
                .build();

        Role role = Role.builder()
                .roleId(resultSet.getInt("role_id"))
                .build();

        return UserDetail.builder()
                .user(user)
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .phoneNumber(resultSet.getString("phone_number"))
                .address(resultSet.getString("address"))
                .gender(resultSet.getString("gender"))
                .role(role)
                .build();
    }
}