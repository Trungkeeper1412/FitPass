package com.ks.fitpass.order.mapper;

import com.ks.fitpass.order.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailWithDeparmentNameMapper extends OrderDetailMapper{
    @Override
    public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDetails o = super.mapRow(rs, rowNum);
        o.setGymDepartmentName(rs.getString("department_name"));
        o.setBuyItemTime(rs.getTimestamp("order_create_time"));
        return o;
    }
}
