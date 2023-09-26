package com.ks.fitpass.core.mapper;

import com.ks.fitpass.core.entity.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
