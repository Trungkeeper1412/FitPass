package com.ks.fitpass.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private int orderDetailId;
    private int orderId;
    private String name;
    private int gymPlanDepartmentId;
    private String gymPlanDepartmentName;
    private int quantity;
    private double pricePerHours;
    private double price;
    private int duration;
    private int planBeforeActiveValidity;
    private int planAfterActiveValidity;
}

