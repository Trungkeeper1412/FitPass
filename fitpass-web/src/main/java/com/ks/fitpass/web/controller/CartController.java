package com.ks.fitpass.web.controller;


import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.department.entity.GymPlan;
import com.ks.fitpass.department.service.GymPlanService;
import com.ks.fitpass.order.dto.CartUpdateRequestDto;
import com.ks.fitpass.order.entity.cart.Cart;
import com.ks.fitpass.order.entity.cart.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final GymPlanService gymPlanService;

    @Autowired
    public CartController(GymPlanService gymPlanService) {
        this.gymPlanService = gymPlanService;
    }

    @GetMapping("/add")
    public String addToCart(@RequestParam int gymPlanId, @RequestParam int quantity, HttpSession session) {
        GymPlanDepartmentNameDto product = gymPlanService.getGymPlanByGymPlanId(gymPlanId);
        if (product != null && quantity > 0) {
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
            boolean productExistsInCart = false;
            for (CartItem item : cart.getItems()) {
                if (item.getGymPlan().getGymPlanId() == gymPlanId) {
                    item.setQuantity(item.getQuantity() + quantity);
                    productExistsInCart = true;
                    break;
                }
            }

            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm nó vào
            if (!productExistsInCart) {
                cart.addItem(product, quantity);
            }

            // Cập nhật session
            session.setAttribute("cart", cart);
        }

        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(Model model,  HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        List<CartItem> cartItems;
        cartItems = cart.getItems();
        model.addAttribute("cartItems", cartItems);
        return "shopping-cart";
    }

    @GetMapping("/remove")
    public String removeItem(@RequestParam int gymPlanId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeItem(gymPlanId);
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart/view";
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<String> updateCartQuantity(@RequestBody CartUpdateRequestDto request, HttpSession session) {
        int gymPlanId = request.getGymPlanId();
        int quantity = request.getQuantity();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.updateQuantity(gymPlanId, quantity);
            // Cập nhật giỏ hàng trong session
            session.setAttribute("cart", cart);
            return ResponseEntity.ok("Cart updated successfully");
        }
        return ResponseEntity.badRequest().body("Cart not found");
    }
}
