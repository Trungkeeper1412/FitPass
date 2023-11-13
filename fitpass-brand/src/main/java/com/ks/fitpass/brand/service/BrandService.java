package com.ks.fitpass.brand.service;

import com.ks.fitpass.brand.entity.Brand;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandService {

    List<Brand> getAllByStatus(int status, int page, int size, String sortPrice, String sortRating) throws DataAccessException;

    List<Brand> getAllByTopRating(int status) throws DataAccessException;

    Brand getOne(int id) throws DataAccessException;

    int countAllBrands(int status, String sortRating);

}
