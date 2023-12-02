package com.ks.fitpass.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.employee.dto.UpdateCheckInHistory;
import com.ks.fitpass.employee.service.EmployeeService;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.notification.service.WebSocketService;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.transaction.dto.TransferCreditHistory;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.WebSocket;
import java.sql.Timestamp;
@Controller
@RequestMapping("/confirm")
@RequiredArgsConstructor
public class ConfirmRequestController {
    private final OrderDetailService orderDetailService;
    private final NotificationService notificationService;
    private final WebSocketService webSocketService;
    private final EmployeeService employeeService;
    private final WalletService walletService;
    private final BrandService brandService;
    private final TransactionService transactionService;
    private final CheckInHistoryService checkInHistoryService;
    @GetMapping("/checkin")
    public ResponseEntity<Integer> performFlexibleCheckIn(@RequestParam("id") int orderDetailId,
                                                          @RequestParam("uis") int userIdSend, @RequestParam("uir") int userIdReceive,
                                                          @RequestParam("di") int departmentId, @RequestParam("cancel") String cancel) {
        // Nếu người dùng không nhấn cancel thì check in
        String username = orderDetailService.getUserNameByOrderDetailId(orderDetailId);
        if (cancel.equals("no")) {
            // Gửi lại thông báo cho employee là người dùng đã xác nhận check in thành công
            Notification successNotification = Notification.builder()
                    .userIdSend(userIdSend) //Khách hàng
                    .userIdReceive(userIdReceive) //Nhân viên
                    .messageType("Thông báo checkin thành công tới employee")
                    .message(username + " đã xác nhận check in thành công")
                    .departmentId(departmentId)
                    .orderDetailId(orderDetailId)
                    .timeSend(new Timestamp(System.currentTimeMillis()))
                    .build();
            notificationService.insertNotification(successNotification);
            webSocketService.notifyEmployee(userIdReceive, successNotification);

            // Update bảng check in history
            employeeService.insertToCheckInHistory(orderDetailId, 0, new Timestamp(System.currentTimeMillis()),
                    null, 0, userIdSend);
            // Thay đổi status thành đang tập để chuyển sang tab check in
            orderDetailService.updateOrderDetailsUseStatus(orderDetailId, "Đang tập");

            // Kiểm tra xem nếu khi check in là gói cố định thì phải trừ cả duration đi nữa
            if (orderDetailService.isFixedGymPlan(orderDetailId)) {
                orderDetailService.decreaseDuration(orderDetailId);
            }
        } else {
            // Gửi lại thông báo cho employee là người dùng đã hủy
            Notification cancelNotification = Notification.builder()
                    .userIdSend(userIdSend) //Khách hàng
                    .userIdReceive(userIdReceive) //Nhân viên
                    .messageType("Thông báo hủy checkin tới employee")
                    .message(username + " đã hủy check in")
                    .departmentId(departmentId)
                    .orderDetailId(orderDetailId)
                    .timeSend(new Timestamp(System.currentTimeMillis()))
                    .build();
            notificationService.insertNotification(cancelNotification);
            webSocketService.notifyEmployee(userIdReceive, cancelNotification);
        }
        // Ngược lại trừ duration trong order detail
        return ResponseEntity.ok(1);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Integer> performFlexibleCheckOut(@RequestBody UpdateCheckInHistory updateCheckInHistory, HttpSession session) throws JsonProcessingException {
        if (updateCheckInHistory.getCancel().equals("No")) {
            User user = (User) session.getAttribute("userInfo");

            // Lấy ra notification từ UpdateCheckInHistory để gửi cho employee + update thông tin cho notification
            Notification successNotification = updateCheckInHistory.getNotification();
            String username = orderDetailService.getUserNameByOrderDetailId(successNotification.getOrderDetailId());
            successNotification.setMessageType("Thông báo checkout thành công tới employee");
            successNotification.setMessage(username + " đã thanh toán thành công");
            successNotification.setTimeSend(new Timestamp(System.currentTimeMillis()));

            // Gửi lại thông báo cho employee là đã thanh toán thành công + insert vào db
            notificationService.insertNotification(successNotification);
            webSocketService.notifyEmployee(successNotification.getUserIdReceive(), successNotification);

            // Trừ credit của người dùng
            walletService.updateBalanceByUderId(user.getUserId(), updateCheckInHistory.getCreditAfterPay());
            double credit = walletService.getBalanceByUserId(user.getUserId());

            // Cộng credit cho brand owner
            int brandOwnerId = brandService.getBrandOwnerIdByDepartmentId(successNotification.getDepartmentId());
            double brandOwnerBalance = walletService.getBalanceByUserId(brandOwnerId);
            double brandOwnerCreditAfter = brandOwnerBalance + updateCheckInHistory.getTotalCredit();
            walletService.updateBalanceByUderId(brandOwnerId, brandOwnerCreditAfter);

            // Update bảng Transfer History
            TransferCreditHistory transferCreditHistory = new TransferCreditHistory();
            transferCreditHistory.setSenderUserId(user.getUserId());
            transferCreditHistory.setReceiverUserId(brandOwnerId);
            transferCreditHistory.setAmount(updateCheckInHistory.getTotalCredit());
            transferCreditHistory.setOrderDetailId(successNotification.getOrderDetailId());
            transferCreditHistory.setTransferDate(new Timestamp(System.currentTimeMillis()));

            transactionService.insertTransferCreditHistory(transferCreditHistory);

            session.setAttribute("userCredit", credit);
            // Update status use của order detail id thành chưa tập
            orderDetailService.updateOrderDetailsUseStatus(updateCheckInHistory.getOrderDetailId(), "Chưa tập");
            // Update bảng check in history
            checkInHistoryService.updateCheckOutTimeAndCredit(updateCheckInHistory.getCheckInHistoryId(), updateCheckInHistory.getCheckOutTime(), updateCheckInHistory.getTotalCredit());

        } else {
            // Lấy ra notification từ UpdateCheckInHistory để gửi cho employee + update thông tin cho notification
            Notification cancelNotification = updateCheckInHistory.getNotification();
            String username = orderDetailService.getUserNameByOrderDetailId(cancelNotification.getOrderDetailId());
            cancelNotification.setMessageType("Thông báo hủy checkout tới employee");
            cancelNotification.setMessage(username + " đã hủy checkout");
            cancelNotification.setTimeSend(new Timestamp(System.currentTimeMillis()));

            // Gửi lại thông báo cho employee là đã check in thất bại + insert vào db
            notificationService.insertNotification(cancelNotification);
            webSocketService.notifyEmployee(cancelNotification.getUserIdReceive(), cancelNotification);

        }

        return ResponseEntity.ok(1);
    }
}
