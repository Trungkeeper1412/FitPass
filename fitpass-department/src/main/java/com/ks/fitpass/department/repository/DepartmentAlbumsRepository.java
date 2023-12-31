package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.DepartmentAlbums;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentAlbumsRepository {
    List<DepartmentAlbums> getAllByDepartmentID(int departmentID) throws DataAccessException;

    int deleteAllAlbumsByDepartmentID(int departmentID) throws DataAccessException;

    int[] addDepartmentAlbums(List<DepartmentAlbums> departmentAlbums) throws DataAccessException;
}
