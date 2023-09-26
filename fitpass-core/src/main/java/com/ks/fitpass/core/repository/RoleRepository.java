package com.ks.fitpass.core.repository;

import com.ks.fitpass.core.entity.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> getRolesByUserAccount(String email);
}
