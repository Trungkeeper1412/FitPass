package com.ks.fitpass.order.repository.impl;

import com.ks.fitpass.order.entity.Order;
import com.ks.fitpass.order.repository.IRepositoryQuery;
import com.ks.fitpass.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertOrder(Order order) {
        return jdbcTemplate.update(IRepositoryQuery.INSERT_ORDER, order.getUserId(), order.getOrderCreateTime(),
                order.getOrderStatusKey(), order.getDiscount(),
                order.getOrderTotalMoney(), order.getOrderNote());
    }

    @Override
    public int getLastOrderInsertId() {
        return jdbcTemplate.queryForObject(IRepositoryQuery.SELECT_LAST_INSERT_ID, Integer.class);
    }

    @Override
    public int getNumberOfOrder(int brandId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.SELECT_NUMBER_OF_ORDER, Integer.class, brandId);
    }

    @Override
    public int getTotalRevenue(int brandId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.SELECT_TOTAL_REVENUE, Integer.class, brandId);
    }
}
