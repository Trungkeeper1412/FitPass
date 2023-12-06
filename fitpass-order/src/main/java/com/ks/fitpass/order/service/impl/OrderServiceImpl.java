package com.ks.fitpass.order.service.impl;

import com.ks.fitpass.order.entity.Order;
import com.ks.fitpass.order.repository.OrderRepository;
import com.ks.fitpass.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public int insertOrder(Order order) {
        return orderRepository.insertOrder(order);
    }

    @Override
    public int getLastOrderInsertId() {
        return orderRepository.getLastOrderInsertId();
    }

    @Override
    public int getNumberOfOrder(int brandId) {
        return orderRepository.getNumberOfOrder(brandId);
    }

    @Override
    public int getTotalRevenue(int brandId) {
        return orderRepository.getTotalRevenue(brandId);
    }
}
