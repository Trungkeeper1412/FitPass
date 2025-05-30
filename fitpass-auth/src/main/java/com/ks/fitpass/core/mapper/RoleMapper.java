package com.ks.fitpass.core.mapper;

import com.ks.fitpass.core.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        return Role.builder()
                .roleId(resultSet.getInt("role_id"))
                .roleName(resultSet.getString("role_name"))
                .build();
    }
}
