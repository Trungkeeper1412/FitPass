package com.ks.fitpass.order.repository.impl;

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
}
