package com.ks.fitpass.web.controller;


import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.order.dto.AddToCartRequestDTO;
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

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequestDTO addToCartRequestDTO ,HttpSession session) {
        int gymPlanId = addToCartRequestDTO.getGymPlanId();
        int quantity = addToCartRequestDTO.getQuantity();
        int departmentId = addToCartRequestDTO.getDepartmentId();
        GymPlanDepartmentNameDto product = gymPlanService.getGymPlanByGymPlanId(gymPlanId,departmentId);
        if (product != null && quantity > 0) {
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
            boolean productExistsInCart = false;
            for (CartItem item : cart.getItems()) {
                if (item.getGymPlan().getGymPlanId() == gymPlanId && item.getGymPlan().getGymDepartmentId()==departmentId) {
/*                    item.setQuantity(item.getQuantity() + quantity);*/
                    productExistsInCart = true;
                    break;

                }
            }

            if(productExistsInCart) {
                return ResponseEntity.badRequest().body("Sản phẩm này đã add vào giỏ hàng");
            }

            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm nó vào
            if (!productExistsInCart) {
                cart.addItem(product, quantity);
            }

        // Update session
        session.setAttribute("cart", cart);
    } else {
        // Handle invalid input
        return ResponseEntity.badRequest().body("Invalid input for adding to cart");
    }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/quantityInCart")
    @ResponseBody
    public ResponseEntity<Integer> getQuantityInCart(HttpSession session) {

            Cart cart = (Cart) session.getAttribute("cart");
            int quantity = 0;
            if(cart != null) {
                quantity = cart.getItems().size();
            }
        return ResponseEntity.ok(quantity);
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

        List<Department> departmentList = new ArrayList<>();

        for (CartItem item:
             cartItems) {
            GymPlanDepartmentNameDto gymPlanDepartmentNameDto = item.getGymPlan();

            if(departmentList.stream().noneMatch(e ->  e.getDepartmentId() == gymPlanDepartmentNameDto.getGymDepartmentId())) {
                Department department = new Department();
                department.setDepartmentId(gymPlanDepartmentNameDto.getGymDepartmentId());
                department.setDepartmentName(gymPlanDepartmentNameDto.getGymDepartmentName());
                departmentList.add(department);
            }
        }

        model.addAttribute("departmentList", departmentList);
        model.addAttribute("cartItems", cartItems);
        return "shopping-cart";
    }

    @GetMapping("/remove")
    public String removeItem(@RequestParam int gymPlanId,@RequestParam int departmentId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeItem(gymPlanId,departmentId);
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

    //Test method
    @GetMapping("/checkout")
    public String showCheckout() {
        return "check-out";
    }
}
