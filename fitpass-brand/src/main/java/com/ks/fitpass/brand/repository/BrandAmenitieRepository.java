package com.ks.fitpass.brand.repository;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandAmenitieRepository {

    List<BrandAmenitie> getAllByBrandID(int brandID) throws DataAccessException;

}
