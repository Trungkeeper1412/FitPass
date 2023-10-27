package com.ks.fitpass.order.service;

import com.ks.fitpass.order.entity.Order;
import com.ks.fitpass.order.entity.OrderDetails;

import java.sql.Timestamp;
import java.util.List;


public interface OrderService {

    int insertOrder(Order order);

    int getLastOrderInsertId();


}
