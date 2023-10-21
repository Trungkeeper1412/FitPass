package com.ks.fitpass.order.entity;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private int userId;
    private int gymDepartmentId;
    private Timestamp orderCreateTime;
    private int orderStatusKey;
    private int discount;
    private double orderTotalMoney;
    private String orderNote;
}
