package com.ks.fitpass.brand.service;

import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.dto.BrandDetailFeedback;
import com.ks.fitpass.brand.dto.BrandDetailFeedbackStat;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BrandService {

    List<Brand> getAllByStatus(int status, int page, int size, String sortPrice, String sortRating) throws DataAccessException;

    List<Brand> getAllByTopRating(int status) throws DataAccessException;

    Brand getOne(int id) throws DataAccessException;

    int countAllBrands(int status, String sortRating);

    Brand getBrandDetail(int userId);

    int updateBrandDetail(BrandOwnerProfile brandOwnerProfile);

    List<BrandDetailFeedback> getFeedbackOfBrandDetail(int brandId, int page, int size, String sortRating);

    BrandDetailFeedbackStat getFeedbackOfBrandDetailStat(int brandId);
    int countTotalFeedback(int brandId, String sortRating);
    int getBrandOwnerIdByDepartmentId(int departmentId);
}
