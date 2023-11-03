package com.ks.fitpass.employee.repository.impl;

import com.ks.fitpass.employee.dto.CheckInFixedDTO;
import com.ks.fitpass.employee.dto.CheckInFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckOutFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckedInFixedDTO;
import com.ks.fitpass.employee.repository.EmployeeRepository;
import com.ks.fitpass.employee.repository.IRepositoryQuery;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CheckInFlexibleDTO> getListNeedCheckInFlexibleByDepartmentId(int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_LIST_NEED_CHECK_IN_FLEXIBLE_BY_DEPARTMENT_ID, (rs, rowNum) -> {
            CheckInFlexibleDTO dto = new CheckInFlexibleDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPricePerHours(rs.getDouble("price_per_hours"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId);
    }

    @Override
    public List<CheckOutFlexibleDTO> getListNeedCheckOutFlexibleByDepartmentId(int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_LIST_NEED_CHECK_OUT_FLEXIBLE_BY_DEPARTMENT_ID, (rs, rowNum) -> {
            CheckOutFlexibleDTO dto = new CheckOutFlexibleDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPricePerHours(rs.getDouble("price_per_hours"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId);
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckInByUsername(String username, int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.SEARCH_LIST_CHECK_IN_BY_USERNAME, (rs, rowNum) -> {
            CheckInFlexibleDTO dto = new CheckInFlexibleDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPricePerHours(rs.getDouble("price_per_hours"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId, "%" + username + "%");
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckInByPhoneNumber(String phoneNumber, int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.SEARCH_LIST_CHECK_IN_BY_PHONE, (rs, rowNum) -> {
            CheckInFlexibleDTO dto = new CheckInFlexibleDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPricePerHours(rs.getDouble("price_per_hours"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId, "%" + phoneNumber + "%");
    }

    @Override
    public int insertToCheckInHistory(int orderDetailId, int statusKey, Timestamp checkInTime, Timestamp checkOutTime, double totalCredit, int empCheckinId) {
        return jdbcTemplate.update(IRepositoryQuery.INSERT_CHECK_IN_HISTORY, orderDetailId, statusKey, checkInTime, checkOutTime, totalCredit, empCheckinId);
    }

    @Override
    public UserReceiveMessageDTO getUserReceiveMessage(int orderDetailId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_USER_RECEIVE, (rs, rowNum) -> {
            UserReceiveMessageDTO userReceiveMessageDTO = new UserReceiveMessageDTO();
            userReceiveMessageDTO.setUserId(rs.getInt("user_id"));
            userReceiveMessageDTO.setGymDepartmentId(rs.getInt("gym_department_id"));
            return userReceiveMessageDTO;
        }, orderDetailId);
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckOutByUsername(String username, int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.SEARCH_LIST_CHECK_OUT_BY_USERNAME, (rs, rowNum) -> {
            CheckInFlexibleDTO dto = new CheckInFlexibleDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPricePerHours(rs.getDouble("price_per_hours"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId, "%" + username + "%");
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckOutByPhoneNumber(String phoneNumber, int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.SEARCH_LIST_CHECK_OUT_BY_PHONE, (rs, rowNum) -> {
            CheckInFlexibleDTO dto = new CheckInFlexibleDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPricePerHours(rs.getDouble("price_per_hours"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId, "%" + phoneNumber + "%");
    }

    @Override
    public List<CheckInFixedDTO> getListNeedCheckInFixedByDepartmentId(int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_LIST_NEED_CHECK_IN_FIXED_BY_DEPARTMENT_ID, (rs, rowNum) -> {
            CheckInFixedDTO dto = new CheckInFixedDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setDuration(rs.getInt("duration"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId);
    }

    @Override
    public List<CheckedInFixedDTO> getListCheckedInFixedByDepartmentId(int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_LIST_CHECKED_IN_FIXED_BY_DEPARTMENT_ID, (rs, rowNum) -> {
            CheckedInFixedDTO dto = new CheckedInFixedDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setDuration(rs.getInt("duration"));
            dto.setPrice(rs.getDouble("price"));
            dto.setCheckInTime(rs.getTimestamp("check_in_time"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId);
    }

    @Override
    public List<CheckInFixedDTO> searchListCheckInFixedByUsername(String username, int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.SEARCH_LIST_FIXED_CHECK_IN_BY_USERNAME, (rs, rowNum) -> {
            CheckInFixedDTO dto = new CheckInFixedDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId, "%" + username + "%");
    }

    @Override
    public List<CheckInFixedDTO> searchListCheckInFixedByPhoneNumber(String phoneNumber, int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.SEARCH_LIST_FIXED_CHECK_IN_BY_PHONE, (rs, rowNum) -> {
            CheckInFixedDTO dto = new CheckInFixedDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPrice(rs.getDouble("price"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId, "%" + phoneNumber + "%");
    }

    @Override
    public List<CheckedInFixedDTO> searchListCheckedInFixedByUsername(String username, int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.SEARCH_LIST_FIXED_CHECKED_IN_BY_USERNAME, (rs, rowNum) -> {
            CheckedInFixedDTO dto = new CheckedInFixedDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPrice(rs.getDouble("price"));
            dto.setDuration(rs.getInt("duration"));
            dto.setCheckInTime(rs.getTimestamp("check_in_time"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId, "%" + username + "%");
    }

    @Override
    public List<CheckedInFixedDTO> searchListCheckedInFixedByPhoneNumber(String phoneNumber, int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.SEARCH_LIST_FIXED_CHECKED_IN_BY_PHONE, (rs, rowNum) -> {
            CheckedInFixedDTO dto = new CheckedInFixedDTO();
            dto.setOrderDetailId(rs.getInt("order_detail_id"));
            dto.setUsername(rs.getString("user_name"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setPrice(rs.getDouble("price"));
            dto.setDuration(rs.getInt("duration"));
            dto.setCheckInTime(rs.getTimestamp("check_in_time"));
            dto.setImageUrl(rs.getString("image_url"));
            return dto;
        }, departmentId, "%" + phoneNumber + "%");
    }
}
