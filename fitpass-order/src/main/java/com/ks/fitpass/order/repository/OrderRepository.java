package com.ks.fitpass.order.repository;

import com.ks.fitpass.order.entity.Order;

public interface OrderRepository {
    int insertOrder(Order order);

    int getLastOrderInsertId();

    Integer getNumberOfOrder(int brandId);

    Integer getTotalRevenue(int brandId);
}
