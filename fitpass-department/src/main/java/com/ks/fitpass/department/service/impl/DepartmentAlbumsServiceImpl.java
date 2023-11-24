package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.repository.DepartmentAlbumsRepository;
import com.ks.fitpass.department.service.DepartmentAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentAlbumsServiceImpl implements DepartmentAlbumsService {
    private final DepartmentAlbumsRepository departmentAlbumsRepository;

    @Autowired
    public DepartmentAlbumsServiceImpl(DepartmentAlbumsRepository departmentAlbumsRepository) {
        this.departmentAlbumsRepository = departmentAlbumsRepository;
    }

    @Override
    public List<DepartmentAlbums> getAllByDepartmentID(int departmentID) throws DataAccessException {
        return departmentAlbumsRepository.getAllByDepartmentID(departmentID);
    }

    @Override
    public int[] addDepartmentAlbums(List<DepartmentAlbums> departmentAlbums) throws DataAccessException {
        return departmentAlbumsRepository.addDepartmentAlbums(departmentAlbums);
    }

    @Override
    public int deleteAllAlbumsByDepartmentID(int departmentID) throws DataAccessException {
        return departmentAlbumsRepository.deleteAllAlbumsByDepartmentID(departmentID);
    }
}
