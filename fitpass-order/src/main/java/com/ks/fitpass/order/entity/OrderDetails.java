package com.ks.fitpass.order.entity;


import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private int orderDetailId;
    private int orderId;
    private String name;
    private int gymPlanDepartmentId;
    private String gymDepartmentName;
    private int quantity;
    private double pricePerHours;
    private double price;
    private int duration;
    private int planBeforeActiveValidity;
    private int planAfterActiveValidity;
    private Timestamp planActiveTime;
    private int itemStatusKey;
    private Timestamp planExpiredTime;
    private String description;
    private Timestamp buyItemTime;
}
