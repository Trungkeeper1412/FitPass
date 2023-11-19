package com.ks.fitpass.brand.service;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandAmenitieService {
    List<BrandAmenitie> getAllByBrandID(int brandID) throws DataAccessException;

    List<BrandAmenitie> getAllByBrandIDActivate(int brandID) throws DataAccessException;

    BrandAmenitie getAmenitieDetail(int amenitieId);

    int createBrandAmenitie(BrandAmenitie amenitie);

    int updateBrandAmenitie(BrandAmenitie amenitie);

    int updateStatusBrandAmenitie(int status);
}
