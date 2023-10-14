package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.mapper.DepartmentAlbumsMapper;
import com.ks.fitpass.department.repository.DepartmentAlbumsRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DepartmentAlbumsRepositoryImpl implements DepartmentAlbumsRepository, IRepositoryQuery {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentAlbumsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<DepartmentAlbums> getAllByDepartmentID(int departmentID) throws DataAccessException {
        return jdbcTemplate.query(GET_DEPARTMENT_ALBUMS_BY_ID_DEPARTMENT, new DepartmentAlbumsMapper(), departmentID);
    }
}

