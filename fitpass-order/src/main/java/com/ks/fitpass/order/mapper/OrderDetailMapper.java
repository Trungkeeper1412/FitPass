package com.ks.fitpass.order.mapper;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.order.entity.OrderDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailMapper implements RowMapper<OrderDetails> {
    @Override
    public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return OrderDetails.builder()
                .orderDetailId(rs.getInt("order_detail_id"))
                .orderId(rs.getInt("order_id"))
                .name(rs.getString("name"))
                .quantity(rs.getInt("quantity"))
                .pricePerHours(rs.getDouble("price_per_hours"))
                .price(rs.getDouble("price"))
                .duration(rs.getInt("duration"))
                .planBeforeActiveValidity(rs.getInt("plan_before_active_validity"))
                .planAfterActiveValidity(rs.getInt("plan_after_active_validity"))
                .gymPlanDepartmentId(rs.getInt("gym_department_id"))
                .planActiveTime(rs.getTimestamp("plan_active_time"))
                .itemStatusKey(rs.getInt("item_status_key"))
                .planExpiredTime(rs.getTimestamp("plan_expired_time"))
                .description(rs.getString("description"))
                .build();
    }
}
