package com.ks.fitpass.order;

import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.mapper.OrderDetailWithDeparmentNameMapper;
import com.ks.fitpass.order.repository.IRepositoryQuery;
import com.ks.fitpass.order.repository.impl.OrderDetailRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class OrderDetailRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private OrderDetailRepositoryImpl orderDetailRepository;

    @BeforeEach
    public void setup() {
        orderDetailRepository = new OrderDetailRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void testInsertOrderDetail() {
        // Create an OrderDetails object with sample data
        OrderDetails orderDetail = OrderDetails.builder()
                .orderId(1)
                .name("Item 1")
                .gymPlanDepartmentId(1)
                .quantity(2)
                .pricePerHours(10.0)
                .price(20.0)
                .duration(1)
                .planBeforeActiveValidity(0)
                .planAfterActiveValidity(30)
                .itemStatusKey(1)
                .description("Test item")
                .build();

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.INSERT_ORDER_PLAN_DETAIL),
                        Mockito.eq(orderDetail.getOrderId()),
                        Mockito.eq(orderDetail.getName()),
                        Mockito.eq(orderDetail.getGymPlanDepartmentId()),
                        Mockito.eq(orderDetail.getQuantity()),
                        Mockito.eq(orderDetail.getPricePerHours()),
                        Mockito.eq(orderDetail.getPrice()),
                        Mockito.eq(orderDetail.getDuration()),
                        Mockito.eq(orderDetail.getPlanBeforeActiveValidity()),
                        Mockito.eq(orderDetail.getPlanAfterActiveValidity()),
                        Mockito.eq(orderDetail.getItemStatusKey()),
                        Mockito.eq(orderDetail.getDescription())))
                .thenReturn(1);

        // Call the insertOrderDetail() method and verify the result
        int result = orderDetailRepository.insertOrderDetail(orderDetail);

        assertEquals(1, result);
    }

    @Test
    public void testGetAllOrderItemByUserId() {
        // Create sample data
        int userId = 1;
        List<OrderDetails> expectedOrderItems = new ArrayList<>();
        expectedOrderItems.add(OrderDetails.builder()
                .orderDetailId(1)
                .orderId(1)
                .name("Item 1")
                .gymPlanDepartmentId(1)
                .gymDepartmentName("Department 1")
                .quantity(2)
                .pricePerHours(10.0)
                .price(20.0)
                .duration(1)
                .planBeforeActiveValidity(0)
                .planAfterActiveValidity(30)
                .planActiveTime(new Timestamp(System.currentTimeMillis()))
                .itemStatusKey(1)
                .planExpiredTime(new Timestamp(System.currentTimeMillis()))
                .description("Test item 1")
                .buyItemTime(new Timestamp(System.currentTimeMillis()))
                .build());
        expectedOrderItems.add(OrderDetails.builder()
                .orderDetailId(2)
                .orderId(1)
                .name("Item 2")
                .gymPlanDepartmentId(2)
                .gymDepartmentName("Department 2")
                .quantity(3)
                .pricePerHours(15.0)
                .price(45.0)
                .duration(2)
                .planBeforeActiveValidity(0)
                .planAfterActiveValidity(60)
                .planActiveTime(new Timestamp(System.currentTimeMillis()))
                .itemStatusKey(1)
                .planExpiredTime(new Timestamp(System.currentTimeMillis()))
                .description("Test item 2")
                .buyItemTime(new Timestamp(System.currentTimeMillis()))
                .build());

        // Mock the behavior of the jdbcTemplate.query() method
        Mockito.when(jdbcTemplate.query(
                        Mockito.eq(IRepositoryQuery.GET_ALL_ORDER_ITEM_BY_USER_ID),
                        Mockito.any(Object[].class),
                        Mockito.any(OrderDetailWithDeparmentNameMapper.class)))
                .thenReturn(expectedOrderItems);

        // Call the getAllOrderItemByUserId() method and verify the result
        List<OrderDetails> result = orderDetailRepository.getAllOrderItemByUserId(userId);

        // Assert the result
        assertEquals(expectedOrderItems, result);
    }

    @Test
    public void testGetOrderDetailByStatusAndUserId() {
        // Create sample data
        int status = 1;
        int userId = 1;
        List<OrderDetails> expectedOrderItems = new ArrayList<>();
        expectedOrderItems.add(OrderDetails.builder()
                .orderDetailId(1)
                .orderId(1)
                .name("Item 1")
                .gymPlanDepartmentId(1)
                .gymDepartmentName("Department 1")
                .quantity(2)
                .pricePerHours(10.0)
                .price(20.0)
                .duration(1)
                .planBeforeActiveValidity(0)
                .planAfterActiveValidity(30)
                .planActiveTime(new Timestamp(System.currentTimeMillis()))
                .itemStatusKey(1)
                .planExpiredTime(new Timestamp(System.currentTimeMillis()))
                .description("Test item 1")
                .buyItemTime(new Timestamp(System.currentTimeMillis()))
                .build());
        expectedOrderItems.add(OrderDetails.builder()
                .orderDetailId(2)
                .orderId(1)
                .name("Item 2")
                .gymPlanDepartmentId(2)
                .gymDepartmentName("Department 2")
                .quantity(3)
                .pricePerHours(15.0)
                .price(45.0)
                .duration(2)
                .planBeforeActiveValidity(0)
                .planAfterActiveValidity(60)
                .planActiveTime(new Timestamp(System.currentTimeMillis()))
                .itemStatusKey(1)
                .planExpiredTime(new Timestamp(System.currentTimeMillis()))
                .description("Test item 2")
                .buyItemTime(new Timestamp(System.currentTimeMillis()))
                .build());

        // Mock the behavior of the jdbcTemplate.query() method
        Mockito.when(jdbcTemplate.query(
                        Mockito.eq(IRepositoryQuery.GET_ORDER_ITEM_BY_STATUS_AND_USER_ID),
                        Mockito.any(Object[].class),
                        Mockito.any(OrderDetailWithDeparmentNameMapper.class)))
                .thenReturn(expectedOrderItems);

        // Call the getOrderDetailByStatusAndUserId() method and verify the result
        List<OrderDetails> result = orderDetailRepository.getOrderDetailByStatusAndUserId(status, userId);

        // Assert the result
        assertEquals(expectedOrderItems, result);
    }

    @Test
    public void testUpdateOrderDetailItemStatus() {
        // Create sample data
        Timestamp planActiveTime = new Timestamp(System.currentTimeMillis());
        int status = 2;
        Timestamp planExpiredTime = new Timestamp(System.currentTimeMillis());
        int orderDetailId = 1;

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS),
                        Mockito.eq(planActiveTime),
                        Mockito.eq(status),
                        Mockito.eq(planExpiredTime),
                        Mockito.eq(orderDetailId)))
                .thenReturn(1);

        // Call the updateOrderDetailItemStatus() method and verify the result
        int result = orderDetailRepository.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);

        // Assert the result
        assertEquals(1, result);
    }
}