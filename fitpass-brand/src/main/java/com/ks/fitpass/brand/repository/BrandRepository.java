package com.ks.fitpass.brand.repository;

import com.ks.fitpass.brand.entity.Brand;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandRepository {

    List<Brand> getAllByStatus(int status) throws DataAccessException;

    List<Brand> getAllByTopRating(int status) throws DataAccessException;

    Brand getOne(int id) throws DataAccessException;
}
