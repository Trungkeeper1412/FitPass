package com.ks.fitpass.order.repository.impl;

import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
import com.ks.fitpass.order.dto.OrderDetailDTO;
import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.mapper.OrderDetailMapper;
import com.ks.fitpass.order.mapper.OrderDetailWithDeparmentNameMapper;
import com.ks.fitpass.order.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderDetailRepositoryImpl implements com.ks.fitpass.order.repository.OrderDetailRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDetailRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertOrderDetail(OrderDetails orderDetail) {
        return jdbcTemplate.update(IRepositoryQuery.INSERT_ORDER_PLAN_DETAIL, orderDetail.getOrderId(), orderDetail.getName(),
                orderDetail.getGymPlanDepartmentId(), orderDetail.getQuantity(),
                orderDetail.getPricePerHours(), orderDetail.getPrice(),
                orderDetail.getDuration(), orderDetail.getPlanBeforeActiveValidity(),
                orderDetail.getPlanAfterActiveValidity(), orderDetail.getItemStatusKey(), orderDetail.getDescription());
    }

    @Override
    public List<OrderDetails> getAllOrderItemByUserId(int userId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_ALL_ORDER_ITEM_BY_USER_ID, new OrderDetailWithDeparmentNameMapper(), userId);
    }

    @Override
    public List<OrderDetails> getOrderDetailByStatusAndUserId(int status, int userId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_ORDER_ITEM_BY_STATUS_AND_USER_ID, new OrderDetailWithDeparmentNameMapper(), userId, status);
    }

    @Override
    public int updateOrderDetailItemStatus(Timestamp planActiveTime, int status, Timestamp planExpiredTime, int orderDetailId) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS, planActiveTime, status, planExpiredTime, orderDetailId);
    }

    @Override
    public int updateOrderDetailsUseStatus(int orderDetailId, String statusUse) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS_USE, statusUse, orderDetailId);
    }

    @Override
    public Double getPricePerHoursByOrderDetailId(int orderDetailId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_PRICE_PER_HOURS_BY_ORDER_DETAIL_ID, Double.class, orderDetailId);
    }

    @Override
    public String getGymDepartmentNameByOrderDetailId(int orderDetailId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_DEPARTMENT_NAME_BY_ORDER_DETAIL_ID, String.class, orderDetailId);
    }

    @Override
    public OrderDetailConfirmCheckOut getByOrderDetailId(int orderDetailId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_ORDER_DETAIL_CONFIRM_CHECKOUT_BY_DETAIL_ID, (rs, rowNum) -> {
            OrderDetailConfirmCheckOut orderDetailConfirmCheckOut = new OrderDetailConfirmCheckOut();
            orderDetailConfirmCheckOut.setDepartmentName(rs.getString("gym_department_name"));
            orderDetailConfirmCheckOut.setGymPlanName(rs.getString("name"));
            orderDetailConfirmCheckOut.setPricePerHours(rs.getDouble("price_per_hours"));
            return  orderDetailConfirmCheckOut;
        }, orderDetailId);
    }

    @Override
    public String getUserNameByOrderDetailId(int orderDetailId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_USER_NAME_BY_ORDER_DETAIL_ID, String.class, orderDetailId);
    }

    @Override
    public int updateAllFixedToCheckIn() {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_ALL_FIXED_TO_CHECK_IN);
    }

    @Override
    public Boolean isFixedGymPlan(int orderDetailId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.IS_FIXED_GYM_PLAN, Boolean.class, orderDetailId);
    }

    @Override
    public int decreaseDuration(int orderDetailId) {
        return jdbcTemplate.update(IRepositoryQuery.INSERT_ORDER_PLAN_DETAIL, orderDetailId);
    }
}
