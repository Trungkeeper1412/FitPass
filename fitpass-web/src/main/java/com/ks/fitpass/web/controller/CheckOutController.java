package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.service.KbnService;
import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.order.dto.PlanData;
import com.ks.fitpass.order.entity.Order;
import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.entity.cart.Cart;
import com.ks.fitpass.order.entity.cart.CartItem;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.order.service.OrderService;
import com.ks.fitpass.transaction.dto.TransferCreditHistory;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
    private final BrandService brandService;
    private final TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    public CheckOutController(WalletService walletService, OrderService orderService, OrderDetailService orderDetailService,
                              KbnService kbnService, BrandService brandService, TransactionService transactionService) {
        this.walletService = walletService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.kbnService = kbnService;
        this.brandService = brandService;
        this.transactionService = transactionService;
    }

    @GetMapping("/view")
    public String viewCheckout(Model model, @RequestParam List<Integer> idList,@RequestParam List<Integer> deptIdList, HttpSession session) {
       try {
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

           for (CartItem item :
                   checkoutCartList) {
               GymPlanDepartmentNameDto gymPlanDepartmentNameDto = item.getGymPlan();

               if (departmentList.stream().noneMatch(e -> e.getDepartmentId() == gymPlanDepartmentNameDto.getGymDepartmentId())) {
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
       } catch (Exception ex) {
           // Handle any unexpected exceptions
           logger.error("An unexpected exception occurred", ex);
           return "error/error";
       }
    }

    @PostMapping("/perform")
    public String payment(Model model,  @RequestParam("planId") List<String> planIdList, @RequestParam("totalPrice") double totalPrice, HttpSession session) {
      try {
          Cart cart = (Cart) session.getAttribute("cart");

          User user = (User) session.getAttribute("userInfo");
          List<PlanData> planDataObjects = new ArrayList<>();
          for (String planData :
                  planIdList) {
              String[] parts = planData.split(";");
              Integer gymPlanId = Integer.parseInt(parts[0]);
              Integer departmentId = Integer.parseInt(parts[1]);
              PlanData planDataObject = new PlanData(gymPlanId, departmentId);
              planDataObjects.add(planDataObject);
          }
          if (cart != null) {
              List<CartItem> cartItemList = cart.getItems();
              List<CartItem> cartItemsRemoveList = new ArrayList<>();
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
                  if (planDataObjects.stream().anyMatch(b -> b.getGymPlanId() == cartItem.getGymPlan().getGymPlanId() && b.getDepartmentId() == cartItem.getGymPlan().getGymDepartmentId())) {
                      GymPlanDepartmentNameDto gymPlanDepartmentNameDto = cartItem.getGymPlan();
                      OrderDetails orderDetails = new OrderDetails();
                      orderDetails.setOrderId(orderId);
                      orderDetails.setName(gymPlanDepartmentNameDto.getGymPlanName());
                      orderDetails.setGymPlanDepartmentId(gymPlanDepartmentNameDto.getGymDepartmentId());
                      orderDetails.setQuantity(1);
                      String gymPlanType = kbnService.getGymPlanTypeByPlanKey(gymPlanDepartmentNameDto.getGymPlanTypeKey());
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

                      // Set thời gian hết hạn của gói tập
                      int planBeforeActiveValidity = gymPlanDepartmentNameDto.getPlanBeforeActiveValidity();
                      Timestamp planExpiredTime = new Timestamp(System.currentTimeMillis());
                      planExpiredTime.setTime(planExpiredTime.getTime() + planBeforeActiveValidity * 24 * 60 * 60 * 1000);
                      orderDetails.setPlanExpiredTime(planExpiredTime);

                      int insertOrderDetailStatus = orderDetailService.insertOrderDetail(orderDetails);
                      int lastOrderDetailId = 0;
                      if (insertOrderDetailStatus <= 0) {
                          statusInsertOrderDetailBoolean = false;
                      } else {
                          lastOrderDetailId = orderDetailService.getLatestOrderDetailId();
                      }
                      cartItemsRemoveList.add(cartItem);

                      if (gymPlanDepartmentNameDto.getPrice() > 0 && lastOrderDetailId > 0) {
                          int brandOwnerId = brandService.getBrandOwnerIdByDepartmentId(gymPlanDepartmentNameDto.getGymDepartmentId());
                          double brandOwnerBalance = walletService.getBalanceByUserId(brandOwnerId);
                          double brandOwnerCreditAfter = brandOwnerBalance + gymPlanDepartmentNameDto.getPrice();
                          walletService.updateBalanceByUderId(brandOwnerId, brandOwnerCreditAfter);
                          TransferCreditHistory transferCreditHistory = new TransferCreditHistory();
                          transferCreditHistory.setSenderUserId(user.getUserId());
                          transferCreditHistory.setReceiverUserId(brandOwnerId);
                          transferCreditHistory.setAmount(gymPlanDepartmentNameDto.getPrice());
                          transferCreditHistory.setOrderDetailId(lastOrderDetailId);
                          transferCreditHistory.setTransferDate(new Timestamp(System.currentTimeMillis()));
                          transactionService.insertTransferCreditHistory(transferCreditHistory);
                      }

                  }
              }
              int insertOrderDetailStatus = statusInsertOrderDetailBoolean == false ? 0 : 1;

              // Gửi thông báo về client
              String messageInsert = insertOrderStatus > 0 ? "Mua thành công" : "Mua không thành công";
              model.addAttribute("messageInsertOrder", messageInsert);

              // Remove khỏi cart
              for (CartItem i :
                      cartItemsRemoveList) {
                  cart.removeItem(i.getGymPlan().getGymPlanId(), i.getGymPlan().getGymDepartmentId());
              }
              session.setAttribute("cart", cart);
              // Lấy user balance
              double userBalance = walletService.getBalanceByUserId(user.getUserId());
              double creditAfter = userBalance - totalPrice;

              session.setAttribute("userCredit", creditAfter);
              // Cập nhật lại user balance sau khi đã trừ
              walletService.updateBalanceByUderId(user.getUserId(), creditAfter);
          }
          return "check-out";
      } catch (Exception ex) {
          // Handle any unexpected exceptions
          logger.error("An unexpected exception occurred", ex);
          return "error/error";
      }
    }
}
