package com.ks.fitpass.department.service;

import com.ks.fitpass.department.entity.DepartmentAlbums;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartmentAlbumsService {
    List<DepartmentAlbums> getAllByDepartmentID(int departmentID) throws DataAccessException;

    int[] addDepartmentAlbums(List<DepartmentAlbums> departmentAlbums) throws DataAccessException;
    int deleteAllAlbumsByDepartmentID(int departmentID) throws DataAccessException;
}
