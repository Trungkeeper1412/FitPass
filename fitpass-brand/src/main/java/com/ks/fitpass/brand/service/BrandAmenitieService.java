package com.ks.fitpass.brand.service;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandAmenitieService {
    List<BrandAmenitie> getAllByBrandID(int brandID) throws DataAccessException;

}
