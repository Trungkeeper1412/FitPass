package com.ks.fitpass.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.employee.dto.*;
import com.ks.fitpass.employee.service.EmployeeService;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final OrderDetailService orderDetailService;
    private final NotificationService notificationService;
    private final CheckInHistoryService checkInHistoryService;
    private final WalletService walletService;

    public EmployeeController(EmployeeService employeeService, OrderDetailService orderDetailService,
                              NotificationService notificationService, CheckInHistoryService checkInHistoryService, WalletService walletService) {
        this.employeeService = employeeService;
        this.orderDetailService = orderDetailService;
        this.notificationService = notificationService;
        this.checkInHistoryService = checkInHistoryService;
        this.walletService = walletService;
    }

    @GetMapping("/check-in/fixed")
    public String getCheckInListOfFixedCustomer(@RequestParam("departmentId") int departmentId, Model model){
        List<CheckInFixedDTO> checkInFixedDTOList = employeeService.getListNeedCheckInFixedByDepartmentId(departmentId);
        List<CheckedInFixedDTO> checkedInDTOList = employeeService.getListCheckedInFixedByDepartmentId(departmentId);
        model.addAttribute("checkInList", checkInFixedDTOList);
        model.addAttribute("checkedInList", checkedInDTOList);
        model.addAttribute("departmentId", departmentId);
        return "employee/employee-check-in-fixed";
    }

    @GetMapping("/check-in/flexible")
    public String getCheckInListOfFlexibleCustomer(){
        return "employee/employee-check-in-flexible";
    }

    @GetMapping("/fixed/sendCheckinRequest")
    public ResponseEntity<Integer> sendCheckInRequestFixed(@RequestParam("id") int orderDetailId, HttpSession session){
        // L?y ra thông tin ngu?i dùng hi?n t?i
        User user = (User) session.getAttribute("userInfo");

        // L?y ra thông tin ngu?i c?n g?i d?n (ngu?i dùng checkin)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        Notification notification = new Notification();
        // Truy?n id ngu?i g?i
        notification.setUserIdSend(user.getUserId());
        notification.setUserIdReceive(userReceiveMessageDTO.getUserId());
        notification.setMessageType("Xác nh?n check in");
        notification.setMessage(""+orderDetailId);
        notification.setDepartmentId(userReceiveMessageDTO.getGymDepartmentId());
        notification.setTimeSend(new Timestamp(System.currentTimeMillis()));

        int insertStatus = notificationService.insertNotification(notification);

        return ResponseEntity.ok(insertStatus);
    }

    @GetMapping("/fixed/searchListCheckIn")
    @ResponseBody
    public ResponseEntity<List<CheckInFixedDTO>> searchListFixedCheckIn(
            @RequestParam("searchText") String searchText,
            @RequestParam("searchOption") String searchOption,
            @RequestParam("departmentId") int departmentId
    ) {
        List<CheckInFixedDTO> searchResults;

        if ("username".equals(searchOption)) {
            // Tìm ki?m theo tên (username)
            searchResults = employeeService.searchListCheckInFixedByUsername(searchText, departmentId);
        } else if ("phonenumber".equals(searchOption)) {
            // Tìm ki?m theo s? di?n tho?i (phone number)
            searchResults = employeeService.searchListCheckInFixedByPhoneNumber(searchText, departmentId);
        } else {
            // M?c d?nh tìm ki?m theo tên (username)
            searchResults = employeeService.searchListCheckInFixedByUsername(searchText, departmentId);
        }
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/fixed/searchListCheckOut")
    @ResponseBody
    public ResponseEntity<List<CheckedInFixedDTO>> searchListFixedCheckOut(
            @RequestParam("searchText") String searchText,
            @RequestParam("searchOption") String searchOption,
            @RequestParam("departmentId") int departmentId
    ) {
        List<CheckedInFixedDTO> searchResults;

        if ("username".equals(searchOption)) {
            // Tìm ki?m theo tên (username)
            searchResults = employeeService.searchListCheckedInFixedByUsername(searchText, departmentId);
        } else if ("phonenumber".equals(searchOption)) {
            // Tìm ki?m theo s? di?n tho?i (phone number)
            searchResults = employeeService.searchListCheckedInFixedByPhoneNumber(searchText, departmentId);
        } else {
            // M?c d?nh tìm ki?m theo tên (username)
            searchResults = employeeService.searchListCheckedInFixedByUsername(searchText, departmentId);
        }
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/history")
    public String getCheckInHistory(@RequestParam("id")int departmentId, Model model){
        List<CheckInHistoryFlexible> listFlexible = checkInHistoryService.getListCheckInHistoryFlexibleByDepartmentId(departmentId);
        model.addAttribute("listFlexible", listFlexible);
        return "employee/employee-check-in-history";
    }

    public String calculateDuration(Timestamp checkInTime, Timestamp checkOutTime) {
        // Tính kho?ng cách th?i gian gi?a checkInTime và checkOutTime
        long durationMillis = checkOutTime.getTime() - checkInTime.getTime();

        // Chuy?n d?i kho?ng cách th?i gian thành gi? và phút
        long hours = durationMillis / (60 * 60 * 1000);
        long minutes = (durationMillis % (60 * 60 * 1000)) / (60 * 1000);

        // Ð?nh d?ng gi? và phút thành chu?i "hour:minutes"
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date durationDate = new Date(0);
        durationDate.setTime(durationMillis);
        return sdf.format(durationDate);
    }
}
