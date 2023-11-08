package com.ks.fitpass.order;

import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
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
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderDetailRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private OrderDetailRepositoryImpl orderDetailRepositoryImpl;

    @BeforeEach
    public void setup() {
        orderDetailRepositoryImpl = new OrderDetailRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void testInsertOrderDetail() {
        // Create an OrderDetails object with sample data
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrderId(1);
        orderDetail.setName("Test Order");


        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.INSERT_ORDER_PLAN_DETAIL),
                        Mockito.anyInt(),
                        Mockito.anyString(),
                        Mockito.anyInt(),
                        Mockito.anyInt(),
                        Mockito.anyDouble(),
                        Mockito.anyDouble(),
                        Mockito.anyInt(),
                        Mockito.anyInt(),
                        Mockito.anyInt(),
                        Mockito.anyInt(),
                        Mockito.isNull()))
                .thenReturn(1);

        // Call the insertOrderDetail() method and verify the result
        int result = orderDetailRepositoryImpl.insertOrderDetail(orderDetail);

        // Assert result
        assertEquals(1, result);
    }
    @Test
    public void testGetAllOrderItemByUserId() {
        // Create a list of OrderDetails objects with sample data
        List<OrderDetails> expectedOrderDetailsList = new ArrayList<>();
        // Add sample OrderDetails objects to the list...

        // Mock the behavior of the jdbcTemplate.query() method
        Mockito.when(jdbcTemplate.query(
                        Mockito.eq(IRepositoryQuery.GET_ALL_ORDER_ITEM_BY_USER_ID),
                        Mockito.any(OrderDetailWithDeparmentNameMapper.class),
                        Mockito.eq(1)))
                .thenReturn(expectedOrderDetailsList);

        // Call the getAllOrderItemByUserId() method
        List<OrderDetails> result = orderDetailRepositoryImpl.getAllOrderItemByUserId(1);

        // Assert result
        assertEquals(expectedOrderDetailsList, result);
    }

    @Test
    public void testGetOrderDetailByStatusAndUserId() {
        // Create a list of OrderDetails objects with sample data
        List<OrderDetails> expectedOrderDetailsList = new ArrayList<>();
        // Add sample OrderDetails objects to the list...

        // Mock the behavior of the jdbcTemplate.query() method
        Mockito.when(jdbcTemplate.query(
                        Mockito.eq(IRepositoryQuery.GET_ORDER_ITEM_BY_STATUS_AND_USER_ID),
                        Mockito.any(OrderDetailWithDeparmentNameMapper.class),
                        Mockito.eq(1),
                        Mockito.eq(2)))
                .thenReturn(expectedOrderDetailsList);

        // Call the getOrderDetailByStatusAndUserId() method
        List<OrderDetails> result = orderDetailRepositoryImpl.getOrderDetailByStatusAndUserId(2, 1);

        // Assert result
        assertEquals(expectedOrderDetailsList, result);
    }

    @Test
    public void testUpdateOrderDetailItemStatus() {
        // Set up sample values for the parameters
        Timestamp planActiveTime = new Timestamp(System.currentTimeMillis());
        int status = 1;
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
        int result = orderDetailRepositoryImpl.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);

        // Assert result
        assertEquals(1, result);
    }

    @Test
    public void testUpdateOrderDetailsUseStatus() {
        // Set up sample values for the parameters
        int orderDetailId = 1;
        String statusUse = "USED";

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS_USE),
                        Mockito.eq(statusUse),
                        Mockito.eq(orderDetailId)))
                .thenReturn(1);

        // Call the updateOrderDetailsUseStatus() method and verify the result
        int result = orderDetailRepositoryImpl.updateOrderDetailsUseStatus(orderDetailId, statusUse);

        // Assert result
        assertEquals(1, result);
    }

    @Test
    public void testGetPricePerHoursByOrderDetailId() {
        // Set up sample values for the parameter
        int orderDetailId = 1;
        Double expectedPricePerHours = 10.0;

        // Mock the behavior of the jdbcTemplate.queryForObject() method
        Mockito.when(jdbcTemplate.queryForObject(
                        Mockito.eq(IRepositoryQuery.GET_PRICE_PER_HOURS_BY_ORDER_DETAIL_ID),
                        Mockito.eq(Double.class),
                        Mockito.eq(orderDetailId)))
                .thenReturn(expectedPricePerHours);

        // Call the getPricePerHoursByOrderDetailId() method
        Double result = orderDetailRepositoryImpl.getPricePerHoursByOrderDetailId(orderDetailId);

        // Assert result
        assertEquals(expectedPricePerHours, result);
    }

    @Test
    public void testGetGymDepartmentNameByOrderDetailId() {
        // Set up sample values for the parameter
        int orderDetailId = 1;
        String expectedDepartmentName = "Fitness";

        // Mock the behavior of the jdbcTemplate.queryForObject() method
        Mockito.when(jdbcTemplate.queryForObject(
                        Mockito.eq(IRepositoryQuery.GET_DEPARTMENT_NAME_BY_ORDER_DETAIL_ID),
                        Mockito.eq(String.class),
                        Mockito.eq(orderDetailId)))
                .thenReturn(expectedDepartmentName);

        // Call the getGymDepartmentNameByOrderDetailId() method
        String result = orderDetailRepositoryImpl.getGymDepartmentNameByOrderDetailId(orderDetailId);

        // Assert result
        assertEquals(expectedDepartmentName, result);
    }
    @Test
    public void testGetUserNameByOrderDetailId() {
        // Set up sample values for the parameter
        int orderDetailId = 1;
        String expectedUserName = "John Doe";

        // Mock the behavior of the jdbcTemplate.queryForObject() method
        Mockito.when(jdbcTemplate.queryForObject(
                        Mockito.eq(IRepositoryQuery.GET_USER_NAME_BY_ORDER_DETAIL_ID),
                        Mockito.eq(String.class),
                        Mockito.eq(orderDetailId)))
                .thenReturn(expectedUserName);

        // Call the getUserNameByOrderDetailId() method
        String result = orderDetailRepositoryImpl.getUserNameByOrderDetailId(orderDetailId);

        // Assert result
        assertEquals(expectedUserName, result);
    }

    @Test
    public void testUpdateAllFixedToCheckIn() {
        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ALL_FIXED_TO_CHECK_IN)))
                .thenReturn(1);

        // Call the updateAllFixedToCheckIn() method and verify the result
        int result = orderDetailRepositoryImpl.updateAllFixedToCheckIn();

        // Assert result
        assertEquals(1, result);
    }

    @Test
    void testIsFixedGymPlan_WhenOrderDetailIdExistsAndGymPlanIsFixed() {
        // Arrange
        int orderDetailId = 1;
        boolean expected = true;

        // Mock the behavior of the jdbcTemplate.queryForObject() method
        Mockito.when(jdbcTemplate.queryForObject(
                        Mockito.eq(IRepositoryQuery.IS_FIXED_GYM_PLAN),
                        Mockito.eq(Boolean.class),
                        Mockito.eq(orderDetailId)))
                .thenReturn(true);

        // Act
        boolean actual = orderDetailRepositoryImpl.isFixedGymPlan(orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testIsFixedGymPlan_WhenOrderDetailIdExistsAndGymPlanIsNotFixed() {
        // Arrange
        int orderDetailId = 2;
        boolean expected = false;

        // Mock the behavior of the jdbcTemplate.queryForObject() method
        Mockito.when(jdbcTemplate.queryForObject(
                        Mockito.eq(IRepositoryQuery.IS_FIXED_GYM_PLAN),
                        Mockito.eq(Boolean.class),
                        Mockito.eq(orderDetailId)))
                .thenReturn(false);

        // Act
        boolean actual = orderDetailRepositoryImpl.isFixedGymPlan(orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }
    @Test
    void testDecreaseDuration_WhenOrderDetailIdExistsAndDurationIsSuccessfullyDecreased() {
        // Arrange
        int orderDetailId = 1;
        int expected = 1; // Number of rows affected by the update query

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.DECREASE_DURATION),
                        Mockito.eq(orderDetailId)))
                .thenReturn(1);

        // Act
        int actual = orderDetailRepositoryImpl.decreaseDuration(orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testDecreaseDuration_WhenOrderDetailIdDoesNotExist() {
        // Arrange
        int orderDetailId = 999;
        int expected = 0; // No rows affected as the orderDetailId does not exist

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.DECREASE_DURATION),
                        Mockito.eq(orderDetailId)))
                .thenReturn(0);

        // Act
        int actual = orderDetailRepositoryImpl.decreaseDuration(orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }
//normal test case
    @Test
    void testUpdateOrderDetailItemStatus_WhenParametersAreValidAndUpdateIsSuccessful() {
        // Arrange
        Timestamp planActiveTime = Timestamp.valueOf("2022-01-01 10:00:00");
        int status = 1;
        Timestamp planExpiredTime = Timestamp.valueOf("2022-01-02 10:00:00");
        int orderDetailId = 1;
        int expected = 1; // Number of rows affected by the update query

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS),
                        Mockito.eq(planActiveTime),
                        Mockito.eq(status),
                        Mockito.eq(planExpiredTime),
                        Mockito.eq(orderDetailId)))
                .thenReturn(1);

        // Act
        int actual = orderDetailRepositoryImpl.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }
    //abnormal test case
    @Test
    void testUpdateOrderDetailItemStatus_WhenOrderDetailIdDoesNotExist() {
        // Arrange
        Timestamp planActiveTime = Timestamp.valueOf("2022-01-01 10:00:00");
        int status = 1;
        Timestamp planExpiredTime = Timestamp.valueOf("2022-01-02 10:00:00");
        int orderDetailId = 999; // Non-existing orderDetailId
        int expected = 0; // No rows affected as the orderDetailId does not exist

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS),
                        Mockito.eq(planActiveTime),
                        Mockito.eq(status),
                        Mockito.eq(planExpiredTime),
                        Mockito.eq(orderDetailId)))
                .thenReturn(0);

        // Act
        int actual = orderDetailRepositoryImpl.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }
    @Test
    void testUpdateOrderDetailItemStatus_WhenStatusIsInvalid() {
        // Arrange
        Timestamp planActiveTime = Timestamp.valueOf("2022-01-01 10:00:00");
        int status = -1; // Invalid status value
        Timestamp planExpiredTime = Timestamp.valueOf("2022-01-02 10:00:00");
        int orderDetailId = 1;
        int expected = 0; // No rows affected as status is invalid

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS),
                        Mockito.eq(planActiveTime),
                        Mockito.eq(status),
                        Mockito.eq(planExpiredTime),
                        Mockito.eq(orderDetailId)))
                .thenReturn(0);

        // Act
        int actual = orderDetailRepositoryImpl.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }
//Boundary Test Cases
    @Test
    void testUpdateOrderDetailItemStatus_WhenPlanActiveTimeIsMinimumAllowedValue() {
        // Arrange
        Timestamp planActiveTime = Timestamp.valueOf("1970-01-01 00:00:00");
        int status = 1;
        Timestamp planExpiredTime = Timestamp.valueOf("2022-01-02 10:00:00");
        int orderDetailId = 1;
        int expected = 1; // Number of rows affected by the update query

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS),
                        Mockito.eq(planActiveTime),
                        Mockito.eq(status),
                        Mockito.eq(planExpiredTime),
                        Mockito.eq(orderDetailId)))
                .thenReturn(1);

        // Act
        int actual = orderDetailRepositoryImpl.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }
    //Boundary Test Cases
    @Test
    void testUpdateOrderDetailItemStatus_WhenPlanExpiredTimeIsMaximumAllowedValue() {
        // Arrange
        Timestamp planActiveTime = Timestamp.valueOf("2022-01-01 10:00:00");
        int status = 1;
        Timestamp planExpiredTime = Timestamp.valueOf("9999-12-31 23:59:59");
        int orderDetailId = 1;
        int expected = 1; // Number of rows affected by the update query

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS),
                        Mockito.eq(planActiveTime),
                        Mockito.eq(status),
                        Mockito.eq(planExpiredTime),
                        Mockito.eq(orderDetailId)))
                .thenReturn(1);

        // Act
        int actual = orderDetailRepositoryImpl.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }
    @Test
    void testUpdateOrderDetailItemStatus_WhenOrderDetailIdIsMaximumAllowedValue() {
        // Arrange
        Timestamp planActiveTime = Timestamp.valueOf("2022-01-01 10:00:00");
        int status = 1;
        Timestamp planExpiredTime = Timestamp.valueOf("2022-01-02 10:00:00");
        int orderDetailId = Integer.MAX_VALUE; // Maximum allowed value for orderDetailId
        int expected = 1; // Number of rows affected by the update query

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IRepositoryQuery.UPDATE_ORDER_DETAIL_ITEM_STATUS),
                        Mockito.eq(planActiveTime),
                        Mockito.eq(status),
                        Mockito.eq(planExpiredTime),
                        Mockito.eq(orderDetailId)))
                .thenReturn(1);

        // Act
        int actual = orderDetailRepositoryImpl.updateOrderDetailItemStatus(planActiveTime, status, planExpiredTime, orderDetailId);

        // Assert
        assertEquals(expected, actual);
    }
}