package com.ks.fitpass.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFixed;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.employee.dto.*;
import com.ks.fitpass.employee.service.EmployeeService;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.notification.service.WebSocketService;
import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
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
    private final WebSocketService webSocketService;

    public EmployeeController(EmployeeService employeeService, OrderDetailService orderDetailService,
                              NotificationService notificationService, CheckInHistoryService checkInHistoryService,
                              WalletService walletService, WebSocketService webSocketService) {
        this.employeeService = employeeService;
        this.orderDetailService = orderDetailService;
        this.notificationService = notificationService;
        this.checkInHistoryService = checkInHistoryService;
        this.walletService = walletService;
        this.webSocketService = webSocketService;
    }

    @GetMapping("/check-in/fixed")
    public String getCheckInListOfFixedCustomer(@RequestParam("departmentId") int departmentId, Model model) {
        List<CheckInFixedDTO> checkInFixedDTOList = employeeService.getListNeedCheckInFixedByDepartmentId(departmentId);
        List<CheckedInFixedDTO> checkedInDTOList = employeeService.getListCheckedInFixedByDepartmentId(departmentId);
        model.addAttribute("checkInList", checkInFixedDTOList);
        model.addAttribute("checkedInList", checkedInDTOList);
        model.addAttribute("departmentId", departmentId);
        return "employee/employee-check-in-fixed";
    }

    @GetMapping("/check-in/flexible")
    public String getCheckInListOfFlexibleCustomer(@RequestParam("departmentId") int departmentId, Model model) {
        List<CheckInFlexibleDTO> checkInFlexibleDTOList = employeeService.getListNeedCheckInFlexibleByDepartmentId(departmentId);
        List<CheckOutFlexibleDTO> checkOutFlexibleDTOList = employeeService.getListNeedCheckOutFlexibleByDepartmentId(departmentId);
        model.addAttribute("checkInList", checkInFlexibleDTOList);
        model.addAttribute("checkOutList", checkOutFlexibleDTOList);
        model.addAttribute("departmentId", departmentId);
        return "employee/employee-check-in-flexible";
    }

    @GetMapping("/searchListCheckIn")
    @ResponseBody
    public ResponseEntity<List<CheckInFlexibleDTO>> searchListCheckIn(
            @RequestParam("searchText") String searchText,
            @RequestParam("searchOption") String searchOption,
            @RequestParam("departmentId") int departmentId
    ) {
        List<CheckInFlexibleDTO> searchResults;

        if ("username".equals(searchOption)) {
            // Tìm kiếm theo tên (username)
            searchResults = employeeService.searchListCheckInByUsername(searchText, departmentId);
        } else if ("phonenumber".equals(searchOption)) {
            // Tìm kiếm theo số điện thoại (phone number)
            searchResults = employeeService.searchListCheckInByPhoneNumber(searchText, departmentId);
        } else {
            // Mặc định tìm kiếm theo tên (username)
            searchResults = employeeService.searchListCheckInByUsername(searchText, departmentId);
        }

        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/searchListCheckOut")
    @ResponseBody
    public ResponseEntity<List<CheckInFlexibleDTO>> searchListCheckOut(
            @RequestParam("searchText") String searchText,
            @RequestParam("searchOption") String searchOption,
            @RequestParam("departmentId") int departmentId
    ) {
        List<CheckInFlexibleDTO> searchResults;

        if ("username".equals(searchOption)) {
            // Tìm kiếm theo tên (username)
            searchResults = employeeService.searchListCheckOutByUsername(searchText, departmentId);
        } else if ("phonenumber".equals(searchOption)) {
            // Tìm kiếm theo số điện thoại (phone number)
            searchResults = employeeService.searchListCheckOutByPhoneNumber(searchText, departmentId);
        } else {
            // Mặc định tìm kiếm theo tên (username)
            searchResults = employeeService.searchListCheckOutByUsername(searchText, departmentId);
        }

        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/flexible/sendCheckinRequest")
    public ResponseEntity<Integer> sendCheckInRequest(@RequestParam("id") int orderDetailId, HttpSession session) {
        // Lấy ra thông tin người dùng hiện tại (employee)
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông tin người cần gửi đến (người dùng checkin)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        int userIdSend = user.getUserId();
        String usernameSend = user.getUserAccount();
        int userIdReceived = userReceiveMessageDTO.getUserId();
        String messageType = "Xác nhận check in";
        int departmentId = userReceiveMessageDTO.getGymDepartmentId();
        String message = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check in ở phòng tập " + departmentId + ". Hãy xác nhận ngay!";

        // Truyền nội dung notification
        Notification notification = Notification.builder()
                .orderDetailId(orderDetailId)
                .userIdSend(userIdSend)
                .userIdReceive(userIdReceived)
                .messageType(messageType)
                .message(message)
                .departmentId(departmentId)
                .timeSend(new Timestamp(System.currentTimeMillis()))
                .build();

        // Gửi thông báo đến người dùng + insert vào db
        webSocketService.notifyUser(userReceiveMessageDTO.getUserId(), notification);
        int insertStatus = notificationService.insertNotification(notification);
        return ResponseEntity.ok(insertStatus);
    }

    @PostMapping("/flexible/sendCheckoutRequest")
    public ResponseEntity<Integer> sendCheckoutRequest(@RequestBody DataSendCheckOutFlexibleDTO dataSendCheckOutFlexibleDTO, HttpSession session) throws JsonProcessingException {
        // Lấy ra thông tin người dùng hiện tại (employee)
        User user = (User) session.getAttribute("userInfo");

        int orderDetailId = dataSendCheckOutFlexibleDTO.getOrderDetailId();
        // Lấy ra thông tin người cần gửi đến (người dùng cần checkout)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        int userIdSend = user.getUserId();
        String usernameSend = user.getUserAccount();
        int userIdReceived = userReceiveMessageDTO.getUserId();
        String messageType = "Xác nhận check out";
        int departmentId = userReceiveMessageDTO.getGymDepartmentId();
        String employeeMessage = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check out ở phòng tập " +
                departmentId + ". Hãy bấm vào để xem chi tiết.";
        dataSendCheckOutFlexibleDTO.setEmployeeMessage(employeeMessage);

        // Lấy ra hết thông tin gửi từ check out + Set các thông tin cần gửi về front
        int duration = dataSendCheckOutFlexibleDTO.getDuration();
        Timestamp checkInTime = dataSendCheckOutFlexibleDTO.getCheckInTime();
        long checkOutTimeLong = dataSendCheckOutFlexibleDTO.getCheckOutTime();
        double totalCredit = dataSendCheckOutFlexibleDTO.getTotalCredit();

        OrderDetailConfirmCheckOut orderCheckOut = orderDetailService.getByOrderDetailId(orderDetailId);
        double userBalance = walletService.getBalanceByUserId(userIdReceived);
        int checkInHistoryId = checkInHistoryService.getCheckInHistoryIdByOrderDetailIdAndCheckInTime(orderDetailId, checkInTime);

        orderCheckOut.setCreditInWallet(userBalance);
        orderCheckOut.setCreditNeedToPay(totalCredit);
        orderCheckOut.setCreditAfterPay(userBalance - totalCredit);
        orderCheckOut.setDurationHavePractice(duration);
        orderCheckOut.setHistoryCheckInId(checkInHistoryId);
        orderCheckOut.setCheckOutTime(new Timestamp(checkOutTimeLong));
        orderCheckOut.setOrderDetailId(orderDetailId);

        // Extract JSON strings for orderDetailConfirmCheckOut and dataSendCheckOutFlexible
        String orderDetailConfirmCheckOutJson = new ObjectMapper().writeValueAsString(orderCheckOut);
        String dataSendCheckOutFlexibleJson = new ObjectMapper().writeValueAsString(dataSendCheckOutFlexibleDTO);


        // Truyền nội dung notification
        Notification notification = Notification.builder()
                .orderDetailId(orderDetailId)
                .userIdSend(userIdSend)
                .userIdReceive(userIdReceived)
                .messageType(messageType)
                // Truyền message dưới dạng json string để về sau hiện pop up confirm check out xử lí
                .message(orderDetailConfirmCheckOutJson + "|" + dataSendCheckOutFlexibleJson)  // Combine the two JSON strings using a separator, e.g., "|"
                .departmentId(departmentId)
                .timeSend(new Timestamp(System.currentTimeMillis()))
                .build();

        // Gửi thông báo đến người dùng + insert vào db
        webSocketService.notifyUser(userReceiveMessageDTO.getUserId(), notification);
        int insertStatus = notificationService.insertNotification(notification);
        return ResponseEntity.ok(insertStatus);
    }

    @GetMapping("/flexible/checkin")
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
                    .timeSend(new Timestamp(System.currentTimeMillis()))
                    .build();

            webSocketService.notifyEmployee(userIdReceive, successNotification);
            notificationService.insertNotification(successNotification);

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
                    .timeSend(new Timestamp(System.currentTimeMillis()))
                    .build();
            webSocketService.notifyEmployee(userIdReceive, cancelNotification);
            notificationService.insertNotification(cancelNotification);
        }
        // Ngược lại trừ duration trong order detail
        return ResponseEntity.ok(1);
    }

    @PostMapping("/flexible/checkout")
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
            webSocketService.notifyEmployee(successNotification.getUserIdReceive(), successNotification);
            notificationService.insertNotification(successNotification);


            // Trừ credit của người dùng
            walletService.updateBalanceByUderId(user.getUserId(), updateCheckInHistory.getCreditAfterPay());
            double credit = walletService.getBalanceByUserId(user.getUserId());
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
            webSocketService.notifyEmployee(cancelNotification.getUserIdReceive(), cancelNotification);
            notificationService.insertNotification(cancelNotification);
        }

        return ResponseEntity.ok(1);
    }

    @GetMapping("/flexible/getCheckInTime")
    public ResponseEntity<DetailCheckOutDTO> showDetail(@RequestParam("id") int orderDetailId) {
        Timestamp checkInTime = checkInHistoryService.getCheckInTimeByOrderDetailId(orderDetailId);
        double pricePerHours = orderDetailService.getPricePerHoursByOrderDetailId(orderDetailId);
        DetailCheckOutDTO detailCheckOutDTO = new DetailCheckOutDTO();
        detailCheckOutDTO.setCheckInTime(checkInTime);
        detailCheckOutDTO.setPricePerHours(pricePerHours);
        return ResponseEntity.ok(detailCheckOutDTO);
    }

    @GetMapping("/fixed/sendCheckinRequest")
    public ResponseEntity<Integer> sendCheckInRequestFixed(@RequestParam("id") int orderDetailId, HttpSession session) {
        // Lấy ra thông tin người dùng hiện tại (employee)
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông tin người cần gửi đến (người dùng checkin)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        int userIdSend = user.getUserId();
        String usernameSend = user.getUserAccount();
        int userIdReceived = userReceiveMessageDTO.getUserId();
        String messageType = "Xác nhận check in";
        int departmentId = userReceiveMessageDTO.getGymDepartmentId();
        String message = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check in ở phòng tập " + departmentId + ". Hãy xác nhận ngay!";

        // Truyền nội dung notification
        Notification notification = Notification.builder()
                .orderDetailId(orderDetailId)
                .userIdSend(userIdSend)
                .userIdReceive(userIdReceived)
                .messageType(messageType)
                .message(message)
                .departmentId(departmentId)
                .timeSend(new Timestamp(System.currentTimeMillis()))
                .build();

        webSocketService.notifyUser(userReceiveMessageDTO.getUserId(), notification);
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
            // Tìm kiếm theo tên (username)
            searchResults = employeeService.searchListCheckInFixedByUsername(searchText, departmentId);
        } else if ("phonenumber".equals(searchOption)) {
            // Tìm kiếm theo số điện thoại (phone number)
            searchResults = employeeService.searchListCheckInFixedByPhoneNumber(searchText, departmentId);
        } else {
            // Mặc định tìm kiếm theo tên (username)
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
            // Tìm kiếm theo tên (username)
            searchResults = employeeService.searchListCheckedInFixedByUsername(searchText, departmentId);
        } else if ("phonenumber".equals(searchOption)) {
            // Tìm kiếm theo số điện thoại (phone number)
            searchResults = employeeService.searchListCheckedInFixedByPhoneNumber(searchText, departmentId);
        } else {
            // Mặc định tìm kiếm theo tên (username)
            searchResults = employeeService.searchListCheckedInFixedByUsername(searchText, departmentId);
        }
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/history")
    public String getCheckInHistory(@RequestParam("id") int departmentId, Model model) {
        List<CheckInHistoryFlexible> listFlexible = checkInHistoryService.getListCheckInHistoryFlexibleByDepartmentId(departmentId);
        List<CheckInHistoryFixed> listFixed = checkInHistoryService.getListCheckInHistoryFixedByDepartmentId(departmentId);
        model.addAttribute("listFlexible", listFlexible);
        model.addAttribute("listFixed", listFixed);
        model.addAttribute("departmentId", departmentId);
        return "employee/employee-check-in-history";
    }

    @GetMapping("/history/searchFlex")
    public ResponseEntity<List<CheckInHistoryFlexible>> searchFlex(
            @RequestParam("id") int departmentId,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "dateFilter", required = false) String dateFilter) {
        List<CheckInHistoryFlexible> listFlexible = checkInHistoryService.searchListHistoryFlexible(departmentId, username, phoneNumber, dateFilter);
        return ResponseEntity.ok(listFlexible);
    }

    @GetMapping("/history/searchFixed")
    public ResponseEntity<List<CheckInHistoryFixed>> searchFixed(
            @RequestParam("id") int departmentId,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "dateFilter", required = false) String dateFilter) {
        List<CheckInHistoryFixed> listFlexible = checkInHistoryService.searchListHistoryFixed(departmentId, username, phoneNumber, dateFilter);
        return ResponseEntity.ok(listFlexible);
    }
}
