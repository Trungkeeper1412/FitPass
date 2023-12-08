package com.ks.fitpass.brand.repository;

import com.ks.fitpass.partner.register.dto.BrandStatAdmin;
import com.ks.fitpass.partner.register.dto.BrandRatingStatAdmin;
import com.ks.fitpass.brand.dto.*;
import com.ks.fitpass.brand.entity.Brand;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandRepository {

    List<Brand> getAllByStatus(int status, int page, int size, String sortPrice, String sortRating) throws DataAccessException;

    List<Brand> getAllByTopRating(int status) throws DataAccessException;

    Brand getOne(int id) throws DataAccessException;

    int countAllBrands(int status, String sortRating);

    Brand getBrandDetail(int userId);

    int updateBrandDetail(BrandOwnerProfile brandOwnerProfile);

   List<BrandAdminList> getAllBrand();

    int updateBrandMoneyPercent(int brandId, int moneyPercent);

    Integer getBrandMoneyPercent(int brandId);

    BrandDetailFeedbackStat getFeedbackOfBrandDetailStat(int brandId);

    List<BrandDetailFeedback> getFeedbackOfBrandDetail(int brandId, int page, int size, String sortRating);

    int countTotalFeedback(int brandId, String sortRating);

    int getBrandOwnerIdByDepartmentId(int departmentId);

    int createBrandWithBrandName(int userId, String brandName);

    Integer countAllBrand();

    List<BrandStatAdmin> getAdminStat();

    List<BrandRatingStatAdmin> getAdminRatingStat();

    Integer getTotalRating(int brandId);

    List<DepartmentBrandHomepageSearch> searchBrandWithPagnition(String search, int page, int size);

    int countSearchBrand(String search);
}
