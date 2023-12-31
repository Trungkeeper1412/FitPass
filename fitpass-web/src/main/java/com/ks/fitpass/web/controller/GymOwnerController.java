package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.department.dto.*;
import com.ks.fitpass.department.entity.*;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.dto.BrandGymPlanFixedDTO;
import com.ks.fitpass.gymplan.dto.BrandGymPlanFlexDTO;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.util.Email;
import com.ks.fitpass.web.util.TimeConversionUtil;
import com.ks.fitpass.web.util.WebUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ks.fitpass.gymplan.dto.GymPlanBuyStat;

import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/gym-owner")
@RequiredArgsConstructor
public class GymOwnerController {

    private final DepartmentScheduleService departmentScheduleService;
    private final DepartmentService departmentService;
    private final DepartmentAlbumsService departmentAlbumsService;
    private final BrandAmenitieService brandAmenitieService;
    private final DepartmentAmenitieService departmentAmenitieService;
    private final UserService userService;
    private final GymPlanService gymPlanService;
    private final DepartmentFeatureService departmentFeatureService;
    private final WalletService walletService;
    private final Email emailService;
    private final OrderDetailService orderDetailService;

    private static final Logger logger = LoggerFactory.getLogger(GymOwnerController.class);

    @ModelAttribute
    public void populateGymOwnerInfo(HttpSession session){
        User user = (User) session.getAttribute("userInfo");
        UserDetail userDetail = userService.getUserDetailByUserId(user.getUserId());

        session.setAttribute("userFullNameGO", userDetail.getFirstName().concat(" ").concat(userDetail.getLastName()));
        session.setAttribute("userAvatarGO", userDetail.getImageUrl());
    }

    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getGOIndex(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }

            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());

            int totalGymPlan = gymPlanService.getTotalGymPlanDepartment(departmentDetails.getDepartmentId());

            int totalBuy = orderDetailService.getTotalBuyByDepartmentId(departmentDetails.getDepartmentId());

            double totalRevenue = orderDetailService.getTotalRevenueByDepartmentId(departmentDetails.getDepartmentId());

            int totalNumberRating = departmentService.getTotalNumberRatingByDepartmentId(departmentDetails.getDepartmentId());

            List<GymPlanBuyStat> gymPlanBuyStats = gymPlanService.getGymPlanBuyStatByDepartmentId(departmentDetails.getDepartmentId());

            model.addAttribute("totalGymPlan", totalGymPlan);
            model.addAttribute("totalBuy", totalBuy);
            model.addAttribute("totalRevenue", totalRevenue);
            model.addAttribute("totalNumberRating", totalNumberRating);
            model.addAttribute("departmentDetails", departmentDetails);
            model.addAttribute("gymPlanBuyStats", gymPlanBuyStats);

            return "gym-owner/index";

        } catch (DuplicateKeyException ex) {
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

    //Gym Owner Profile (a person) & Change Password
    @GetMapping("/profile")
    public String getGymOwnerProfile(HttpSession session, Model model) {

        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            return "gym-owner/gym-department-profile";
        } catch (DuplicateKeyException ex) {
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
    @PostMapping("/profile")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model,HttpSession session, RedirectAttributes redirectAttributes) {

        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            User user = (User) session.getAttribute("userInfo");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Kiểm tra mật khẩu hiện tại
            if (!passwordEncoder.matches(currentPassword, user.getUserPassword())) {
                model.addAttribute("currentPasswordError", "Mật khẩu hiện tại không đúng");
                return "gym-owner/gym-department-profile";
            }
            // Kiểm tra mật khẩu mới và xác nhận mật khẩu
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("passwordMismatchError", "Mật khẩu mới và xác nhận mật khẩu không khớp");
                return "gym-owner/gym-department-profile";
            }
            String hashedPassword = passwordEncoder.encode(newPassword);
            // Cập nhật mật khẩu mới
            userService.updatePassword(hashedPassword, user.getUserId());
            // Redirect hoặc hiển thị thông báo thành công
            redirectAttributes.addAttribute("success", "true");
            return "redirect:/gym-owner/profile";
        } catch (DuplicateKeyException ex) {
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

    //Employee Management
    @GetMapping("/employee/list")
    public String getListOfEmployee(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());

            List<GymOwnerListDTO> listOfEmployee = userService.getAllAccountByDepartmentId(departmentDetails.getDepartmentId());

            model.addAttribute("listOfEmployee", listOfEmployee);
            return "gym-owner/gym-department-employee-list";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/employee/details")
    public String getEmployeeDetails(HttpSession session, Model model, @RequestParam("id1") int userId, @RequestParam("id2") int userDetailId) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }

            UserDetail ud = userService.getUserDetailByUserDetailId(userDetailId);

            EmployeUpdateDTO employeUpdateDTO = new EmployeUpdateDTO();
            employeUpdateDTO.setUserDetailId(ud.getUserDetailId());
            employeUpdateDTO.setFirstName(ud.getFirstName());
            employeUpdateDTO.setLastName(ud.getLastName());
            employeUpdateDTO.setEmail(ud.getEmail());
            employeUpdateDTO.setPhone(ud.getPhoneNumber());
            employeUpdateDTO.setAddress(ud.getAddress());
            employeUpdateDTO.setDateOfBirth(ud.getDateOfBirth());
            employeUpdateDTO.setGender(ud.getGender());
            employeUpdateDTO.setImageUrl(ud.getImageUrl());
            employeUpdateDTO.setIdCard(ud.getSecurityId());
            employeUpdateDTO.setUserId(userId);
            employeUpdateDTO.setUserDeleted(ud.isUserDeleted());
            employeUpdateDTO.setOldEmail(ud.getEmail());

            model.addAttribute("employeeInfo", employeUpdateDTO);
            return "gym-owner/gym-department-employee-detail";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/employee/update")
    public String updateEmployeeDetails(HttpSession session, Model model,
                                        @Valid @ModelAttribute("employeeInfo") EmployeUpdateDTO employeeInfo,
                                        BindingResult bindingResult) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (!employeeInfo.getEmail().equals(employeeInfo.getOldEmail())) {
                if (userService.checkEmailExist(employeeInfo.getEmail())) {
                    bindingResult.rejectValue("email", "error.email", "Email đã tồn tại");
                }
            }

            if (bindingResult.hasErrors()) {
                return "/gym-owner/gym-department-employee-detail";
            }
            UserDetail userDetail = new UserDetail();
            userDetail.setUserDetailId(employeeInfo.getUserDetailId());
            userDetail.setFirstName(employeeInfo.getFirstName());
            userDetail.setLastName(employeeInfo.getLastName());
            userDetail.setEmail(employeeInfo.getEmail());
            userDetail.setPhoneNumber(employeeInfo.getPhone());
            userDetail.setAddress(employeeInfo.getAddress());
            userDetail.setDateOfBirth(employeeInfo.getDateOfBirth());
            userDetail.setImageUrl(employeeInfo.getImageUrl());
            userDetail.setGender(employeeInfo.getGender());
            userDetail.setSecurityId(employeeInfo.getIdCard());

            userService.updateUserDetail(userDetail);

            userService.updateUserStatusByUserId(employeeInfo.getUserId(), employeeInfo.isUserDeleted() ? 1 : 0);

            return "redirect:/gym-owner/employee/list";
        } catch (DuplicateKeyException ex) {
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

    @GetMapping("/employee/add")
    public String addEmployee(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            model.addAttribute("employeeInfo", new EmployeeCreateDTO());
            return "gym-owner/gym-department-employee-add";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/employee/add")
    public String createEmployee(HttpSession session, Model model,
                                 @Valid @ModelAttribute("employeeInfo") EmployeeCreateDTO employeeInfo,
                                 BindingResult bindingResult) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (userService.checkEmailExist(employeeInfo.getEmail())) {
                bindingResult.rejectValue("email", "error.email", "Email đã tồn tại");
            }

            if (bindingResult.hasErrors()) {
                return "/gym-owner/gym-department-employee-add";
            }
            User user = (User) session.getAttribute("userInfo");
            UserDetail userDetail = new UserDetail();
            userDetail.setFirstName(employeeInfo.getFirstName());
            userDetail.setLastName(employeeInfo.getLastName());
            userDetail.setEmail(employeeInfo.getEmail());
            userDetail.setPhoneNumber(employeeInfo.getPhone());
            userDetail.setAddress(employeeInfo.getAddress());
            userDetail.setDateOfBirth(employeeInfo.getDateOfBirth());
            userDetail.setImageUrl(employeeInfo.getImageUrl());
            userDetail.setSecurityId(employeeInfo.getIdCard());
            userDetail.setGender(employeeInfo.getGender());

            // Insert into User Detail
            userService.insertIntoUserDetail(userDetail);
            // Get last insert user detail id
            int userDetailId = userService.getLastInsertUserDetailId(userDetail);
            // Get deparmentId by userId
            Department departmentDetails = departmentService.getByUserId(user.getUserId());
            String departmentName = departmentDetails.getDepartmentName();
            // Get number of account gym-owner by brand id
            int numberOfAccountCreated = userService.getNumberOfAccountCreatedByDepartmentId(departmentDetails.getDepartmentId());
            // Create userName
            String accountName = "fp_" + departmentName.replaceAll("\\s+", "") + "_" + ++numberOfAccountCreated + "_" + employeeInfo.getUsername();
            // Create password random
            String randomPassword = WebUtil.generateRandomPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(randomPassword);
            // Create user create time
            long userCreateTimeLong = System.currentTimeMillis();
            String createTime = Long.toString(userCreateTimeLong);
            // Create user_deleted = 0;
            boolean userDelete = false;
            // Create new User
            User newUser = new User();
            newUser.setUserAccount(accountName);
            newUser.setUserCreateTime(userCreateTimeLong);
            newUser.setUserPassword(hashedPassword);
            newUser.setUserDeleted(userDelete);
            newUser.setUserDetailId(userDetailId);
            newUser.setCreatedBy(user.getUserId());
            newUser.setInDepartmentId(departmentDetails.getDepartmentId());
            // Insert into User
            userService.createEmployee(newUser);
            // Get last user insert id
            int userInsertId = userService.getLastUserInsertId(newUser);
            // Insert into user_role
            userService.insertIntoUserRole(userInsertId, 3);
            // Insert into wallet
            walletService.insertWallet(userInsertId, 0);

            // Send email to user email
            // Todo: trang tri trang html
            emailService.send("Test", "Account: " + accountName + ", Password: " + randomPassword,
                    userDetail.getEmail());

            return "redirect:/gym-owner/employee/list";
        } catch (DuplicateKeyException ex) {
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

    //Feedback Management
    @GetMapping("/feedback/list")
    public String getListOfFeedback(HttpSession session, Model model) {
        try{
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        if (isFirstTime) {
            return "redirect:/gym-owner/department/update-details";
        }
        User user = (User) session.getAttribute("userInfo");
        Department departmentDetails = departmentService.getByUserId(user.getUserId());

        List<UserFeedbackOfBrandOwner> userFeedbackList = departmentService.getAllDepartmentFeedbackOfBrandOwner(departmentDetails.getDepartmentId());
        model.addAttribute("userFeedbackList", userFeedbackList);
        model.addAttribute("departmentDetails", departmentDetails);
        return "gym-owner/gym-department-feedback";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    //Department Management
    @GetMapping("/department/update-details")
    public String addDepartmentDetails(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (!isFirstTime) {
                return "redirect:/gym-owner/department/info";
            }

            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());

            // Get List Of fixed gym plan by brand id
            List<BrandGymPlanFixedDTO> listFixedGymPlan = gymPlanService.getAllGymPlanFixedByBrandIdActive(departmentDetails.getBrandId());
            // Get List Of flexible gym plan by brand id
            List<BrandGymPlanFlexDTO> listFlexGymPlan = gymPlanService.getAllGymPlanFlexByBrandIdActive(departmentDetails.getBrandId());
            // Get list brand amenitie
            List<BrandAmenitie> brandAmenitie = brandAmenitieService.getAllByBrandIDActivate(departmentDetails.getBrandId());

            // Get list feature
            List<Feature> features = departmentFeatureService.getAllFeatures();

            model.addAttribute("features", features);
            model.addAttribute("brandAmenitie", brandAmenitie);
            model.addAttribute("listFlexGymPlan", listFlexGymPlan);
            model.addAttribute("listFixedGymPlan", listFixedGymPlan);
            model.addAttribute("departmentName", departmentDetails.getDepartmentName());
            model.addAttribute("departmentId", departmentDetails.getDepartmentId());
            model.addAttribute("departmentInfo", new GODepartmentUpdateDetail());

            return "gym-owner/gym-department-update-detail";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/department/update-details")
    public ResponseEntity<String> createDepartmentDetails(HttpSession session, Model model,
                                                          @Valid @RequestBody GODepartmentUpdateDetail departmentInfo,
                                                          BindingResult bindingResult) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (bindingResult.hasErrors()) {
                // Tạo một Map chứa thông tin về lỗi
                Map<String, Object> errors = new HashMap<>();
                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }

                StringBuilder errorMsg = new StringBuilder();

                for (String key : errors.keySet()) {
                    errorMsg.append("Lỗi ở: ").append(key).append(", lí do: ").append(errors.get(key)).append("\n");
                }
                return ResponseEntity.badRequest().body(errorMsg.toString());
            }

            // Update department information
            Department department = Department.builder()
                    .departmentId(departmentInfo.getDepartmentId())
                    .departmentAddress(departmentInfo.getDepartmentAddress())
                    .departmentContactNumber(departmentInfo.getDepartmentContactNumber())
                    .departmentDescription(departmentInfo.getDepartmentDescription())
                    .capacity(departmentInfo.getCapacity())
                    .area(departmentInfo.getArea())
                    .city(departmentInfo.getCity())
                    .latitude(departmentInfo.getLatitude())
                    .longitude(departmentInfo.getLongitude())
                    .build();
            int numRowUpdateDepartment = departmentService.updateGymOwnerDepartmentInfoDetails(department);

            // Parse time into string
            String mondayOpenTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getMondayOpenTime());
            String mondayCloseTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getMondayCloseTime());
            String tuesdayOpenTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getTuesdayOpenTime());
            String tuesdayCloseTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getTuesdayCloseTime());
            String wednesdayOpenTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getWednesdayOpenTime());
            String wednesdayCloseTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getWednesdayCloseTime());
            String thursdayOpenTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getThursdayOpenTime());
            String thursdayCloseTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getThursdayCloseTime());
            String fridayOpenTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getFridayOpenTime());
            String fridayCloseTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getFridayCloseTime());
            String saturdayOpenTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getSaturdayOpenTime());
            String saturdayCloseTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getSaturdayCloseTime());
            String sundayOpenTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getSundayOpenTime());
            String sundayCloseTime = TimeConversionUtil.convertTo12HourFormat(departmentInfo.getSundayCloseTime());

            Map<String, String> dayToTimeMap = new LinkedHashMap<>();
            dayToTimeMap.put("Thứ 2", mondayOpenTime + "-" + mondayCloseTime);
            dayToTimeMap.put("Thứ 3", tuesdayOpenTime + "-" + tuesdayCloseTime);
            dayToTimeMap.put("Thứ 4", wednesdayOpenTime + "-" + wednesdayCloseTime);
            dayToTimeMap.put("Thứ 5", thursdayOpenTime + "-" + thursdayCloseTime);
            dayToTimeMap.put("Thứ 6", fridayOpenTime + "-" + fridayCloseTime);
            dayToTimeMap.put("Thứ 7", saturdayOpenTime + "-" + saturdayCloseTime);
            dayToTimeMap.put("Chủ Nhật", sundayOpenTime + "-" + sundayCloseTime);

            // Insert into department schedule
            int[] numRowInsertScheduleResult = departmentScheduleService.addDepartmentSchedule(dayToTimeMap, departmentInfo.getDepartmentId());
            int numRowInsertSchedule = Arrays.stream(numRowInsertScheduleResult).sum();

            // Insert into gym plan department
            List<Integer> gymPlanFlexList = WebUtil.parseIntegerList(departmentInfo.getListSelectedFlexGymPlanId());
            List<Integer> gymPlanFixedList = WebUtil.parseIntegerList(departmentInfo.getListSelectedFixedGymPlanId());
            int departmentId = departmentInfo.getDepartmentId();
            List<Integer> gymPlan = Stream.concat(gymPlanFixedList.stream(), gymPlanFlexList.stream()).collect(Collectors.toList());
            int[] resultGymPlan = gymPlanService.insertGymPlanDepartment(departmentId, gymPlan);
            int rowGymPlan = Arrays.stream(resultGymPlan).sum();

            // Insert into department amenitie
            List<Integer> deparmentAmenitie = WebUtil.parseIntegerList(departmentInfo.getListSelectedAmenitiesId());
            int[] resultAmenitie = departmentAmenitieService.insertDepartmentAmenitie(departmentId, deparmentAmenitie);
            int rowAmenitie = Arrays.stream(resultAmenitie).sum();

            // Insert into department feature
            List<Integer> departmentFeature = WebUtil.parseIntegerList(departmentInfo.getListSelectedFeaturesId());
            int[] resultFeature = departmentFeatureService.insertDepartmentFeature(departmentId, departmentFeature);
            int rowFeature = Arrays.stream(resultFeature).sum();

            // Update first time login status
            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());
            int rowfirstTimeLogin = departmentService.updateFirstTimeDepartmentCreated(departmentDetails.getDepartmentId());

            // Check if any row is 0
            if (numRowInsertSchedule == 0 || numRowUpdateDepartment == 0 || rowFeature == 0 || rowfirstTimeLogin == 0
                    || rowAmenitie == 0 || rowGymPlan == 0) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cập nhật thất bại");
            }

            return ResponseEntity.ok("Cập nhật thành công");
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/department/info")
    public String getDepartmentInfo(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());
            int departmentId = departmentDetails.getDepartmentId();

            List<DepartmentSchedule> departmentSchedules = departmentScheduleService.getAllByDepartmentID(departmentId);
//        model.addAttribute("departmentDetails", departmentDetails);
            model.addAttribute("departmentSchedules", departmentSchedules);

            UpdateGymOwnerDepartmentInfo updateGymOwnerDepartmentInfo = new UpdateGymOwnerDepartmentInfo();
            updateGymOwnerDepartmentInfo.setDepartmentId(departmentDetails.getDepartmentId());
            updateGymOwnerDepartmentInfo.setDepartmentName(departmentDetails.getDepartmentName());
            updateGymOwnerDepartmentInfo.setDepartmentAddress(departmentDetails.getDepartmentAddress());
            updateGymOwnerDepartmentInfo.setDepartmentContactNumber(departmentDetails.getDepartmentContactNumber());
            updateGymOwnerDepartmentInfo.setDepartmentDescription(departmentDetails.getDepartmentDescription());
            updateGymOwnerDepartmentInfo.setCapacity(departmentDetails.getCapacity());
            updateGymOwnerDepartmentInfo.setArea(departmentDetails.getArea());
            updateGymOwnerDepartmentInfo.setCity(departmentDetails.getCity());

            model.addAttribute("updateGymOwnerDepartmentInfo", updateGymOwnerDepartmentInfo);
            return "gym-owner/gym-department-update-info";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/department/info")
    public String updateDepartmentInfo(@Valid @ModelAttribute UpdateGymOwnerDepartmentInfo updateGymOwnerDepartmentInfo,
                                       BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (bindingResult.hasErrors()) {
                logger.error("Binding errors found:");
                bindingResult.getAllErrors().forEach(error -> logger.error(error.toString()));

                User user = (User) session.getAttribute("userInfo");
                Department departmentDetails = departmentService.getByUserId(user.getUserId());
                int departmentId = departmentDetails.getDepartmentId();
                List<DepartmentSchedule> departmentSchedules = departmentScheduleService.getAllByDepartmentID(departmentId);
                model.addAttribute("departmentSchedules", departmentSchedules);
                TimeUnit.SECONDS.sleep(2);
                return "/gym-owner/gym-department-update-info";
            }

            Department department = Department.builder()
                    .departmentId(updateGymOwnerDepartmentInfo.getDepartmentId())
                    .departmentAddress(updateGymOwnerDepartmentInfo.getDepartmentAddress())
                    .departmentContactNumber(updateGymOwnerDepartmentInfo.getDepartmentContactNumber())
                    .departmentDescription(updateGymOwnerDepartmentInfo.getDepartmentDescription())
                    .capacity(updateGymOwnerDepartmentInfo.getCapacity())
                    .area(updateGymOwnerDepartmentInfo.getArea())
                    .city(updateGymOwnerDepartmentInfo.getCity())
                    .build();
            departmentService.updateGymOwnerDepartmentInfo(department);

            // Update giờ mở cửa
            String mondayOpenTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getMondayOpenTime());
            String mondayCloseTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getMondayCloseTime());
            String tuesdayOpenTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getTuesdayOpenTime());
            String tuesdayCloseTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getTuesdayCloseTime());
            String wednesdayOpenTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getWednesdayOpenTime());
            String wednesdayCloseTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getWednesdayCloseTime());
            String thursdayOpenTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getThursdayOpenTime());
            String thursdayCloseTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getThursdayCloseTime());
            String fridayOpenTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getFridayOpenTime());
            String fridayCloseTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getFridayCloseTime());
            String saturdayOpenTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getSaturdayOpenTime());
            String saturdayCloseTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getSaturdayCloseTime());
            String sundayOpenTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getSundayOpenTime());
            String sundayCloseTime = TimeConversionUtil.convertTo12HourFormat(updateGymOwnerDepartmentInfo.getSundayCloseTime());

            Map<String, String> dayToTimeMap = new LinkedHashMap<>();
            dayToTimeMap.put("Thứ 2", mondayOpenTime + "-" + mondayCloseTime);
            dayToTimeMap.put("Thứ 3", tuesdayOpenTime + "-" + tuesdayCloseTime);
            dayToTimeMap.put("Thứ 4", wednesdayOpenTime + "-" + wednesdayCloseTime);
            dayToTimeMap.put("Thứ 5", thursdayOpenTime + "-" + thursdayCloseTime);
            dayToTimeMap.put("Thứ 6", fridayOpenTime + "-" + fridayCloseTime);
            dayToTimeMap.put("Thứ 7", saturdayOpenTime + "-" + saturdayCloseTime);
            dayToTimeMap.put("Chủ Nhật", sundayOpenTime + "-" + sundayCloseTime);

            // Delete all department schedule to update
            int numRowDeleteSchedule = departmentScheduleService.deleteAllDepartmentSchedule(updateGymOwnerDepartmentInfo.getDepartmentId());

            // Insert into department schedule
            int[] numRowInsertScheduleResult = departmentScheduleService.addDepartmentSchedule(dayToTimeMap, updateGymOwnerDepartmentInfo.getDepartmentId());
            int numRowInsertSchedule = Arrays.stream(numRowInsertScheduleResult).sum();

            TimeUnit.SECONDS.sleep(2);
            redirectAttributes.addAttribute("success", "true");
            return "redirect:/gym-owner/department/info";
        } catch (InterruptedException ex) {
            // Handle InterruptedException
            logger.error("InterruptedException occurred", ex);
            return "error/error";
        } catch (DuplicateKeyException ex) {
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

    @GetMapping("/department/amenities")
    public String getDepartmentAmenities(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());
            int brandId = departmentDetails.getBrandId();

            List<BrandAmenitie> brandAmenitie = brandAmenitieService.getAllByBrandIDActivate(brandId);
            List<DepartmentAmenitie> departmentAmenities = departmentAmenitieService.getAllAmenitieOfDepartment(departmentDetails.getDepartmentId());
            List<Integer> selectedId = departmentAmenities.stream()
                    .map(DepartmentAmenitie::getAmenitieId)
                    .filter(amenitieId -> brandAmenitie.stream()
                            .anyMatch(brand -> brand.getAmenitieId() == amenitieId))
                    .toList();
            String result = selectedId.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            model.addAttribute("selectedId", result);
            model.addAttribute("brandAmenitie", brandAmenitie);
            model.addAttribute("departmentId", departmentDetails.getDepartmentId());
            return "gym-owner/gym-department-update-amenities";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/department/amenities")
    public String updateDepartmentAmenities(HttpSession session, Model model,
                                            @RequestParam("selectedAmenitieId") List<Integer> selectedAmenitieId,
                                            @RequestParam("departmentId") int departmentId) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }


            int rowDelete = departmentAmenitieService.deleteAllDepartmentAmenitie(departmentId);
            int[] rowInsertResult = departmentAmenitieService.insertDepartmentAmenitie(departmentId, selectedAmenitieId);
            int rowInsert = Arrays.stream(rowInsertResult).sum();

            return "redirect:/gym-owner/department/amenities";
        } catch (DuplicateKeyException ex) {
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

    @GetMapping("/department/features")
    public String getDepartmentFeatures(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());
            int departmentId = departmentDetails.getDepartmentId();

            List<Feature> allFeatures = departmentFeatureService.getAllFeatures();
            List<DepartmentFeature> departmentFeatures = departmentFeatureService.getDepartmentFeatures(departmentId);

            List<Integer> selectedId = departmentFeatures.stream()
                    .filter(departmentFeature -> allFeatures.stream()
                            .anyMatch(feature -> feature.getFeatureID() == departmentFeature.getFeature().getFeatureID()))
                    .map(e -> e.getFeature().getFeatureID())
                    .toList();

            String result = selectedId.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));

            model.addAttribute("selectedId", result);
            model.addAttribute("departmentId", departmentId);
            model.addAttribute("allFeatures", allFeatures);
            return "gym-owner/gym-department-update-features";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/department/features")
    public String updateDepartmentFeatures(HttpSession session, Model model,
                                           @RequestParam("selectedFeatureId") List<Integer> selectedId, @RequestParam int departmentId) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }

            int deleteRow = departmentFeatureService.deleteAllDepartmentFeatures(departmentId);
            int[] insertRowResult = departmentFeatureService.insertDepartmentFeature(departmentId, selectedId);
            int insertRow = Arrays.stream(insertRowResult).sum();
            return "redirect:/gym-owner/department/features";
        } catch (DuplicateKeyException ex) {
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

    @GetMapping("/department/image")
    public String getDepartmentImages(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());
            int departmentId = departmentDetails.getDepartmentId();
            // Get Department Album

            List<DepartmentAlbums> departmentAlbums = departmentAlbumsService.getAllByDepartmentID(departmentId);
            model.addAttribute("departmentDetails", departmentDetails);
            model.addAttribute("departmentAlbums", departmentAlbums);
            return "gym-owner/gym-department-update-image";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/department/image")
    public ResponseEntity<?> updateDepartmentImages(@RequestParam String imageLogoUrl, @RequestParam String imageThumbnailUrl,
                                                    @RequestParam String imageWallpaperUrl,
                                                    @RequestParam String listAlbumUrl, HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create("/gym-owner/department/update-details"))
                        .build();
            }

            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());

            departmentService.updateDepartmentImage(departmentDetails.getDepartmentId(), imageLogoUrl, imageThumbnailUrl, imageWallpaperUrl);
            departmentAlbumsService.deleteAllAlbumsByDepartmentID(departmentDetails.getDepartmentId());

            String[] listAlbum = listAlbumUrl.split(",");
            List<DepartmentAlbums> departmentAlbumsList = new ArrayList<>();
            Arrays.stream(listAlbum).forEach(albumUrl -> {
                DepartmentAlbums departmentAlbums = new DepartmentAlbums();
                departmentAlbums.setDepartmentId(departmentDetails.getDepartmentId());
                departmentAlbums.setPhotoUrl(albumUrl);
                departmentAlbumsList.add(departmentAlbums);
            });
            departmentAlbumsService.addDepartmentAlbums(departmentAlbumsList);

            // Return a successful response entity
            return ResponseEntity.ok("Cập nhật hình ảnh thành công");

        } catch (DuplicateKeyException ex) {
            logger.error("DuplicateKeyException occurred", ex);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Lỗi trùng khóa - hình ảnh có thể đã tồn tại.");
        } catch (EmptyResultDataAccessException ex) {
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy dữ liệu.");
        } catch (IncorrectResultSizeDataAccessException ex) {
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kích thước ảnh không chính xác.");
        } catch (DataAccessException ex) {
            logger.error("DataAccessException occurred", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi truy cập dữ liệu.");
        }
    }

    @GetMapping("/department/location")
    public String getDepartmentLocation(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }
            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());

            UpdateGymOwnerDepartmentLocation updateGymOwnerDepartmentLocation = new UpdateGymOwnerDepartmentLocation();

            updateGymOwnerDepartmentLocation.setLatitude(String.valueOf(departmentDetails.getLatitude()));
            updateGymOwnerDepartmentLocation.setLongitude(String.valueOf(departmentDetails.getLongitude()));

            model.addAttribute("updateGymOwnerDepartmentLocation", updateGymOwnerDepartmentLocation);
            return "gym-owner/gym-department-update-location";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/department/location")
    public String updateDepartmentLocation(HttpSession session, Model model,
                                           @Valid @ModelAttribute UpdateGymOwnerDepartmentLocation updateGymOwnerDepartmentLocation,
                                           BindingResult bindingResult) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }

            if (bindingResult.hasErrors()) {
                // Nếu có lỗi validation, xử lý ở đây nếu cần
                return "gym-owner/gym-department-update-location";
            }
            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());

            departmentService.updateLongitudeLatitude(departmentDetails.getDepartmentId(),
                    Double.parseDouble(updateGymOwnerDepartmentLocation.getLongitude()),
                    Double.parseDouble(updateGymOwnerDepartmentLocation.getLatitude()));

            return "redirect:/gym-owner/department/location";
        } catch (DuplicateKeyException ex) {
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

    @GetMapping("/department/gym-plans")
    public String getDepartmentGymPlans(HttpSession session, Model model) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }

            User user = (User) session.getAttribute("userInfo");
            Department departmentDetails = departmentService.getByUserId(user.getUserId());
            int departmentId = departmentDetails.getDepartmentId();
            int brandId = departmentDetails.getBrandId();

            List<BrandGymPlanFixedDTO> listFixedGymPlan = gymPlanService.getAllGymPlanFixedByBrandIdActive(brandId);
            List<BrandGymPlanFlexDTO> listFlexGymPlan = gymPlanService.getAllGymPlanFlexByBrandIdActive(brandId);

            List<GymPlanDepartmentNameDto> listFixedGymPlanSelected = gymPlanService.getGymPlanDepartmentFixedByDepartmentId(departmentId);
            List<GymPlanDepartmentNameDto> listFlexGymPlanSelected = gymPlanService.getGymPlanDepartmentFlexByDepartmentId(departmentId);

            List<Integer> selectedFixedId = listFixedGymPlanSelected.stream()
                    .map(GymPlanDepartmentNameDto::getGymPlanId)
                    .filter(gymPlanId -> listFixedGymPlan.stream()
                            .anyMatch(brandGymPlanFixedDTO -> brandGymPlanFixedDTO.getGymPlanId() == gymPlanId))
                    .toList();

            List<Integer> selectedFlexId = listFlexGymPlanSelected.stream()
                    .map(GymPlanDepartmentNameDto::getGymPlanId)
                    .filter(gymPlanId -> listFlexGymPlan.stream()
                            .anyMatch(brandGymPlanFlexDTO -> brandGymPlanFlexDTO.getGymPlanId() == gymPlanId))
                    .toList();

            // Parse to string ['1', '2', '3']
            String selectedFixedIdString = selectedFixedId.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            String selectedFlexIdString = selectedFlexId.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));

            model.addAttribute("selectedFixedId", selectedFixedIdString);
            model.addAttribute("selectedFlexId", selectedFlexIdString);
            model.addAttribute("listFixedGymPlan", listFixedGymPlan);
            model.addAttribute("listFlexGymPlan", listFlexGymPlan);
            model.addAttribute("departmentId", departmentId);

            return "gym-owner/gym-department-update-plan";
        } catch (DuplicateKeyException ex) {
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

    @PostMapping("/department/gym-plans")
    public String updateDepartmentGymPlans(HttpSession session, Model model,
                                           @RequestParam("selectedFixedId") List<Integer> selectedFixedGymPlanId,
                                           @RequestParam("selectedFlexId") List<Integer> selectedFlexGymPlanId,
                                           @RequestParam("departmentId") int departmentId) {
        boolean isFirstTime = checkAndSetIsFirstTime(session, model);
        try {
            if (isFirstTime) {
                return "redirect:/gym-owner/department/update-details";
            }

            int deleteRow = gymPlanService.deleteAllGymPlanByDepartmentId(departmentId);
            int[] insertRowResult = gymPlanService.insertGymPlanDepartment(departmentId,
                    Stream.concat(selectedFixedGymPlanId.stream(), selectedFlexGymPlanId.stream()).distinct().collect(Collectors.toList()));
            int insertRow = Arrays.stream(insertRowResult).sum();

            return "redirect:/gym-owner/department/gym-plans";
        } catch (DuplicateKeyException ex) {
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

    public boolean checkAndSetIsFirstTime(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userInfo");
        boolean isFirstTimeInSession = session.getAttribute("isFirstTime") != null
                ? (boolean) session.getAttribute("isFirstTime")
                : false;
        Department departmentDetails = departmentService.getByUserId(user.getUserId());
        int departmentId = departmentDetails.getDepartmentId();

        boolean isFirstTimeInDB = departmentService.checkFirstTimeDepartmentCreated(departmentId);

        if (isFirstTimeInDB != isFirstTimeInSession) {
            // Giá trị isFirstTime trong DB khác với giá trị trong session
            // Cập nhật giá trị trong session để phản ánh giá trị mới từ DB
            session.setAttribute("isFirstTime", isFirstTimeInDB);
        }
        model.addAttribute("isFirstTime", isFirstTimeInDB);
        return isFirstTimeInDB;
    }
}
