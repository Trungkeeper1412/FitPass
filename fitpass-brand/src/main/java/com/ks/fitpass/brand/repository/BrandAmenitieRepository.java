package com.ks.fitpass.brand.repository;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandService;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandAmenitieRepository {

    List<BrandAmenitie> getAllByBrandID(int brandID) throws DataAccessException;

    BrandAmenitie getAmenitieDetail(int amenitieId);

    int createBrandAmenitie(BrandAmenitie amenitie);

    int updateBrandAmenitie(BrandAmenitie amenitie);

    int updateStatusBrandAmenitie(int status);
}
