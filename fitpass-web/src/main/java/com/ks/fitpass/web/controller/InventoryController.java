package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.service.OrderDetailService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;


@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final OrderDetailService orderDetailService;

    public InventoryController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @GetMapping("/view")
    public String viewInventory(Model model, HttpSession session) {
        try {
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


            if (session.getAttribute("activeItemMSG") != null) {
                model.addAttribute("activeItemMSG", session.getAttribute("activeItemMSG"));
                session.removeAttribute("activeItemMSG");
            }
            return "user/inventory";
        }catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return "error/duplicate-key-error";
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return "error/no-data";
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return "error/incorrect-result-size-error";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @PostMapping("/activeItem")
    public String activeItem(@RequestParam("orderDetailId") int orderDetailId, @RequestParam("duration") int duration, HttpSession session) {
      try {
          Timestamp planActiveTime = new Timestamp(System.currentTimeMillis());
          long millisecondsInDay = 24 * 60 * 60 * 1000; // Số miligiây trong một ngày

          long durationInMilliseconds = duration * millisecondsInDay;
          long planExpiredTimeMilliseconds = planActiveTime.getTime() + durationInMilliseconds;
          Timestamp planExpiredTime = new Timestamp(planExpiredTimeMilliseconds);
          // Chuyển trạng thái thành chưa tập để lấy ra bên check in
          orderDetailService.updateOrderDetailsUseStatus(orderDetailId, "Chưa tập");
          int status = orderDetailService.updateOrderDetailItemStatus(planActiveTime, 1, planExpiredTime, orderDetailId);
          String message = status > 0 ? "Kích hoạt thành công" : "Kích hoạt không thành công";
          session.setAttribute("activeItemMSG", message);
          return "redirect:/inventory/view";
      }catch (DuplicateKeyException ex) {
          // Handle duplicate key violation
          logger.error("DuplicateKeyException occurred", ex);
          return "error/duplicate-key-error";
      } catch (EmptyResultDataAccessException ex) {
          // Handle empty result set
          logger.error("EmptyResultDataAccessException occurred", ex);
          return "error/no-data";
      } catch (IncorrectResultSizeDataAccessException ex) {
          // Handle incorrect result size
          logger.error("IncorrectResultSizeDataAccessException occurred", ex);
          return "error/incorrect-result-size-error";
      } catch (DataAccessException ex) {
          // Handle other data access issues
          logger.error("DataAccessException occurred", ex);
          return "error/data-access-error";
      }
    }
}
