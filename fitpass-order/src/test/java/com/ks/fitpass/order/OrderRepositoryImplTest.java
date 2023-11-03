package com.ks.fitpass.order;

import com.ks.fitpass.order.entity.Order;
import com.ks.fitpass.order.repository.IRepositoryQuery;
import com.ks.fitpass.order.repository.impl.OrderRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private OrderRepositoryImpl orderRepositoryImpl;

    @BeforeEach
    public void setup() {
        orderRepositoryImpl = new OrderRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void testInsertOrder() {
        // Create an Order object with sample data
        Order order = Order.builder()
                .userId(1)
                .orderCreateTime(new Timestamp(System.currentTimeMillis()))
                .orderStatusKey(1)
                .discount(10)
                .orderTotalMoney(100.0)
                .orderNote("Test order")
                .build();

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.INSERT_ORDER),
                        Mockito.eq(order.getUserId()),
                        Mockito.eq(order.getOrderCreateTime()),
                        Mockito.eq(order.getOrderStatusKey()),
                        Mockito.eq(order.getDiscount()),
                        Mockito.eq(order.getOrderTotalMoney()),
                        Mockito.eq(order.getOrderNote())))
                .thenReturn(1);

        // Call the insertOrder() method and verify the result
        int result = orderRepositoryImpl.insertOrder(order);
        // Assert result
        assertEquals(1, result);
    }

    @Test
    public void testGetLastOrderInsertId() {
        //Create sample data
        int lastInsertId = 5;

        // Mock the behavior of the jdbcTemplate.queryForObject() method
        Mockito.when(jdbcTemplate.queryForObject(
                        Mockito.eq(IRepositoryQuery.SELECT_LAST_INSERT_ID),
                        Mockito.eq(Integer.class)))
                .thenReturn(lastInsertId);

        // Call the getLastOrderInsertId() method and verify the result
        int result = orderRepositoryImpl.getLastOrderInsertId();
        //assert the result
        assertEquals(lastInsertId, result);
    }
}