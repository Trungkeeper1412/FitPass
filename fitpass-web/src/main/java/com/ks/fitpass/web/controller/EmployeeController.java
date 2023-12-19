package com.ks.fitpass.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFixed;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryPage;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.department.dto.DepartmentNotificationDTO;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.employee.dto.*;
import com.ks.fitpass.employee.service.EmployeeService;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.notification.service.WebSocketService;
import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
import com.ks.fitpass.order.service.OrderDetailService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    private final OrderDetailService orderDetailService;
    private final NotificationService notificationService;
    private final CheckInHistoryService checkInHistoryService;
    private final WebSocketService webSocketService;
    private final DepartmentService departmentService;
    private final UserService userService;
    private final UserRepository userRepository;


    public EmployeeController(EmployeeService employeeService, OrderDetailService orderDetailService,
                              NotificationService notificationService, CheckInHistoryService checkInHistoryService, WebSocketService webSocketService,
                              DepartmentService departmentService, UserService userService, UserRepository userRepository) {
        this.employeeService = employeeService;
        this.orderDetailService = orderDetailService;
        this.notificationService = notificationService;
        this.checkInHistoryService = checkInHistoryService;
        this.webSocketService = webSocketService;
        this.departmentService = departmentService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public boolean checkValidDepartmentParameter(HttpSession session, int departmentId) {
        try {
            User user = (User) session.getAttribute("userInfo");
            int userDepartmentId = userRepository.getDepartmentIdByEmployeeId(user.getUserId());
            return departmentId == userDepartmentId;
        } catch (Exception e) {
            return false;
        }
    }

    @ModelAttribute
    public void populateEmployeeInfo(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        UserDetail userDetail = userService.getUserDetailByUserId(user.getUserId());
        int userDepartmentId = userRepository.getDepartmentIdByEmployeeId(user.getUserId());

        session.setAttribute("userFullNameEmp", userDetail.getFirstName().concat(" ").concat(userDetail.getLastName()));
        session.setAttribute("userAvatarEmp", userDetail.getImageUrl());
        model.addAttribute("departmentId", userDepartmentId);
    }

    @GetMapping("/changePassword")
    public String getRegistrationList() {
        return "employee/change-password";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            User user = (User) session.getAttribute("userInfo");

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Kiểm tra mật khẩu hiện tại
            if (!passwordEncoder.matches(currentPassword, user.getUserPassword())) {
                model.addAttribute("currentPasswordError", "Mật khẩu hiện tại không đúng");
                return "employee/change-password";
            }

            // Kiểm tra mật khẩu mới và xác nhận mật khẩu
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("passwordMismatchError", "Mật khẩu mới và xác nhận mật khẩu không khớp");
                return "employee/change-password";
            }
            String hashedPassword = passwordEncoder.encode(newPassword);
            // Cập nhật mật khẩu mới
            userService.updatePassword(hashedPassword, user.getUserId());

            redirectAttributes.addAttribute("success", "true");
            return "redirect:/employee/changePassword";
            
        } catch (Exception e) {
            // Handle other exceptions if necessary
            model.addAttribute("unexpectedError", "Lỗi không xác định");
            return "employee/change-password";
        }
    }

    @GetMapping("/check-in/fixed")
    public String getCheckInListOfFixedCustomer(@RequestParam("departmentId") int departmentId, Model model, HttpSession session) {
        try {
            if (session == null || !checkValidDepartmentParameter(session, departmentId)) {
                return "error/403";
            }
            List<CheckInFixedDTO> checkInFixedDTOList = employeeService.getListNeedCheckInFixedByDepartmentId(departmentId);
            List<CheckedInFixedDTO> checkedInDTOList = employeeService.getListCheckedInFixedByDepartmentId(departmentId);
            model.addAttribute("checkInList", checkInFixedDTOList);
            model.addAttribute("checkedInList", checkedInDTOList);
            return "employee/employee-check-in-fixed";
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            return "error/duplicate-key-error";
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            return "error/no-data";
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            return "error/incorrect-result-size-error";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            return "error/data-access-error";
        }
    }


    @GetMapping("/check-in/flexible")
    public String getCheckInListOfFlexibleCustomer(@RequestParam("departmentId") int departmentId, Model model, HttpSession session) {
        try {
            if (session == null || !checkValidDepartmentParameter(session, departmentId)) {
                return "error/403";
            }
            List<CheckInFlexibleDTO> checkInFlexibleDTOList = employeeService.getListNeedCheckInFlexibleByDepartmentId(departmentId);
            List<CheckOutFlexibleDTO> checkOutFlexibleDTOList = employeeService.getListNeedCheckOutFlexibleByDepartmentId(departmentId);
            model.addAttribute("checkInList", checkInFlexibleDTOList);
            model.addAttribute("checkOutList", checkOutFlexibleDTOList);
            return "employee/employee-check-in-flexible";
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            return "error/duplicate-key-error";
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            return "error/no-data";
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            return "error/incorrect-result-size-error";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            return "error/data-access-error";
        }
    }


    @GetMapping("/searchListCheckIn")
    @ResponseBody
    public ResponseEntity<List<CheckInFlexibleDTO>> searchListCheckIn(HttpSession session,
                                                                      @RequestParam("searchText") String searchText,
                                                                      @RequestParam("searchOption") String searchOption,
                                                                      @RequestParam("departmentId") int departmentId
    ) {
        try {
            if (session == null || !checkValidDepartmentParameter(session, departmentId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            List<CheckInFlexibleDTO> searchResults;

            if ("username".equals(searchOption)) {
                // Tìm kiếm theo tên (username)
                searchResults = employeeService.searchListCheckInByUsername(searchText, departmentId);
            } else if ("phone-number".equals(searchOption)) {
                // Tìm kiếm theo số điện thoại (phone number)
                searchResults = employeeService.searchListCheckInByPhoneNumber(searchText, departmentId);
            } else {
                // Mặc định tìm kiếm theo tên (username)
                searchResults = employeeService.searchListCheckInByUsername(searchText, departmentId);
            }

            return ResponseEntity.ok(searchResults);
        } catch (DataAccessException e) {
            // Handle DataAccessException
            logger.error("Error during database access in searchListCheckIn", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            // Handle other exceptions if necessary
            logger.error("An unexpected error occurred in searchListCheckIn", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/searchListCheckOut")
    @ResponseBody
    public ResponseEntity<List<CheckInFlexibleDTO>> searchListCheckOut(HttpSession session,
                                                                       @RequestParam("searchText") String searchText,
                                                                       @RequestParam("searchOption") String searchOption,
                                                                       @RequestParam("departmentId") int departmentId
    ) {
        try {
            if (session == null || !checkValidDepartmentParameter(session, departmentId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            List<CheckInFlexibleDTO> searchResults;

            if ("username".equals(searchOption)) {
                // Tìm kiếm theo tên (username)
                searchResults = employeeService.searchListCheckOutByUsername(searchText, departmentId);
            } else if ("phone-number".equals(searchOption)) {
                // Tìm kiếm theo số điện thoại (phone number)
                searchResults = employeeService.searchListCheckOutByPhoneNumber(searchText, departmentId);
            } else {
                // Mặc định tìm kiếm theo tên (username)
                searchResults = employeeService.searchListCheckOutByUsername(searchText, departmentId);
            }

            return ResponseEntity.ok(searchResults);
        } catch (DataAccessException e) {
            // Handle DataAccessException
            logger.error("Error during database access in searchListCheckIn", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            // Handle other exceptions if necessary
            logger.error("An unexpected error occurred in searchListCheckIn", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/flexible/sendCheckinRequest")
    public ResponseEntity<Integer> sendCheckInRequest(@RequestParam("id") int orderDetailId, HttpSession session) {
        // Lấy ra thông tin người dùng hiện tại (employee)
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông tin người cần gửi đến (người dùng checkin)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        int departmentId = userReceiveMessageDTO.getGymDepartmentId();
        DepartmentNotificationDTO departmentNotificationDTO = departmentService.getDepartmentNotificationDtoById(departmentId);

        String departmentName = departmentNotificationDTO.getDepartmentName();
        String departmentLogoUrl = departmentNotificationDTO.getDepartmentLogoUrl();

        UserDetail employeeDetail = userService.getUserDetailByUserDetailId(user.getUserId());
        String usernameSend = employeeDetail.getFirstName().concat(" ").concat(employeeDetail.getLastName());

        int userIdSend = user.getUserId();
        int userIdReceived = userReceiveMessageDTO.getUserId();
        String messageType = "Xác nhận check in";

        String message = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check in ở phòng tập " + departmentName + ". Hãy xác nhận ngay!";

        // Truyền nội dung notification
        Notification notification = Notification.builder()
                .orderDetailId(orderDetailId)
                .userIdSend(userIdSend)
                .userIdReceive(userIdReceived)
                .messageType(messageType)
                .message(message)
                .departmentId(departmentId)
                .departmentName(departmentName)
                .departmentLogoUrl(departmentLogoUrl)
                .timeSend(new Timestamp(System.currentTimeMillis()))
                .build();

        int insertStatus = 0;

        try {
            //insert vào db
            insertStatus = notificationService.insertNotification(notification);

            // Retrieve the generated ID after insertion
            if (insertStatus > 0) {
                logger.info("The User Received id ={}", userIdReceived);
                // Gửi thông báo đến người dùng
                webSocketService.notifyUser(userIdReceived, notification);
                return ResponseEntity.ok(insertStatus);
            } else {
                logger.error("Notification insertion failed for some reason.");
            }
        } catch (DataAccessException e) {
            logger.error("Error during notification insertion", e);
        }

        return ResponseEntity.ok(insertStatus);
    }

    @PostMapping("/flexible/sendCheckoutRequest")
    public ResponseEntity<Integer> sendCheckoutRequest(@RequestBody DataSendCheckOutFlexibleDTO dataSendCheckOutFlexibleDTO, HttpSession session) throws JsonProcessingException {
        // Lấy ra thông tin người dùng hiện tại (employee)
        User user = (User) session.getAttribute("userInfo");

        int orderDetailId = dataSendCheckOutFlexibleDTO.getOrderDetailId();
        // Lấy ra thông tin người cần gửi đến (người dùng cần checkout)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        // Get department informations
        int departmentId = userReceiveMessageDTO.getGymDepartmentId();
        DepartmentNotificationDTO departmentNotificationDTO = departmentService.getDepartmentNotificationDtoById(departmentId);
        String departmentName = departmentNotificationDTO.getDepartmentName();
        String departmentLogoUrl = departmentNotificationDTO.getDepartmentLogoUrl();

        //Get employee name
        UserDetail employeeDetail = userService.getUserDetailByUserDetailId(user.getUserId());
        String usernameSend = employeeDetail.getFirstName().concat(" ").concat(employeeDetail.getLastName());

        int userIdSend = user.getUserId();
        int userIdReceived = userReceiveMessageDTO.getUserId();
        String messageType = "Xác nhận check out";
        String employeeMessage = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check out ở phòng tập " +
                departmentName + ". Hãy bấm vào để xem chi tiết.";
        dataSendCheckOutFlexibleDTO.setEmployeeMessage(employeeMessage);

        // Lấy ra hết thông tin gửi từ check out + Set các thông tin cần gửi về front
        int duration = dataSendCheckOutFlexibleDTO.getDuration();
        Timestamp checkInTime = dataSendCheckOutFlexibleDTO.getCheckInTime();
        long checkOutTimeLong = dataSendCheckOutFlexibleDTO.getCheckOutTime();

        OrderDetailConfirmCheckOut orderCheckOut = orderDetailService.getByOrderDetailId(orderDetailId);
        int checkInHistoryId = checkInHistoryService.getCheckInHistoryIdByOrderDetailIdAndCheckInTime(orderDetailId, checkInTime);

        // Set trg hợp khách hàng chưa tập đủ 1 tiếng
        double totalCredit;
        if (duration <= 60) {
            totalCredit = orderCheckOut.getPricePerHours();
        } else {
            totalCredit = dataSendCheckOutFlexibleDTO.getTotalCredit();
        }

        orderCheckOut.setCreditNeedToPay(totalCredit);
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
                .departmentName(departmentName)
                .departmentLogoUrl(departmentLogoUrl)
                .timeSend(new Timestamp(System.currentTimeMillis()))
                .build();

        int insertStatus = 0;

        try {
            //insert vào db
            insertStatus = notificationService.insertNotification(notification);

            // Retrieve the generated ID after insertion
            if (insertStatus > 0) {
                webSocketService.notifyUser(userReceiveMessageDTO.getUserId(), notification);
                return ResponseEntity.ok(insertStatus);
            } else {
                logger.error("Notification insertion failed for some reason.");
            }
        } catch (DataAccessException e) {
            logger.error("Error during notification insertion", e);
        }

        return ResponseEntity.ok(insertStatus);
    }

    @GetMapping("/flexible/getCheckInTime")
    public ResponseEntity<DetailCheckOutDTO> showDetail(@RequestParam("id") int orderDetailId) {
        try {
            Timestamp checkInTime = checkInHistoryService.getCheckInTimeByOrderDetailId(orderDetailId);
            double pricePerHours = orderDetailService.getPricePerHoursByOrderDetailId(orderDetailId);
            DetailCheckOutDTO detailCheckOutDTO = new DetailCheckOutDTO();
            detailCheckOutDTO.setCheckInTime(checkInTime);
            detailCheckOutDTO.setPricePerHours(pricePerHours);
            return ResponseEntity.ok(detailCheckOutDTO);
        } catch (DataAccessException e) {
            // Handle DataAccessException
            logger.error("Error during database access in showDetail", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            // Handle other exceptions if necessary
            logger.error("An unexpected error occurred in showDetail", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/fixed/sendCheckinRequest")
    public ResponseEntity<Integer> sendCheckInRequestFixed(@RequestParam("id") int orderDetailId, HttpSession session) {
        // Lấy ra thông tin người dùng hiện tại (employee)
        User user = (User) session.getAttribute("userInfo");

        // Lấy ra thông tin người cần gửi đến (người dùng checkin)
        UserReceiveMessageDTO userReceiveMessageDTO = employeeService.getUserReceiveMessage(orderDetailId);

        int departmentId = userReceiveMessageDTO.getGymDepartmentId();
        DepartmentNotificationDTO departmentNotificationDTO = departmentService.getDepartmentNotificationDtoById(departmentId);

        String departmentName = departmentNotificationDTO.getDepartmentName();
        String departmentLogoUrl = departmentNotificationDTO.getDepartmentLogoUrl();

        int userIdSend = user.getUserId();
        String usernameSend = user.getUserAccount();
        int userIdReceived = userReceiveMessageDTO.getUserId();
        String messageType = "Xác nhận check in";
        String message = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check in ở phòng tập " + departmentName + ". Hãy xác nhận ngay!";

        // Truyền nội dung notification
        Notification notification = Notification.builder()
                .orderDetailId(orderDetailId)
                .userIdSend(userIdSend)
                .userIdReceive(userIdReceived)
                .messageType(messageType)
                .message(message)
                .departmentId(departmentId)
                .departmentName(departmentName)
                .departmentLogoUrl(departmentLogoUrl)
                .timeSend(new Timestamp(System.currentTimeMillis()))
                .build();

        int insertStatus = 0;

        try {
            //insert vào db
            insertStatus = notificationService.insertNotification(notification);

            // Retrieve the generated ID after insertion
            if (insertStatus > 0) {
                webSocketService.notifyUser(userReceiveMessageDTO.getUserId(), notification);
                return ResponseEntity.ok(insertStatus);
            } else {
                logger.error("Notification insertion failed for some reason.");
            }
        } catch (DataAccessException e) {
            logger.error("Error during notification insertion", e);
        }

        return ResponseEntity.ok(insertStatus);
    }

    @GetMapping("/fixed/searchListCheckIn")
    @ResponseBody
    public ResponseEntity<List<CheckInFixedDTO>> searchListFixedCheckIn(HttpSession session,
                                                                        @RequestParam("searchText") String searchText,
                                                                        @RequestParam("searchOption") String searchOption,
                                                                        @RequestParam("departmentId") int departmentId
    ) {
        try {
            if (session == null || !checkValidDepartmentParameter(session, departmentId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
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
        } catch (DataAccessException e) {
            // Handle other exceptions if necessary
            logger.error("An unexpected error occurred in showDetail", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/fixed/searchListCheckOut")
    @ResponseBody
    public ResponseEntity<List<CheckedInFixedDTO>> searchListFixedCheckOut(HttpSession session,
                                                                           @RequestParam("searchText") String searchText,
                                                                           @RequestParam("searchOption") String searchOption,
                                                                           @RequestParam("departmentId") int departmentId
    ) {
        try {
            if (session == null || !checkValidDepartmentParameter(session, departmentId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
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
        } catch (Exception e) {
            // Handle other exceptions if necessary
            logger.error("An unexpected error occurred in showDetail", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/history")
    public String getCheckInHistory(@RequestParam("id") int departmentId, HttpSession session) {
        try {
            if (session == null || !checkValidDepartmentParameter(session, departmentId)) {
                return "error/403";
            }
            return "employee/employee-check-in-history";
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return "error/no-data";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @GetMapping("/history/getListHistory")
    public ResponseEntity<CheckInHistoryPage> getCheckInHistory(@RequestParam("id") int departmentId,
                                                                @RequestParam("plan") String plan,
                                                                @RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "7") int size) {
        try {
            CheckInHistoryPage checkInHistoryPage = null;
            if (plan.equals("flexible")) {
                List<CheckInHistoryFlexible> listFlexible = checkInHistoryService.getListCheckInHistoryFlexibleByDepartmentId(departmentId, page, size);
                int totalListCheckInHistoryFlexible = checkInHistoryService.getTotalListCheckInHistoryFlexibleByDepartmentId(departmentId);
                int totalPages = (int) Math.ceil((double) totalListCheckInHistoryFlexible / size);

                checkInHistoryPage = CheckInHistoryPage.builder()
                        .listFlexible(listFlexible)
                        .totalPages(totalPages)
                        .currentPage(page)
                        .departmentId(departmentId)
                        .build();
            } else if (plan.equals("fixed")) {
                List<CheckInHistoryFixed> listFixed = checkInHistoryService.getListCheckInHistoryFixedByDepartmentId(departmentId, page, size);
                int totalListCheckInHistoryFixed = checkInHistoryService.getTotalListCheckInHistoryFixedByDepartmentId(departmentId);
                int totalPages = (int) Math.ceil((double) totalListCheckInHistoryFixed / size);

                checkInHistoryPage = CheckInHistoryPage.builder()
                        .listFixed(listFixed)
                        .totalPages(totalPages)
                        .currentPage(page)
                        .departmentId(departmentId)
                        .build();
            }
            return ResponseEntity.ok(checkInHistoryPage);
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (DataAccessException e) {
            // Handle other exceptions if necessary
            logger.error("An unexpected error occurred in showDetail", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/history/searchFlex")
    public ResponseEntity<CheckInHistoryPage> searchFlex(
            @RequestParam("id") int departmentId,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "dateFilter", required = false) String dateFilter,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            List<CheckInHistoryFlexible> results = checkInHistoryService.searchListHistoryFlexible(departmentId, username, phoneNumber, dateFilter, page, size);
            int totalRecords = checkInHistoryService.countSearchListHistoryFlexible(departmentId, username, phoneNumber, dateFilter);
            int totalPages = (int) Math.ceil((double) totalRecords / size);

            CheckInHistoryPage checkInHistoryPage = CheckInHistoryPage.builder()
                    .listFlexible(results)
                    .totalPages(totalPages)
                    .currentPage(page)
                    .departmentId(departmentId)
                    .build();
            return ResponseEntity.ok(checkInHistoryPage);
        }
        catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (DataAccessException e) {
            // Handle other exceptions if necessary
            logger.error("An unexpected error occurred in showDetail", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/history/searchFixed")
    public ResponseEntity<List<CheckInHistoryFixed>> searchFixed(
            @RequestParam("id") int departmentId,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "dateFilter", required = false) String dateFilter) {
        try {
            List<CheckInHistoryFixed> listFlexible = checkInHistoryService.searchListHistoryFixed(departmentId, username, phoneNumber, dateFilter);
            return ResponseEntity.ok(listFlexible);
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (DataAccessException e) {
            // Handle other exceptions if necessary
            logger.error("An unexpected error occurred in showDetail", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
