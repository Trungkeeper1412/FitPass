package com.ks.fitpass.brand.service.impl;

import com.ks.fitpass.brand.entity.BrandAlbums;
import com.ks.fitpass.brand.repository.BrandAlbumsRepository;
import com.ks.fitpass.brand.service.BrandAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandAlbumsServiceImpl implements BrandAlbumsService {
    private final BrandAlbumsRepository brandAlbumsRepository;

    @Autowired
    public BrandAlbumsServiceImpl(BrandAlbumsRepository brandAlbumsRepository) {
        this.brandAlbumsRepository = brandAlbumsRepository;
    }

    @Override
    public List<BrandAlbums> getAllByBrandID(int brandID) throws DataAccessException {
        return brandAlbumsRepository.getAllByBrandID(brandID);
    }
}
