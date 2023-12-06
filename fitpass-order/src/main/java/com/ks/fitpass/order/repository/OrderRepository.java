package com.ks.fitpass.order.repository;

import com.ks.fitpass.order.entity.Order;
import com.ks.fitpass.order.entity.OrderDetails;

import java.util.List;

public interface OrderRepository {
    int insertOrder(Order order);

    int getLastOrderInsertId();

    int getNumberOfOrder(int brandId);

    int getTotalRevenue(int brandId);
}
