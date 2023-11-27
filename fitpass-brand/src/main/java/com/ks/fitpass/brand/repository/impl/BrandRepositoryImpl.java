package com.ks.fitpass.brand.repository.impl;

import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.mapper.BrandWithTotalOrderMapper;
import com.ks.fitpass.brand.repository.IRepositoryQuery;
import com.ks.fitpass.brand.repository.BrandRepository;
import com.ks.fitpass.brand.mapper.BrandMapper;
import com.ks.fitpass.brand.dto.BrandDetailFeedback;
import com.ks.fitpass.brand.dto.BrandDetailFeedbackStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepositoryImpl implements BrandRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BrandRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Brand> getAllByStatus(int status, int page, int size, String sortPrice, String sortRating) throws DataAccessException {
        int offset = (page - 1) * size;
        String sql = GET_ALL_BRAND_BY_STATUS;

        if(sortRating != null && !sortRating.isEmpty()) {
            sql +=  " AND b.rating >  " + sortRating + " \n";
        }

        if(sortPrice != null && !sortPrice.isEmpty()) {
            if(sortPrice.equals("lowToHigh")) {
                sql += " ORDER BY (SELECT COUNT(*)\n" +
                        "                      FROM order_plan_detail opd\n" +
                        "                      JOIN gym_department gd ON opd.gym_department_id = gd.gym_department_id\n" +
                        "                      WHERE gd.brand_id = b.brand_id) asc \n";
            } else {
                sql += " ORDER BY (SELECT COUNT(*)\n" +
                        "                      FROM order_plan_detail opd\n" +
                        "                      JOIN gym_department gd ON opd.gym_department_id = gd.gym_department_id\n" +
                        "                      WHERE gd.brand_id = b.brand_id) desc \n";
            }
        }

        sql += "LIMIT "+size+" OFFSET " + offset;

        return jdbcTemplate.query(sql, new BrandWithTotalOrderMapper(), status);
    }

    @Override
    public List<Brand> getAllByTopRating(int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_BRAND_ORDER_BY_RATING, new BrandMapper(), status);
    }

    @Override
    public Brand getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(GET_BRAND_BY_ID, new BrandMapper(), id);
    }

    @Override
    public int countAllBrands(int status, String sortRating) {
        String sql = COUNT_ALL_BRAND_BY_STATUS;

        if(sortRating != null && !sortRating.isEmpty()) {
            sql +=  " AND b.rating >  " + sortRating + " \n";
        }

        return jdbcTemplate.queryForObject(sql, Integer.class, status);
    }

    @Override
    public Brand getBrandDetail(int userId) {
        return jdbcTemplate.queryForObject(GET_BRAND_DETAIL_BY_USER_ID, new BrandMapper(), userId);
    }

    @Override
    public int updateBrandDetail(BrandOwnerProfile brandOwnerProfile) {
        return jdbcTemplate.update(UPDATE_BRAND_DETAIL_BY_BRAND_ID, brandOwnerProfile.getBrandName(), brandOwnerProfile.getBrandLogoUrl(),
                brandOwnerProfile.getBrandWallpaperUrl(),brandOwnerProfile.getBrandThumbnailUrl(), brandOwnerProfile.getBrandDescription(),
                brandOwnerProfile.getBrandContactNumber(), brandOwnerProfile.getBrandEmail(), brandOwnerProfile.getBrandStatus().getBrandStatusCd(),
                brandOwnerProfile.getBrandId());
    }

    @Override
    public BrandDetailFeedbackStat getFeedbackOfBrandDetailStat(int brandId) {
        return jdbcTemplate.queryForObject(GET_FEEDBACK_OF_BRAND_STAT, (rs, rowNum) -> {
            BrandDetailFeedbackStat dto = new BrandDetailFeedbackStat();
            dto.setTotalFeedback(rs.getInt("totalFeedback"));
            dto.setFiveStar(rs.getInt("fiveStarFeedback"));
            dto.setFourStar(rs.getInt("fourStarFeedback"));
            dto.setThreeStar(rs.getInt("threeStarFeedback"));
            dto.setTwoStar(rs.getInt("twoStarFeedback"));
            dto.setOneStar(rs.getInt("oneStarFeedback"));
            return dto;
        }, brandId);
    }

    @Override
    public List<BrandDetailFeedback> getFeedbackOfBrandDetail(int brandId, int page, int size, String sortRating) {
        String sql = GET_ALL_FEEDBACK_OF_BRAND;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("brandId", brandId);

        if (sortRating != null && !sortRating.isEmpty()) {
            int sortRatingInt = Integer.parseInt(sortRating);
            sql += " AND uf.rating >= :minRating AND uf.rating < :maxRating";
            params.addValue("minRating", sortRatingInt);
            params.addValue("maxRating", sortRatingInt + 1);
        }

        int offset = (page - 1) * size;
        sql += " LIMIT :size OFFSET :offset";
        params.addValue("size", size);
        params.addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            BrandDetailFeedback dto = new BrandDetailFeedback();
            dto.setFeedbackId(rs.getInt("feedback_id"));
            dto.setUserId(rs.getInt("user_id"));
            dto.setFirstName(rs.getString("first_name"));
            dto.setLastName(rs.getString("last_name"));
            dto.setImageUrl(rs.getString("image_url"));
            dto.setRating(rs.getInt("rating"));
            dto.setComment(rs.getString("comments"));
            dto.setFeedbackTime(rs.getTimestamp("feedback_time"));
            return dto;
        });
    }

    @Override
    public int countTotalFeedback(int brandId, String sortRating) {
        String sql = COUNT_TOTAL_FEEDBACK_OF_BRAND;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("brandId", brandId);

        if (sortRating != null && !sortRating.isEmpty()) {
            int sortRatingInt = Integer.parseInt(sortRating);
            sql += " AND uf.rating >= :minRating AND uf.rating < :maxRating";
            params.addValue("minRating", sortRatingInt);
            params.addValue("maxRating", sortRatingInt + 1);
        }
        return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    @Override
    public int getBrandOwnerIdByDepartmentId(int departmentId) {
        return jdbcTemplate.queryForObject(GET_BRAND_OWNER_ID_BY_DEPARTMENT_ID, Integer.class, departmentId);
    }
}
