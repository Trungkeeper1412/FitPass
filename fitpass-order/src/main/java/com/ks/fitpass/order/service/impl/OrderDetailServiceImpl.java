package com.ks.fitpass.order.service.impl;

import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.repository.OrderDetailRepository;
import com.ks.fitpass.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public int insertOrderDetail(OrderDetails orderDetails) {
        return orderDetailRepository.insertOrderDetail(orderDetails);
    }

    @Override
    public List<OrderDetails> getAllOrderItemByUserId(int userId) {
        return orderDetailRepository.getAllOrderItemByUserId(userId);
    }

    @Override
    public List<OrderDetails> getOrderDetailByStatusAndUserId(int status, int userId) {
        return orderDetailRepository.getOrderDetailByStatusAndUserId(status, userId);
    }

    @Override
    public int updateOrderDetailItemStatus(Timestamp planActiveTime, int status, Timestamp planExpiredTime, int orderDetailId) {
        return orderDetailRepository.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);
    }
}
