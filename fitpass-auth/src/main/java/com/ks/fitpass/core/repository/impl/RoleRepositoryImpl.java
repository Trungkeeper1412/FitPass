package com.ks.fitpass.core.repository.impl;

import com.ks.fitpass.core.entity.Role;
import com.ks.fitpass.core.mapper.RoleMapper;
import com.ks.fitpass.core.repository.IRepositoryQuery;
import com.ks.fitpass.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> getRolesByUserAccount(String account) {
        return jdbcTemplate.query(GET_ROLES_BY_USER_ACCOUNT, new RoleMapper(), account);
    }
}
