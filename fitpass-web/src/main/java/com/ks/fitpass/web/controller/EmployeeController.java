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
    public String getCheckInListOfFlexibleCustomer(@RequestParam("departmentId") int departmentId, Model model){
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

    @GetMapping("/flexible/checkin")
    public ResponseEntity<Integer> performFlexibleCheckIn(@RequestParam("id") int orderDetailId,
                                                          @RequestParam("uis") int userIdSend,@RequestParam("uir") int userIdReceive,
                                                          @RequestParam("di") int departmentId, @RequestParam("cancel") String cancel){
        // Nếu người dùng không nhấn cancel thì check in
        if(cancel.equals("no")) {
            int updateResult = employeeService.insertToCheckInHistory(orderDetailId, 0, new Timestamp(System.currentTimeMillis()), null, 0, userIdSend);
            // Thay đổi status thành đang tập để chuyển sang tab check in
            int updateOrderDetailUseStatus = orderDetailService.updateOrderDetailsUseStatus(orderDetailId, "Đang tập");
            // Gửi lại thông báo cho employee là người dùng đã xác nhận check in thành công
            Notification notification = new Notification();
            notification.setUserIdSend(userIdReceive);
            notification.setUserIdReceive(userIdSend);
            String username = orderDetailService.getUserNameByOrderDetailId(orderDetailId);
            notification.setMessageType("Thông báo checkin thành công tới employee");
            notification.setMessage(username + " đã xác nhận check in thành công");
            notification.setDepartmentId(departmentId);
            notification.setTimeSend(new Timestamp(System.currentTimeMillis()));
            int insertStatus = notificationService.insertNotification(notification);

            // Kiểm tra xem nếu khi check in là gói cố định thì phải trừ cả duration đi nữa
            if(orderDetailService.isFixedGymPlan(orderDetailId)) {
                orderDetailService.decreaseDuration(orderDetailId);
            }
        } else {
            // Gửi lại thông báo cho employee là người dùng đã hủy
            Notification notification = new Notification();
            notification.setUserIdSend(userIdReceive);
            notification.setUserIdReceive(userIdSend);
            String username = orderDetailService.getUserNameByOrderDetailId(orderDetailId);
            notification.setMessageType("Thông báo hủy checkin tới employee");
            notification.setMessage(username + " đã hủy check in");
            notification.setDepartmentId(departmentId);
            notification.setTimeSend(new Timestamp(System.currentTimeMillis()));
            int insertStatus = notificationService.insertNotification(notification);
        }
        // Kiểm tra xem nếu người dùng đã check in trong ngày hôm nay thì không trừ duration

        // Ngược lại trừ duration trong order detail
        return ResponseEntity.ok(1);
    }

    @PostMapping("/flexible/checkout")
    public ResponseEntity<Integer> performFlexibleCheckOut(@RequestBody UpdateCheckInHistory updateCheckInHistory, HttpSession session) throws JsonProcessingException {
        if(updateCheckInHistory.getCancel().equals("No")) {
            int updateResult = checkInHistoryService.updateCheckOutTimeAndCredit(updateCheckInHistory.getCheckInHistoryId(), updateCheckInHistory.getCheckOutTime(), updateCheckInHistory.getTotalCredit());
            // Update status use của order detail id thành chưa tập
            orderDetailService.updateOrderDetailsUseStatus(updateCheckInHistory.getOrderDetailId(), "Chưa tập");
            User user = (User) session.getAttribute("userInfo");

            // Lấy ra notification từ employee gửi yêu cầu check out trước đấy
            Notification notificationFromEmployee = updateCheckInHistory.getNotification();
            ObjectMapper objectMapper = new ObjectMapper();
            DataSendCheckOutFlexibleDTO dataSendCheckOutFlexibleDTO = objectMapper.readValue(notificationFromEmployee.getMessage(), DataSendCheckOutFlexibleDTO.class);
            int orderDetailId = dataSendCheckOutFlexibleDTO.getOrderDetailId();


            // Gửi lại thông báo cho người gửi trước đấy là đã thanh toán thành công
            Notification notification = new Notification();
            notification.setUserIdSend(notificationFromEmployee.getUserIdReceive());
            notification.setUserIdReceive(notificationFromEmployee.getUserIdSend());
            String username = orderDetailService.getUserNameByOrderDetailId(orderDetailId);
            notification.setMessageType("Thông báo checkout thành công tới employee");
            notification.setMessage(username + " đã thanh toán thành công");
            notification.setDepartmentId(notificationFromEmployee.getDepartmentId());
            notification.setTimeSend(new Timestamp(System.currentTimeMillis()));
            int insertStatus = notificationService.insertNotification(notification);

            // Trừ credit của người dùng
            walletService.updateBalanceByUderId(user.getUserId(), updateCheckInHistory.getCreditAfterPay());
        } else {
            Notification notificationFromEmployee = updateCheckInHistory.getNotification();
            ObjectMapper objectMapper = new ObjectMapper();
            DataSendCheckOutFlexibleDTO dataSendCheckOutFlexibleDTO = objectMapper.readValue(notificationFromEmployee.getMessage(), DataSendCheckOutFlexibleDTO.class);
            int orderDetailId = dataSendCheckOutFlexibleDTO.getOrderDetailId();
            // Gửi lại thông báo cho người gửi trước đấy là đã thanh toán thành công
            Notification notification = new Notification();
            notification.setUserIdSend(notificationFromEmployee.getUserIdReceive());
            notification.setUserIdReceive(notificationFromEmployee.getUserIdSend());
            String username = orderDetailService.getUserNameByOrderDetailId(orderDetailId);
            notification.setMessageType("Thông báo hủy checkout tới employee");
            notification.setMessage(username + " đã hủy checkout");
            notification.setDepartmentId(notificationFromEmployee.getDepartmentId());
            notification.setTimeSend(new Timestamp(System.currentTimeMillis()));
            int insertStatus = notificationService.insertNotification(notification);
        }

        return ResponseEntity.ok(1);
    }

    @GetMapping("/flexible/sendCheckinRequest")
    public ResponseEntity<Integer> sendCheckInRequest(@RequestParam("id") int orderDetailId, HttpSession session){
        // Lấy ra thông tin người dùng hiện tại
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông tin người cần gửi đến (người dùng checkin)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        Notification notification = new Notification();
        // Truyền id người gửi
        notification.setUserIdSend(user.getUserId());
        notification.setUserIdReceive(userReceiveMessageDTO.getUserId());
        notification.setMessageType("Xác nhận check in");
        notification.setMessage(""+orderDetailId);
        notification.setDepartmentId(userReceiveMessageDTO.getGymDepartmentId());
        notification.setTimeSend(new Timestamp(System.currentTimeMillis()));

        int insertStatus = notificationService.insertNotification(notification);

        return ResponseEntity.ok(insertStatus);
    }

    @PostMapping("/flexible/sendCheckoutRequest")
    public ResponseEntity<Integer> sendCheckoutRequest(@RequestBody DataSendCheckOutFlexibleDTO dataSendCheckOutFlexibleDTO, HttpSession session) throws JsonProcessingException {
//         Lấy ra thông tin người dùng hiện tại
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông tin người cần gửi đến (người dùng cần checkout)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(dataSendCheckOutFlexibleDTO.getOrderDetailId());

        // Lấy ra thời điểm check out
        Timestamp checkOutTime = new Timestamp(dataSendCheckOutFlexibleDTO.getCheckOutTime());
        int duration = dataSendCheckOutFlexibleDTO.getDuration();
        Timestamp checkInTime = dataSendCheckOutFlexibleDTO.getCheckInTime();
        double totalCredit = dataSendCheckOutFlexibleDTO.getTotalCredit();
        int orderDetailId = dataSendCheckOutFlexibleDTO.getOrderDetailId();

        Notification notification = new Notification();
        // Truyền id người gửi
        notification.setUserIdSend(user.getUserId());
        // Truyền id người nhận
        notification.setUserIdReceive(userReceiveMessageDTO.getUserId());
        notification.setMessageType("Xác nhận check out");
        // Truyền thông báo dưới dạng json string để về sau hiện pop up cofirm check out xử lí
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(dataSendCheckOutFlexibleDTO);
        notification.setMessage(jsonString);
        notification.setDepartmentId(userReceiveMessageDTO.getGymDepartmentId());
        notification.setTimeSend(new Timestamp(System.currentTimeMillis()));


        // Gửi thông báo đến người dùng
        int insertStatus = notificationService.insertNotification(notification);

        return ResponseEntity.ok(insertStatus);
    }

    @GetMapping("/flexible/getCheckInTime")
    public ResponseEntity<DetailCheckOutDTO> showDetail(@RequestParam("id") int orderDetailId){
        Timestamp checkInTime = checkInHistoryService.getCheckInTimeByOrderDetailId(orderDetailId);
        double pricePerHours = orderDetailService.getPricePerHoursByOrderDetailId(orderDetailId);
        DetailCheckOutDTO detailCheckOutDTO = new DetailCheckOutDTO();
        detailCheckOutDTO.setCheckInTime(checkInTime);
        detailCheckOutDTO.setPricePerHours(pricePerHours);
        return ResponseEntity.ok(detailCheckOutDTO);
    }

    @GetMapping("/fixed/sendCheckinRequest")
    public ResponseEntity<Integer> sendCheckInRequestFixed(@RequestParam("id") int orderDetailId, HttpSession session){
        // Lấy ra thông tin người dùng hiện tại
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông tin người cần gửi đến (người dùng checkin)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        Notification notification = new Notification();
        // Truyền id người gửi
        notification.setUserIdSend(user.getUserId());
        notification.setUserIdReceive(userReceiveMessageDTO.getUserId());
        notification.setMessageType("Xác nhận check in");
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
    public String getCheckInHistory(@RequestParam("id")int departmentId, Model model){
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

    public String calculateDuration(Timestamp checkInTime, Timestamp checkOutTime) {
        // Tính khoảng cách thời gian giữa checkInTime và checkOutTime
        long durationMillis = checkOutTime.getTime() - checkInTime.getTime();

        // Chuyển đổi khoảng cách thời gian thành giờ và phút
        long hours = durationMillis / (60 * 60 * 1000);
        long minutes = (durationMillis % (60 * 60 * 1000)) / (60 * 1000);

        // Định dạng giờ và phút thành chuỗi "hour:minutes"
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date durationDate = new Date(0);
        durationDate.setTime(durationMillis);
        return sdf.format(durationDate);
    }
}
