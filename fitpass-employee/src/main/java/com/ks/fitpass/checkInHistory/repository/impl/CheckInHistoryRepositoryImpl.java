package com.ks.fitpass.checkInHistory.repository.impl;

import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;
import com.ks.fitpass.checkInHistory.repository.CheckInHistoryRepository;
import com.ks.fitpass.checkInHistory.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CheckInHistoryRepositoryImpl implements CheckInHistoryRepository {

    private JdbcTemplate jdbcTemplate;

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
    public List<CheckInHistoryFlexible> getListCheckInHistoryFlexibleByDepartmentId(int departmentId) {
        List<CheckInHistoryFlexible> list = jdbcTemplate.query(IRepositoryQuery.GET_LIST_CHECK_IN_HISTORY_FLEXIBLE_BY_DEPARTMENT_ID, (rs, rowNum) -> {
            CheckInHistoryFlexible checkInHistoryFlexible = new CheckInHistoryFlexible();
            checkInHistoryFlexible.setUsername(rs.getString("username"));
            checkInHistoryFlexible.setPhoneNumber(rs.getString("phone_number"));
            checkInHistoryFlexible.setEmpName(rs.getString("emp_name"));
            checkInHistoryFlexible.setCheckInTime(rs.getTimestamp("checkInTime"));
            checkInHistoryFlexible.setCheckOutTime(rs.getTimestamp("checkOutTime"));
            checkInHistoryFlexible.setPricePerHours(rs.getDouble("pricePerHours"));
            checkInHistoryFlexible.setTotalCredit(rs.getDouble("totalCredit"));
            return checkInHistoryFlexible;
        }, departmentId);
        return list;
    }


}
