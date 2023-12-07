package com.ks.fitpass.brand.service.impl;
import com.ks.fitpass.partner.register.dto.BrandStatAdmin;
import com.ks.fitpass.partner.register.dto.BrandRatingStatAdmin;
import com.ks.fitpass.brand.dto.*;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.brand.repository.BrandRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return brandRepository.getOne(id);
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

    @Override
    public int countAllBrand() {
        Integer count = brandRepository.countAllBrand();
        return (count != null) ? count : 0;
    }

    @Override
    public List<BrandStatAdmin> getAdminStat() {
        List<BrandStatAdmin> stats = brandRepository.getAdminStat();
        return (stats != null) ? stats : new ArrayList<>();
    }

    @Override
    public List<BrandRatingStatAdmin> getAdminRatingStat() {
        List<BrandRatingStatAdmin> list = brandRepository.getAdminRatingStat();
        return (list != null) ? list : new ArrayList<>();
    }

    @Override
    public int getTotalRating(int brandId) {
        Integer count = brandRepository.getTotalRating(brandId);
        return (count != null) ? count : 0;
    }

    @Override
    public List<DepartmentBrandHomepageSearch> searchBrandWithPagnition(String search, int page, int size) {
        return brandRepository.searchBrandWithPagnition(search, page, size);
    }

    @Override
    public int countSearchBrand(String search) {
        return brandRepository.countSearchBrand(search);
    }
}
