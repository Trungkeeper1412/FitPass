package com.ks.fitpass.brand.repository;

import com.ks.fitpass.brand.dto.BrandAdminList;
import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.dto.BrandDetailFeedback;
import com.ks.fitpass.brand.dto.BrandDetailFeedbackStat;
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

    int getBrandMoneyPercent(int brandId);

    BrandDetailFeedbackStat getFeedbackOfBrandDetailStat(int brandId);

    List<BrandDetailFeedback> getFeedbackOfBrandDetail(int brandId, int page, int size, String sortRating);

    int countTotalFeedback(int brandId, String sortRating);

    int getBrandOwnerIdByDepartmentId(int departmentId);

    int createBrandWithBrandName(int userId, String brandName);
}
