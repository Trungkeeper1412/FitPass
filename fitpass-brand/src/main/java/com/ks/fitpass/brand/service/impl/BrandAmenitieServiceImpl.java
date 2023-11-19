package com.ks.fitpass.brand.service.impl;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.repository.BrandAmenitieRepository;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandAmenitieServiceImpl implements BrandAmenitieService {
    private final BrandAmenitieRepository brandAmenitieRepository;
    @Autowired
    public BrandAmenitieServiceImpl(BrandAmenitieRepository brandAmenitieRepository) {
        this.brandAmenitieRepository = brandAmenitieRepository;
    }

    @Override
    public List<BrandAmenitie> getAllByBrandID(int brandID) throws DataAccessException {
        return brandAmenitieRepository.getAllByBrandID(brandID);
    }

    @Override
    public List<BrandAmenitie> getAllByBrandIDActivate(int brandID) throws DataAccessException {
        return brandAmenitieRepository.getAllByBrandIDActivate(brandID);
    }

    @Override
    public BrandAmenitie getAmenitieDetail(int amenitieId) {
        return brandAmenitieRepository.getAmenitieDetail(amenitieId);
    }

    @Override
    public int createBrandAmenitie(BrandAmenitie amenitie) {
        return brandAmenitieRepository.createBrandAmenitie(amenitie);
    }

    @Override
    public int updateBrandAmenitie(BrandAmenitie amenitie) {
        return brandAmenitieRepository.updateBrandAmenitie(amenitie);
    }

    @Override
    public int updateStatusBrandAmenitie(int status) {
        return brandAmenitieRepository.updateStatusBrandAmenitie(status);
    }
}
