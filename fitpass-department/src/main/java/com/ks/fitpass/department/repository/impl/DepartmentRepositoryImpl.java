package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.dto.DepartmentListByBrandDTO;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentStatus;
import com.ks.fitpass.department.entity.UserFeedback;
import com.ks.fitpass.department.mapper.DepartmentHomePageMapper;
import com.ks.fitpass.department.mapper.DepartmentMapper;
import com.ks.fitpass.department.mapper.UserFeedbackMapper;
import com.ks.fitpass.department.repository.DepartmentRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DepartmentRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Department getByUserId(int userId) throws DataAccessException {
        return jdbcTemplate.queryForObject(GET_DEPARTMENT_BY_USER, new DepartmentMapper(), userId);
    }

    @Override
//    public List<Department> getAllByStatus(int status, int page, int size, String city, String sortPrice, String sortRating,
//                                           double userLatitude, double userLongitude, String belowDistance) throws DataAccessException {
//        int offset = (page - 1) * size;
//        String sql = GET_ALL_DEPARTMENT_BY_STATUS;
//
//        if(userLatitude != 0 && userLongitude != 0) {
//            sql += " HAVING distance <= " + belowDistance + "\n";
//        }
//
//        if(sortRating != null && !sortRating.isEmpty()) {
//            sql +=  " AND d.rating >=  " + sortRating + " \n";
//        }
//
//        if(city != null && !city.isEmpty() && !city.equalsIgnoreCase("all")) {
//            sql +=  " AND d.city =  '"+city+"'\n";
//        }
//
//        if(sortPrice != null && !sortPrice.isEmpty()) {
//            if(sortPrice.equals("lowToHigh")) {
//                sql += " ORDER BY COALESCE((SELECT MAX(gp.price) FROM gym_plan gp WHERE gp.gym_department_id = d.gym_department_id), 0) asc, \n" +
//                        " COALESCE((SELECT MIN(gp.price) FROM gym_plan gp WHERE gp.gym_department_id = d.gym_department_id), 0) asc \n";
//            } else {
//                sql += " ORDER BY COALESCE((SELECT MAX(gp.price) FROM gym_plan gp WHERE gp.gym_department_id = d.gym_department_id), 0) desc, \n" +
//                        " COALESCE((SELECT MIN(gp.price) FROM gym_plan gp WHERE gp.gym_department_id = d.gym_department_id), 0) desc \n";
//            }
//        }
//
//        if(userLatitude == 0 && userLongitude == 0) {
//            if(sortPrice == null || sortPrice.isEmpty()) {
//                sql += "ORDER BY d.rating DESC";
//            } else {
//                sql += ", d.rating DESC";
//            }
//        }
//
//        sql += " LIMIT " + size + " OFFSET " + offset;
//
//        return jdbcTemplate.query(sql, new DepartmentHomePageMapper(), userLatitude, userLongitude, userLatitude, status);
//    }
    public List<Department> getAllByStatus(int status, int page, int size, String city,
                                           String sortPrice, String sortRating, double userLatitude, double userLongitude, String belowDistance)
            throws DataAccessException {
        int offset = (page - 1) * size;
        String sql = GET_ALL_DEPARTMENT_BY_STATUS;


        List<Object> parameters = new ArrayList<>();
        parameters.add(userLatitude);
        parameters.add(userLongitude);
        parameters.add(userLatitude);
        parameters.add(status);

        if(userLatitude != 0 && userLongitude != 0) {
            sql += " HAVING distance <= ? \n";
            parameters.add(belowDistance);
        }

        if(sortRating != null && !sortRating.isEmpty()) {
            sql +=  " AND d.rating >=  ? \n";
            parameters.add(sortRating);
        }

        if(city != null && !city.isEmpty() && !city.equalsIgnoreCase("all")) {
            sql +=  " AND d.city =  ? \n";
            parameters.add(city);
        }

        if(userLatitude != 0 && userLongitude != 0) {
            sql += " ORDER BY distance ASC ";
            if(sortPrice != null && !sortPrice.isEmpty()) {
                if(sortPrice.equals("lowToHigh")) {
                    sql += " , max_price asc, \n" +
                            " min_price asc \n";
                } else {
                    sql += " , max_price desc, \n" +
                            " min_price desc \n";
                }
            }
        } else {
            if(sortPrice != null && !sortPrice.isEmpty()) {
                if(sortPrice.equals("lowToHigh")) {
                    sql += " ORDER BY max_price asc, \n" +
                            " min_price asc \n";
                } else {
                    sql += " ORDER BY max_price desc, \n" +
                            " min_price desc \n";
                }
            }
        }


        if(userLatitude == 0 && userLongitude == 0) {
            if(sortPrice == null || sortPrice.isEmpty()) {
                sql += "ORDER BY d.rating DESC";
            } else {
                sql += ", d.rating DESC";
            }
        }



        sql += " LIMIT ? OFFSET ?";
        parameters.add(size);
        parameters.add(offset);

        return jdbcTemplate.query(sql, parameters.toArray(), new DepartmentHomePageMapper());
    }
    @Override
    public List<Department> getAllByTopRating(int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_ORDER_BY_RATING, new DepartmentMapper(), status);
    }

    @Override
    public List<Department> getAllByBrandId(int brandId, int status) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_BY_BRAND_ID, new DepartmentMapper(), brandId, status);
    }

    @Override
    public Department getOne(int id) throws DataAccessException {
    return jdbcTemplate.queryForObject(GET_DEPARTMENT_BY_ID, new DepartmentMapper(), id);
    }
    @Override
    public boolean update(Department department) throws DataAccessException {
        return jdbcTemplate.update(
                UPDATE_DEPARTMENT,
                department.getBrandId(),
                department.getDepartmentName(),
                department.getDepartmentAddress(),
                department.getDepartmentContactNumber(),
                department.getDepartmentLogoUrl(),
                department.getDepartmentWallpaperUrl(),
                department.getDepartmentDescription(),
                department.getDepartmentStatus().getDepartmentStatusCd(),
                department.getDepartmentId()
        ) > 0;
    }

    @Override
    public List<Department> findByRatingBetween(double from, double to) throws DataAccessException {
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_ORDER_BY_RATING, new DepartmentMapper(), from, to);
    }

    @Override
    public List<UserFeedback> getDepartmentFeedback(int departmentId) {
        return jdbcTemplate.query(GET_DEPARTMENT_FEEDBACK, new UserFeedbackMapper(), departmentId);
    }

    @Override
    public List<UserFeedback> getDepartmentFeedbackPagnition(int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        return jdbcTemplate.query(GET_DEPARTMENT_FEEDBACK_PAGNITION, new UserFeedbackMapper(), departmentId, size, offset);
    }

    @Override
    public List<Department> getDepartmentByBrandID(int status, int brandID) throws DataAccessException {
        return jdbcTemplate.query(GET_DEPARTMENT_BY_BRAND_ID, new DepartmentMapper(), status,brandID);
    }

    @Override
    public List<DepartmentListByBrandDTO> getAllDepartmentListOfBrand(int brandId) {
        return jdbcTemplate.query(GET_ALL_DEPARTMENT_LIST_OF_BRAND, (rs, rowNum) -> {
            DepartmentListByBrandDTO dto = new DepartmentListByBrandDTO();
            dto.setDepartmentId(rs.getInt("gym_department_id"));
            dto.setDepartmentName(rs.getString("name"));
            dto.setUserName(rs.getString("user_name"));
            dto.setDepartmentContactNumber(rs.getString("contact_number"));
            dto.setDepartmentThumbnailUrl(rs.getString("thumbnail_url"));
            dto.setDepartmentStatus(DepartmentStatus.builder().
                    departmentStatusCd(rs.getInt("gym_department_status_key")).
                    departmentStatusName(rs.getString("gym_department_status_name")).build());
            return dto;
        }, brandId);
    }

    @Override
    public int updateDepartmentStatus(int status, int departmentId) {
        return jdbcTemplate.update(UPDATE_DEPARTMENT_STATUS, status, departmentId);
    }

    @Override
    public int createDepartmentWithBrandId(int brandId, String name) {
        return jdbcTemplate.update(CREATE_DEPARTMENT_WITH_BRAND_ID, name, brandId, 2);
    }

    @Override
    public int countAllDepartment(int status , String city, String sortPrice, String sortRating, double userLatitude, double userLongitude, String belowDistance) {
        String sql = "SELECT COUNT(*) -- Đếm số dòng\n" +
                "        FROM gym_department subd\n" +
                "        LEFT JOIN mst_kbn subkbn_department_status\n" +
                "            ON subd.gym_department_status_key = subkbn_department_status.mst_kbn_key\n" +
                "            AND subkbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'\n" +
                "        WHERE subd.gym_department_status_key = ?\n";
        if(userLatitude != 0 && userLongitude != 0) {
            sql += "        AND (\n" +
                    "            6371 * acos(\n" +
                    "                cos(radians(?)) * cos(radians(subd.latitude)) *\n" +
                    "                cos(radians(subd.longitude) - radians(?)) +\n" +
                    "                sin(radians(?)) * sin(radians(subd.latitude))\n" +
                    "            )\n" +
                    "        ) <= "+belowDistance+" \n";
        }

        if(sortRating != null && !sortRating.isEmpty()) {
            sql +=  " AND subd.rating >=  " + sortRating + " \n";
        }

        if(city != null && !city.isEmpty() && !city.equalsIgnoreCase("all")) {
            sql +=  " AND subd.city =  '"+city+"'\n";
        }

        if(userLatitude != 0 && userLongitude != 0) {
            return jdbcTemplate.queryForObject(sql, Integer.class,  status, userLatitude, userLongitude, userLatitude);
        }
        return jdbcTemplate.queryForObject(sql, Integer.class,  status);
    }
}
