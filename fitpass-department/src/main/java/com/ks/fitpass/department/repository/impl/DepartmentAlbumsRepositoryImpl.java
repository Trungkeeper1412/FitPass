package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.mapper.DepartmentAlbumsMapper;
import com.ks.fitpass.department.repository.DepartmentAlbumsRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Repository
public class DepartmentAlbumsRepositoryImpl implements DepartmentAlbumsRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentAlbumsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<DepartmentAlbums> getAllByDepartmentID(int departmentID) throws DataAccessException {
        return jdbcTemplate.query(GET_DEPARTMENT_ALBUMS_BY_ID_DEPARTMENT, new DepartmentAlbumsMapper(), departmentID);
    }

    @Override
    public int deleteAllAlbumsByDepartmentID(int departmentID) throws DataAccessException{
        return jdbcTemplate.update(DELETE_DEPARTMENT_ALBUMS_BY_ID_DEPARTMENT, departmentID);
    }

    @Override
    public int[] addDepartmentAlbums(List<DepartmentAlbums> departmentAlbums) throws DataAccessException{
        return jdbcTemplate.batchUpdate(INSERT_DEPARTMENT_ALBUM, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, departmentAlbums.get(i).getDepartmentId());
                ps.setString(2, departmentAlbums.get(i).getPhotoUrl());
            }

            @Override
            public int getBatchSize() {
                return departmentAlbums.size();
            }
        });
    }
}

