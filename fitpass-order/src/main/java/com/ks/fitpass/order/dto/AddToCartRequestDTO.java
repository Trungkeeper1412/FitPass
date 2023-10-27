package com.ks.fitpass.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AddToCartRequestDTO {
    int gymPlanId;
    int quantity;
    int departmentId;
}
