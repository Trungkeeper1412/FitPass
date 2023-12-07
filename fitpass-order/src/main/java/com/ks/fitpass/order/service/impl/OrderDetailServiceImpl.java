package com.ks.fitpass.order.service.impl;

import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
import com.ks.fitpass.order.dto.OrderDetailStatAdmin;
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

    @Override
    public int updateOrderDetailsUseStatus(int orderDetailId, String statusUse) {
        return orderDetailRepository.updateOrderDetailsUseStatus(orderDetailId, statusUse);
    }

    @Override
    public Double getPricePerHoursByOrderDetailId(int orderDetailId) {
        return orderDetailRepository.getPricePerHoursByOrderDetailId(orderDetailId);
    }

    @Override
    public String getGymDepartmentNameByOrderDetailId(int orderDetailId) {
        return orderDetailRepository.getGymDepartmentNameByOrderDetailId(orderDetailId);
    }

    @Override
    public OrderDetailConfirmCheckOut getByOrderDetailId(int orderDetailId) {
        return orderDetailRepository.getByOrderDetailId(orderDetailId);
    }

    @Override
    public String getUserNameByOrderDetailId(int orderDetailId) {
        return orderDetailRepository.getUserNameByOrderDetailId(orderDetailId);
    }

    @Override
    public Boolean isFixedGymPlan(int orderDetailId) {
        return orderDetailRepository.isFixedGymPlan(orderDetailId);
    }

    @Override
    public int decreaseDuration(int orderDetailId) {
        return orderDetailRepository.decreaseDuration(orderDetailId);
    }

    @Override
    public List<Integer> getListOrderDetailExpired() {
        return orderDetailRepository.getListOrderDetailExpired();
    }

    @Override
    public int[] updateOrderDetailExpiredStatus(List<Integer> listId) {
        return orderDetailRepository.updateOrderDetailExpiredStatus(listId);
    }

    @Override
    public int getLatestOrderDetailId() {
        return orderDetailRepository.getLatestOrderDetailId();
    }

    @Override
    public OrderDetailStatAdmin getAdminStat() {
        OrderDetailStatAdmin orderDetailStatAdmin = orderDetailRepository.getAdminStat();
        return (orderDetailStatAdmin != null) ? orderDetailStatAdmin : new OrderDetailStatAdmin();
    }

    @Override
    public int getTotalBuyByDepartmentId(int departmentId) {
        Integer count = orderDetailRepository.getTotalBuyByDepartmentId(departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public double getTotalRevenueByDepartmentId(int departmentId) {
        Double count = orderDetailRepository.getTotalRevenueByDepartmentId(departmentId);
        return (count != null) ? count : 0.0;
    }
}
