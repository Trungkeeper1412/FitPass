package com.ks.fitpass.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.fitpass.checkInHistory.repository.CheckInHistoryRepository;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.employee.dto.DataSendCheckOutFlexibleDTO;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;
    private final OrderDetailService orderDetailService;
    private final WalletService walletService;
    private final CheckInHistoryService checkInHistoryService;

    public NotificationController(NotificationService notificationService, OrderDetailService orderDetailService, WalletService walletService, CheckInHistoryService checkInHistoryService) {
        this.notificationService = notificationService;
        this.orderDetailService = orderDetailService;
        this.walletService = walletService;
        this.checkInHistoryService = checkInHistoryService;
    }

    @GetMapping("/confirmCheckIn")
    public ResponseEntity<Notification> getConfirmCheckIn(HttpSession session){
        // Lấy ra thông tin người dùng hiện tại
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông báo confirm check in mới nhất với id của người dùng hiện tại
        Notification notification = notificationService.getConfirmCheckInByUserIdReceive(user.getUserId());
        if(notification == null) {
            Notification n = new Notification();
            n.setNotificationId(-1);
            return ResponseEntity.ok(n);
        }
        notification.setUserIdReceive(user.getUserId());
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/notificationCheckInEmployee")
    public ResponseEntity<Notification> getEmployeeCheckInNotification(HttpSession session){
        // Lấy ra thông tin người dùng hiện tại
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông báo confirm check in mới nhất với id của người dùng hiện tại
        Notification notification = notificationService.getConfirmCheckInByEmpIdReceive(user.getUserId());
        if(notification == null) {
            Notification n = new Notification();
            n.setNotificationId(-1);
            return ResponseEntity.ok(n);
        }
        notification.setUserIdReceive(user.getUserId());
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/confirmCheckOut")
    public ResponseEntity<OrderDetailConfirmCheckOut> getConfirmCheckOut(HttpSession session) throws JsonProcessingException {
        // Lấy ra thông tin người dùng hiện tại
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông báo confirm check in mới nhất với id của người dùng hiện tại
        Notification notification = notificationService.getConfirmCheckOutByUserIdReceive(user.getUserId());
        if(notification == null) {
            OrderDetailConfirmCheckOut n = new OrderDetailConfirmCheckOut();
            n.setDurationHavePractice(-1);
            return ResponseEntity.ok(n);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        DataSendCheckOutFlexibleDTO dataSendCheckOutFlexibleDTO = objectMapper.readValue(notification.getMessage(), DataSendCheckOutFlexibleDTO.class);
        // Lấy ra hết thông tin gửi từ check out
        int orderDetailId = dataSendCheckOutFlexibleDTO.getOrderDetailId();
        int duration = dataSendCheckOutFlexibleDTO.getDuration();
        Timestamp checkInTime = dataSendCheckOutFlexibleDTO.getCheckInTime();
        long checkOutTimeLong = dataSendCheckOutFlexibleDTO.getCheckOutTime();
        double totalCredit = dataSendCheckOutFlexibleDTO.getTotalCredit();

        // Set các thông tin cần gửi về front
        OrderDetailConfirmCheckOut orderCheckOut = orderDetailService.getByOrderDetailId(orderDetailId);
        double userBalance = walletService.getBalanceByUserId(user.getUserId());
        orderCheckOut.setCreditInWallet(userBalance);
        orderCheckOut.setCreditNeedToPay(totalCredit);
        orderCheckOut.setCreditAfterPay(userBalance - totalCredit);
        orderCheckOut.setDurationHavePractice(duration);
        orderCheckOut.setNotificationId(notification.getNotificationId());
        orderCheckOut.setHistoryCheckInId(checkInHistoryService.getCheckInHistoryIdByOrderDetailIdAndCheckInTime(orderDetailId, checkInTime));
        orderCheckOut.setCheckOutTime(new Timestamp(checkOutTimeLong));
        orderCheckOut.setOrderDetailId(orderDetailId);
        return ResponseEntity.ok(orderCheckOut);
    }

    @GetMapping("/seen")
    public ResponseEntity<Integer> performSeenNotification(@RequestParam("id") int notificationId){
        // Chuyển status thành 1 là đã đọc
        int status = notificationService.updateStatusNotificationById(notificationId, 1);
        return ResponseEntity.ok(status);
    }
}
