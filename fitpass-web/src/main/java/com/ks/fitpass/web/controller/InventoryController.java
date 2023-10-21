package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.service.OrderDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;


@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final OrderDetailService orderDetailService;

    public InventoryController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/view")
    public String viewInventory(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        // Get ALl Item
        List<OrderDetails> itemList = orderDetailService.getAllOrderItemByUserId(user.getUserId());

        // Get item chưa kích hoạt
        List<OrderDetails> itemListNotActive = orderDetailService.getOrderDetailByStatusAndUserId(0, user.getUserId());
        // Get item đã kích hoạt
        List<OrderDetails> itemListActive = orderDetailService.getOrderDetailByStatusAndUserId(1, user.getUserId());
        // Get item đã hết hạn
        List<OrderDetails> itemListExpired = orderDetailService.getOrderDetailByStatusAndUserId(2, user.getUserId());


        model.addAttribute("itemList", itemList);
        model.addAttribute("itemListNotActive", itemListNotActive);
        model.addAttribute("itemListActive", itemListActive);
        model.addAttribute("itemListExpired", itemListExpired);
        return "inventory";
    }

    @PostMapping("/activeItem")
    public String activeItem(Model model, @RequestParam("orderDetailId") int orderDetailId, @RequestParam("duration") int duration) {
        Timestamp planActiveTime = new Timestamp(System.currentTimeMillis());
        long millisecondsInDay = 24 * 60 * 60 * 1000; // Số miligiây trong một ngày

        long durationInMilliseconds = duration * millisecondsInDay;
        long planExpiredTimeMilliseconds = planActiveTime.getTime() + durationInMilliseconds;
        Timestamp planExpiredTime = new Timestamp(planExpiredTimeMilliseconds);
        orderDetailService.updateOrderDetailItemStatus(planActiveTime, 1, planExpiredTime, orderDetailId);
        return "redirect:/inventory/view";
    }
}
