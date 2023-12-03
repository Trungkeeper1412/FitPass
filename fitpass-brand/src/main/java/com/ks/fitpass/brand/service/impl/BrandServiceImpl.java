package com.ks.fitpass.brand.service.impl;
import com.ks.fitpass.brand.dto.BrandAdminList;
import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.brand.repository.BrandRepository;
import com.ks.fitpass.brand.dto.BrandDetailFeedback;
import com.ks.fitpass.brand.dto.BrandDetailFeedbackStat;
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

    @Override
    public Brand getBrandDetail(int userId) {
        return brandRepository.getBrandDetail(userId);
    }

    @Override
    public int updateBrandDetail(BrandOwnerProfile brandOwnerProfile) {
        return brandRepository.updateBrandDetail(brandOwnerProfile);
    }

   @Override
    public List<BrandAdminList> getAllBrand() {
        return brandRepository.getAllBrand();
    }

    @Override
    public int updateBrandMoneyPercent(int brandId, int moneyPercent) {
        return brandRepository.updateBrandMoneyPercent(brandId, moneyPercent);
    }

    @Override
    public int getBrandMoneyPercent(int brandId) {
        return brandRepository.getBrandMoneyPercent(brandId);
    }

    @Override
    public List<BrandDetailFeedback> getFeedbackOfBrandDetail(int brandId, int page, int size, String sortRating) {
        return brandRepository.getFeedbackOfBrandDetail(brandId, page, size, sortRating);
    }

    @Override
    public BrandDetailFeedbackStat getFeedbackOfBrandDetailStat(int brandId) {
        return brandRepository.getFeedbackOfBrandDetailStat(brandId);
    }

    @Override
    public int countTotalFeedback(int brandId, String sortRating) {
        return brandRepository.countTotalFeedback(brandId, sortRating);
    }

    @Override
    public int getBrandOwnerIdByDepartmentId(int departmentId) {
        return brandRepository.getBrandOwnerIdByDepartmentId(departmentId);
    }

    @Override
    public int createBrandWithBrandName(int userId, String brandName) {
        return brandRepository.createBrandWithBrandName(userId, brandName);
    }
}
