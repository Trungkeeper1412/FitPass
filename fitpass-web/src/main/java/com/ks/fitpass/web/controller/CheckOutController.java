package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.service.KbnService;
import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.order.entity.Order;
import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.entity.cart.Cart;
import com.ks.fitpass.order.entity.cart.CartItem;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.order.service.OrderService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/checkout")
public class CheckOutController {

    private final WalletService walletService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    private final KbnService kbnService;

    @Autowired
    public CheckOutController(WalletService walletService, OrderService orderService, OrderDetailService orderDetailService, KbnService kbnService) {
        this.walletService = walletService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.kbnService = kbnService;
    }

    @GetMapping("/view")
    public String viewCheckout(Model model, @RequestParam List<Integer> idList,@RequestParam List<Integer> deptIdList, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> checkoutCartList = new ArrayList<>();
        if (cart != null) {
            List<CartItem> cartItemList = cart.getItems();
            for (CartItem cartItem : cartItemList) {
                if (idList.contains(cartItem.getGymPlan().getGymPlanId()) && deptIdList.contains(cartItem.getGymPlan().getGymDepartmentId())) {
                    checkoutCartList.add(cartItem);
                }
            }
        }

        // Lấy ra tất cả department list trong check out cart
        List<Department> departmentList = new ArrayList<>();

        for (CartItem item:
                checkoutCartList) {
            GymPlanDepartmentNameDto gymPlanDepartmentNameDto = item.getGymPlan();

            if(departmentList.stream().noneMatch(e ->  e.getDepartmentId() == gymPlanDepartmentNameDto.getGymDepartmentId())) {
                Department department = new Department();
                department.setDepartmentId(gymPlanDepartmentNameDto.getGymDepartmentId());
                department.setDepartmentName(gymPlanDepartmentNameDto.getGymDepartmentName());
                departmentList.add(department);
            }
        }

        model.addAttribute("departmentList", departmentList);

        double totalPrice = checkoutCartList.stream().mapToDouble(item -> item.getGymPlan().getPrice() * item.getQuantity()).sum();

        // Lấy user balance
        User user = (User) session.getAttribute("userInfo");
        double userBalance = walletService.getBalanceByUserId(user.getUserId());

        model.addAttribute("userBalanceAfterBuying", userBalance - totalPrice);
        model.addAttribute("userBalance", userBalance);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("checkoutCartList", checkoutCartList);
        return "check-out";
    }

    @PostMapping("/perform")
    public String payment(Model model,  @RequestParam("planId") List<String> planIdList, @RequestParam("totalPrice") double totalPrice, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");

        User user = (User) session.getAttribute("userInfo");

        if (cart != null) {
            List<CartItem> cartItemList = cart.getItems();
            List<CartItem> cartItemsRemoveList= new ArrayList<>();
            boolean statusInsertOrderDetailBoolean = true;
            Order order = new Order();
            order.setUserId(user.getUserId());
            order.setOrderCreateTime(new Timestamp(System.currentTimeMillis()));
            order.setOrderStatusKey(1);
            // Set tổng tiền của hóa đơn
            order.setOrderTotalMoney(totalPrice);
            order.setDiscount(0);
            int insertOrderStatus = orderService.insertOrder(order);
            int orderId = orderService.getLastOrderInsertId();
            for (CartItem cartItem : cartItemList) {
                if (planIdList.contains(cartItem.getGymPlan().getGymPlanId())) {
                    GymPlanDepartmentNameDto gymPlanDepartmentNameDto = cartItem.getGymPlan();
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOrderId(orderId);
                    orderDetails.setName(gymPlanDepartmentNameDto.getGymPlanName());
                    orderDetails.setGymPlanDepartmentId(gymPlanDepartmentNameDto.getGymDepartmentId());
                    orderDetails.setQuantity(1);
                    String gymPlanType = kbnService.getGymPlanTypeByPlanKey(gymPlanDepartmentNameDto.getGymPlanKey());
                    if (gymPlanType.equalsIgnoreCase("Gói không theo giờ")) {
                        orderDetails.setPrice(gymPlanDepartmentNameDto.getPrice());
                    } else if (gymPlanType.equalsIgnoreCase("Gói theo giờ")) {
                        orderDetails.setPricePerHours(gymPlanDepartmentNameDto.getPricePerHours());
                    }
                    orderDetails.setDuration(gymPlanDepartmentNameDto.getDuration());
                    orderDetails.setPlanAfterActiveValidity(gymPlanDepartmentNameDto.getPlanAfterActiveValidity());
                    orderDetails.setPlanBeforeActiveValidity(gymPlanDepartmentNameDto.getPlanBeforeActiveValidity());
                    // Set status là chưa kích hoạt (Trạng thái vừa mới mua và thanh toán)
                    orderDetails.setItemStatusKey(0);
                    orderDetails.setDescription(gymPlanDepartmentNameDto.getGymPlanDescription());

                    int insertOrderDetailStatus = orderDetailService.insertOrderDetail(orderDetails);
                    if(insertOrderDetailStatus <= 0) {
                        statusInsertOrderDetailBoolean = false;
                    }
                    cartItemsRemoveList.add(cartItem);
                }
            }
            int insertOrderDetailStatus = statusInsertOrderDetailBoolean == false ? 0 : 1;

            // Gửi thông báo về client
            String messageInsert = insertOrderStatus > 0 ? "Mua thành công" : "Mua không thành công";
            model.addAttribute("messageInsertOrder", messageInsert);

            // Remove khỏi cart
            for (CartItem i:
                 cartItemsRemoveList) {
             cart.removeItem(i.getGymPlan().getGymPlanId(),i.getGymPlan().getGymDepartmentId());
            }
            session.setAttribute("cart", cart);
            // Lấy user balance
            double userBalance = walletService.getBalanceByUserId(user.getUserId());
            double creditAfter = userBalance - totalPrice;

            session.setAttribute("userCredit",creditAfter);
            // Cập nhật lại user balance sau khi đã trừ
            walletService.updateBalanceByUderId(user.getUserId(), creditAfter);
        }



        return "check-out";
    }
}
