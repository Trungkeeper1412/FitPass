package com.ks.fitpass.brand.service.impl;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.brand.repository.BrandRepository;
import com.ks.fitpass.department.entity.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;


    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAllByStatus(int status, int page, int size, String sortPrice, String sortRating) throws DataAccessException {
        return brandRepository.getAllByStatus(status, page, size, sortPrice, sortRating);
    }

    @Override
    public List<Brand> getAllByTopRating(int status) throws DataAccessException {
        return brandRepository.getAllByTopRating(status);
    }

    @Override
    public Brand getOne(int id) throws DataAccessException {
        Brand brand =  brandRepository.getOne(id);
        return brand;

    }

    @Override
    public int countAllBrands(int status, String sortRating) {
        return brandRepository.countAllBrands(status, sortRating);
    }
}
