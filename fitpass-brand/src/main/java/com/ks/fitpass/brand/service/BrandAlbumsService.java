package com.ks.fitpass.brand.service;

import com.ks.fitpass.brand.entity.BrandAlbums;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandAlbumsService {

    List<BrandAlbums> getAllByBrandID(int brandID) throws DataAccessException;

}
