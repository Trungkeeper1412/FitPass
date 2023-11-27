package com.ks.fitpass.order.repository;

import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
import com.ks.fitpass.order.dto.OrderDetailDTO;
import com.ks.fitpass.order.entity.OrderDetails;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDetailRepository {
    int insertOrderDetail(OrderDetails orderDetails);

    List<OrderDetails> getAllOrderItemByUserId(int userId);

    List<OrderDetails> getOrderDetailByStatusAndUserId(int status, int userId);

    int updateOrderDetailItemStatus(Timestamp planActiveTime, int status, Timestamp planExpiredTime, int orderDetailId);

    int updateOrderDetailsUseStatus(int orderDetailId, String statusUse);

    Double getPricePerHoursByOrderDetailId(int orderDetailId);

    String getGymDepartmentNameByOrderDetailId(int orderDetailId);

    OrderDetailConfirmCheckOut getByOrderDetailId(int orderDetailId);

    String getUserNameByOrderDetailId(int orderDetailId);

    int updateAllFixedToCheckIn();

    Boolean isFixedGymPlan(int orderDetailId);

    int decreaseDuration(int orderDetailId);

    List<Integer> getListOrderDetailExpired();

    int[] updateOrderDetailExpiredStatus(List<Integer> listId);

    int getLatestOrderDetailId();
}
