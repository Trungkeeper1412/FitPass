package com.ks.fitpass.order.entity.cart;



import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private final List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(GymPlanDepartmentNameDto product, int quantity) {
        for (CartItem item : items) {
            if (item.getGymPlan().getGymPlanId() == product.getGymPlanId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        CartItem newItem = new CartItem();
        newItem.setGymPlan(product);
        newItem.setQuantity(quantity);
        items.add(newItem);
    }

    public void updateQuantity(int productId, int quantity) {
        for (CartItem item : items) {
            if (item.getGymPlan().getGymPlanId() == productId) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    items.remove(item);
                }
                return;
            }
        }
    }



    public void removeItem(int gymPlanId) {
        items.removeIf(item -> item.getGymPlan().getGymPlanId() == gymPlanId);
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getGymPlan().getPrice() * item.getQuantity()).sum();
    }
}
