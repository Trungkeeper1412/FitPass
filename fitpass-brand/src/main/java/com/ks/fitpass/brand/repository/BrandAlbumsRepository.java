package com.ks.fitpass.brand.repository;

import com.ks.fitpass.brand.entity.BrandAlbums;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandAlbumsRepository {
    List<BrandAlbums> getAllByBrandID(int brandID) throws DataAccessException;

}
