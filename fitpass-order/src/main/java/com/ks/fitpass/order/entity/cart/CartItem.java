package com.ks.fitpass.order.entity.cart;

import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.department.entity.GymPlan;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CartItem implements Serializable {
    private GymPlanDepartmentNameDto gymPlan;
    private int quantity;

}
