package com.ks.fitpass.checkInHistory.repository.impl;

import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFixed;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;
import com.ks.fitpass.checkInHistory.repository.CheckInHistoryRepository;
import com.ks.fitpass.checkInHistory.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ks.fitpass.checkInHistory.repository.IRepositoryQuery.GET_TOTAL_LIST_CHECK_IN_FIXED_BY_DEPARTMENT_ID;
import static com.ks.fitpass.checkInHistory.repository.IRepositoryQuery.GET_TOTAL_LIST_CHECK_IN_FLEXIBLE_BY_DEPARTMENT_ID;

@Repository
public class CheckInHistoryRepositoryImpl implements CheckInHistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CheckInHistoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Timestamp getCheckInTimeByOrderDetailId(int orderDetailId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_CHECK_IN_TIME_BY_ORDER_DETAIL_ID, Timestamp.class, orderDetailId);
    }

    @Override
    public Integer getCheckInHistoryIdByOrderDetailIdAndCheckInTime(int orderDetailId, Timestamp checkInTime) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_CHECK_IN_HISTORY_ID_BY_ORDER_DETAIL_ID_AND_CHECK_IN_TIME, Integer.class, orderDetailId, checkInTime);
    }

    @Override
    public int updateCheckOutTimeAndCredit(int checkInHistoryId, Timestamp checkOutTime, double totalCredit) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_CHECK_OUT_TIME_AND_TOTAL_CREDIT, checkOutTime, totalCredit, checkInHistoryId);
    }

    @Override
    public List<Integer> getListCheckInHistoryIdNeedCheckOut() {
        return jdbcTemplate.queryForList(IRepositoryQuery.GET_LIST_CHECK_IN_HISTORY_ID_NEED_CHECK_OUT, Integer.class);
    }

    @Override
    public List<CheckInHistoryFlexible> getListCheckInHistoryFlexibleByDepartmentId(int departmentId, int offset, int size) {
        List<CheckInHistoryFlexible> list = jdbcTemplate.query(IRepositoryQuery.GET_LIST_CHECK_IN_HISTORY_FLEXIBLE_BY_DEPARTMENT_ID + " LIMIT ?, ?", (rs, rowNum) -> {
            CheckInHistoryFlexible checkInHistoryFlexible = new CheckInHistoryFlexible();
            checkInHistoryFlexible.setUsername(rs.getString("username"));
            checkInHistoryFlexible.setUserImageUrl(rs.getString("image_url"));
            checkInHistoryFlexible.setPhoneNumber(rs.getString("phone_number"));
            checkInHistoryFlexible.setEmpName(rs.getString("emp_name"));
            checkInHistoryFlexible.setCheckInTime(rs.getTimestamp("checkInTime"));
            checkInHistoryFlexible.setCheckOutTime(rs.getTimestamp("checkOutTime"));
            checkInHistoryFlexible.setGymPlanName(rs.getString("name"));
            checkInHistoryFlexible.setPricePerHours(rs.getDouble("pricePerHours"));
            checkInHistoryFlexible.setTotalCredit(rs.getDouble("totalCredit"));
            String duration = "";
            if(rs.getTimestamp("checkOutTime") != null) {
                duration = calculateDuration(rs.getTimestamp("checkInTime"), rs.getTimestamp("checkOutTime"));
            }
            checkInHistoryFlexible.setDuration(duration);
            return checkInHistoryFlexible;
        }, departmentId, offset, size);
        return list;
    }

    @Override
    public List<CheckInHistoryFixed> getListCheckInHistoryFixedByDepartmentId(int departmentId, int offset, int size) {
        return jdbcTemplate.query(IRepositoryQuery.GET_LIST_CHECK_IN_HISTORY_FIXED_BY_DEPARTMENT_ID + " LIMIT ?, ?", (rs, rowNum) -> {
            CheckInHistoryFixed checkInHistoryFixed = new CheckInHistoryFixed();
            checkInHistoryFixed.setUsername(rs.getString("username"));
            checkInHistoryFixed.setUserImageUrl(rs.getString("image_url"));
            checkInHistoryFixed.setPhoneNumber(rs.getString("phone_number"));
            checkInHistoryFixed.setEmpName(rs.getString("emp_name"));
            checkInHistoryFixed.setCheckInTime(rs.getTimestamp("checkInTime"));
            checkInHistoryFixed.setGymPlanName(rs.getString("name"));
            checkInHistoryFixed.setCredit(rs.getDouble("price"));
            return checkInHistoryFixed;
        }, departmentId, offset, size);
    }

    @Override
    public List<CheckInHistoryFlexible> searchListHistoryFlexible(
            int departmentId,
            String username,
            String phoneNumber,
            String dateFilter,
            int offset,
            int size) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(departmentId);

        String sql = IRepositoryQuery.GET_LIST_CHECK_IN_HISTORY_FLEXIBLE_BY_DEPARTMENT_ID;

        // Conditional query parts for filters
        if (username != null && !username.isEmpty()) {
            sql += " AND CONCAT(ud.first_name, ' ', ud.last_name) LIKE ?";
            parameters.add("%" + username + "%");
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            sql += " AND ud.phone_number LIKE ?";
            parameters.add("%" + phoneNumber + "%");
        }

        if (dateFilter != null && !dateFilter.isEmpty()) {
            switch (dateFilter) {
                case "day":
                    sql += " AND DATE(ci.check_in_time) = CURDATE()";
                    break;
                case "week":
                    sql += " AND WEEK(ci.check_in_time) = WEEK(NOW())";
                    break;
                case "month":
                    sql += " AND MONTH(ci.check_in_time) = MONTH(NOW())";
                    break;
                case "year":
                    sql += " AND YEAR(ci.check_in_time) = YEAR(NOW())";
                    break;
            }
        }

        // Add ORDER BY clause for consistent ordering
        sql += " ORDER BY ci.check_in_time DESC";

        // Add pagination using LIMIT and OFFSET
        sql += " LIMIT ? OFFSET ?";
        parameters.add(size);
        parameters.add(offset);

        List<CheckInHistoryFlexible> list = jdbcTemplate.query(sql, parameters.toArray(), (rs, rowNum) -> {
                    CheckInHistoryFlexible checkInHistoryFlexible = new CheckInHistoryFlexible();
                    checkInHistoryFlexible.setUsername(rs.getString("username"));
                    checkInHistoryFlexible.setPhoneNumber(rs.getString("phone_number"));
                    checkInHistoryFlexible.setEmpName(rs.getString("emp_name"));
                    checkInHistoryFlexible.setCheckInTime(rs.getTimestamp("checkInTime"));
                    checkInHistoryFlexible.setCheckOutTime(rs.getTimestamp("checkOutTime"));
                    checkInHistoryFlexible.setGymPlanName(rs.getString("name"));
                    checkInHistoryFlexible.setPricePerHours(rs.getDouble("pricePerHours"));
                    checkInHistoryFlexible.setTotalCredit(rs.getDouble("totalCredit"));
                    String duration = "";
                    if (rs.getTimestamp("checkOutTime") != null) {
                        duration = calculateDuration(rs.getTimestamp("checkInTime"), rs.getTimestamp("checkOutTime"));
                    }
                    checkInHistoryFlexible.setDuration(duration);
                    return checkInHistoryFlexible;
                });
        return (!list.isEmpty()) ? list : new ArrayList<>();
    }
    public List<CheckInHistoryFixed> searchListHistoryFixed(int departmentId, String username, String phoneNumber, String dateFilter) {
        String sql = IRepositoryQuery.GET_LIST_CHECK_IN_HISTORY_FIXED_BY_DEPARTMENT_ID;

        if (username != null && !username.isEmpty()) {
            sql += " AND CONCAT(ud.first_name, ' ', ud.last_name) LIKE '%" + username + "%'";
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            sql += " AND ud.phone_number LIKE '%" + phoneNumber + "%'";
        }

        if (dateFilter != null && !dateFilter.isEmpty()) {
            if (dateFilter.equals("day")) {
                sql += " AND DATE(ci.check_in_time) = CURDATE()";
            } else if (dateFilter.equals("week")) {
                sql += " AND WEEK(ci.check_in_time) = WEEK(NOW())";
            } else if (dateFilter.equals("month")) {
                sql += " AND MONTH(ci.check_in_time) = MONTH(NOW())";
            } else if (dateFilter.equals("year")) {
                sql += " AND YEAR(ci.check_in_time) = YEAR(NOW())";
            }
        }

        List<CheckInHistoryFixed> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            CheckInHistoryFixed checkInHistoryFixed = new CheckInHistoryFixed();
            checkInHistoryFixed.setUsername(rs.getString("username"));
            checkInHistoryFixed.setPhoneNumber(rs.getString("phone_number"));
            checkInHistoryFixed.setEmpName(rs.getString("emp_name"));
            checkInHistoryFixed.setCheckInTime(rs.getTimestamp("checkInTime"));
            checkInHistoryFixed.setGymPlanName(rs.getString("name"));
            checkInHistoryFixed.setCredit(rs.getDouble("price"));
            return checkInHistoryFixed;
        }, departmentId);

        if(list.isEmpty()) {
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public Integer getTotalListCheckInHistoryFlexibleByDepartmentId(int departmentId) {
        return jdbcTemplate.queryForObject(GET_TOTAL_LIST_CHECK_IN_FLEXIBLE_BY_DEPARTMENT_ID, Integer.class,departmentId);
    }

    @Override
    public Integer getTotalListCheckInHistoryFixedByDepartmentId(int departmentId) {
        return jdbcTemplate.queryForObject(GET_TOTAL_LIST_CHECK_IN_FIXED_BY_DEPARTMENT_ID, Integer.class,departmentId);
    }

    @Override
    public Integer countSearchListHistoryFlexible(int departmentId, String username, String phoneNumber, String dateFilter) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(departmentId);

        // Start with the base query from the constant
        String sql = IRepositoryQuery.GET_TOTAL_LIST_CHECK_IN_FLEXIBLE_BY_DEPARTMENT_ID;

        // Modify the existing query to add additional conditions
        if (username != null && !username.isEmpty()) {
            sql += " AND CONCAT(ud.first_name, ' ', ud.last_name) LIKE ?";
            parameters.add("%" + username + "%");
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            sql += " AND ud.phone_number LIKE ?";
            parameters.add("%" + phoneNumber + "%");
        }

        if (dateFilter != null && !dateFilter.isEmpty()) {
            if (dateFilter.equals("day")) {
                sql += " AND DATE(ci.check_in_time) = CURDATE()";
            } else if (dateFilter.equals("week")) {
                sql += " AND WEEK(ci.check_in_time) = WEEK(NOW())";
            } else if (dateFilter.equals("month")) {
                sql += " AND MONTH(ci.check_in_time) = MONTH(NOW())";
            } else if (dateFilter.equals("year")) {
                sql += " AND YEAR(ci.check_in_time) = YEAR(NOW())";
            }
        }

        // Execute the query to get the count
        return jdbcTemplate.queryForObject(sql, Integer.class, parameters.toArray());
    }

    @Override
    public Integer countSearchListHistoryFixed(int departmentId, String username, String phoneNumber, String dateFilter) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(departmentId);

        // Start with the base query from the constant
        String sql = IRepositoryQuery.GET_TOTAL_LIST_CHECK_IN_FIXED_BY_DEPARTMENT_ID;

        // Modify the existing query to add additional conditions
        if (username != null && !username.isEmpty()) {
            sql += " AND CONCAT(ud.first_name, ' ', ud.last_name) LIKE ?";
            parameters.add("%" + username + "%");
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            sql += " AND ud.phone_number LIKE ?";
            parameters.add("%" + phoneNumber + "%");
        }

        if (dateFilter != null && !dateFilter.isEmpty()) {
            if (dateFilter.equals("day")) {
                sql += " AND DATE(ci.check_in_time) = CURDATE()";
            } else if (dateFilter.equals("week")) {
                sql += " AND WEEK(ci.check_in_time) = WEEK(NOW())";
            } else if (dateFilter.equals("month")) {
                sql += " AND MONTH(ci.check_in_time) = MONTH(NOW())";
            } else if (dateFilter.equals("year")) {
                sql += " AND YEAR(ci.check_in_time) = YEAR(NOW())";
            }
        }

        // Execute the query to get the count
        return jdbcTemplate.queryForObject(sql, Integer.class, parameters.toArray());
    }

    private String calculateDuration(Timestamp checkInTime, Timestamp checkOutTime) {
        long durationInMillis = checkOutTime.getTime() - checkInTime.getTime();

        long hours = durationInMillis / (60 * 60 * 1000);
        long minutes = (durationInMillis % (60 * 60 * 1000)) / (60 * 1000);

        // Định dạng chuỗi "HH h mm p"
        String formattedDuration = String.format("%02d giờ %02d phút", hours, minutes);

        return formattedDuration;
    }

}
