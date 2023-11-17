package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentListByBrandDTO;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.dto.UserFeedbackOfBrandOwner;
import com.ks.fitpass.department.entity.*;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.enums.PageEnum;
import com.ks.fitpass.web.util.Email;
import com.ks.fitpass.web.util.WebUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/brand-owner")
@RequiredArgsConstructor
public class BrandOwnerController {
    private final BrandService brandService;
    private final DepartmentService departmentService;
    private final GymPlanService gymPlanService;
    private final DepartmentScheduleService departmentScheduleService;

    private final DepartmentAlbumsService departmentAlbumsService;

    private  final DepartmentFeatureService departmentFeatureService;
    private final UserService userService;
    private final WalletService walletService;
    private final Email emailService;
    private final BrandAmenitieService brandAmenitieService;

    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getBOIndex() {
        return "brand-owner/index";
    }

    //Brand Profile (Not a person)
    @GetMapping("/profile")
    public String getBrandProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userInfo");
        Brand brandDetails = brandService.getBrandDetail(user.getUserId());

        model.addAttribute("time", System.currentTimeMillis());
        model.addAttribute("brandDetails", brandDetails);
        return "brand-owner/gym-brand-update-profile";
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<Integer> updateBrandProfile(@RequestBody BrandOwnerProfile brandOwnerProfile,
                                                      HttpSession session) {
        int updateResult = brandService.updateBrandDetail(brandOwnerProfile);
        return ResponseEntity.ok(updateResult);
    }

    //Change password (for brand owner account)
    @GetMapping("/password")
    public String updateBrandPassword() {
        return "brand-owner/gym-brand-update-password";
    }

    //Department Management
    @GetMapping("/department/list")
    public String getListOfDepartment(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        // Get brandId by brandOwnerId
        Brand brand = brandService.getBrandDetail(user.getUserId());
        int brandId = brand.getBrandId();
        List<DepartmentListByBrandDTO> departmentDTOList = departmentService.getAllDepartmentListOfBrand(brandId);

        model.addAttribute("brandId", brandId);
        model.addAttribute("departmentList", departmentDTOList);
        return "brand-owner/gym-brand-department-list";
    }

    @GetMapping("/department/details")
    public String getDepartmentDetails(@RequestParam("id") int departmentId, Model model) {
        Department department = departmentService.getOne(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("page", PageEnum.XXX_FIRST_PAGE.getCode());

        // Get list of gym plans for the department
        List<GymPlanDto> gymPlans = gymPlanService.getGymPlanDetailsByDepartmentId(departmentId);
        model.addAttribute("gymPlans", gymPlans);

        // Get department schedule
        List<DepartmentSchedule> departmentSchedules = departmentScheduleService.getAllByDepartmentID(departmentId);
        model.addAttribute("departmentSchedules", departmentSchedules);

        // Get Department Album
        List<DepartmentAlbums> departmentAlbums = departmentAlbumsService.getAllByDepartmentID(departmentId);
        model.addAttribute("departmentAlbums", departmentAlbums);

        // Calculate the rating statistics
        DepartmentDTO departmentDTO = departmentService.filterDepartmentFeedbacks(departmentId);
        model.addAttribute("departmentFeedbacks", departmentDTO);

        // Get department features
        List<DepartmentFeature> departmentFeatures = departmentFeatureService.getDepartmentFeatures(departmentId);
        model.addAttribute("departmentFeatures", departmentFeatures);

        model.addAttribute("departmentId", departmentId);
        return "brand-owner/gym-brand-department-detail";
    }

    @GetMapping("/department/add")
    public String addDepartment(@RequestParam("id") int brandId, Model model) {
        model.addAttribute("brandId", brandId);
        return "brand-owner/gym-brand-department-add";
    }
    @PostMapping("/department/add")
    public String createDepartment(@RequestParam int brandId, @RequestParam String brandName) {
        departmentService.createDepartmentWithBrandId(brandId, brandName);
        return "redirect:/brand-owner/department/list?id=" + brandId;
    }

    @PostMapping("/department/updateStatus")
    public ResponseEntity<Integer> updateStatusDepartment(@RequestParam int status,@RequestParam int departmentId) {
        int update = departmentService.updateDepartmentStatus(status, departmentId);
        return ResponseEntity.ok(update);
    }

    //Feedback Management
    @GetMapping("/feedback/list")
    public String getListOfDepartmentFeedback(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        // Get brandId by brandOwnerId
        Brand brand = brandService.getBrandDetail(user.getUserId());
        int brandId = brand.getBrandId();
        List<DepartmentDTO> departments = departmentService.getDepartmentByBrandID(brandId);

        model.addAttribute("listDepartment", departments);
        return "brand-owner/gym-brand-feedback-list";
    }

    @GetMapping("/feedback/details")
    public String getDepartmentFeedbackDetails(@RequestParam("id") int departmentId, Model model) {
        Department department = departmentService.getOne(departmentId);
        model.addAttribute("department", department);

        List<UserFeedbackOfBrandOwner> userFeedbackList = departmentService.getAllDepartmentFeedbackOfBrandOwner(departmentId);
        model.addAttribute("userFeedbackList", userFeedbackList);
        return "brand-owner/gym-brand-feedback-list-detail";
    }

    //Service Management
    @GetMapping("/service/list")
    public String getListOfService(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        // Get brandId by brandOwnerId
        Brand brand = brandService.getBrandDetail(user.getUserId());
        int brandId = brand.getBrandId();
        // Get all list service
        List<BrandAmenitie> brandAmenitieList = brandAmenitieService.getAllByBrandID(brandId);
        model.addAttribute("brandAmenitieList", brandAmenitieList);
        return "brand-owner/gym-brand-service-list";
    }

    @GetMapping("/service/details")
    public String getServiceDetails(@RequestParam("id") int id, Model model) {
        BrandAmenitie brandAmenitie = brandAmenitieService.getAmenitieDetail(id);
        model.addAttribute("brandAmenitie", brandAmenitie);
        return "brand-owner/gym-brand-service-detail";
    }

    @PostMapping("/service/update")
    public String updateServiceDetails(@RequestParam String photoUrl, @RequestParam String amenitieName,
                                       @RequestParam String status, @RequestParam String description,
                                       @RequestParam int amenitieId) {
        BrandAmenitie brandAmenitie = new BrandAmenitie();
        brandAmenitie.setPhotoUrl(photoUrl);
        brandAmenitie.setAmenitieName(amenitieName);
        brandAmenitie.setDescription(description);
        brandAmenitie.setStatus(status.equals("true") ? 1 : 0);
        brandAmenitie.setAmenitieId(amenitieId);
        brandAmenitieService.updateBrandAmenitie(brandAmenitie);
        return "redirect:/brand-owner/service/list";
    }

    @GetMapping("/service/add")
    public String addService() {
        return "brand-owner/gym-brand-service-add";
    }

    @PostMapping("/service/add")
    public String createService(@RequestParam String photoUrl, @RequestParam String amenitieName,
                             @RequestParam String description, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        // Get brandId by brandOwnerId
        Brand brand = brandService.getBrandDetail(user.getUserId());
        int brandId = brand.getBrandId();

        BrandAmenitie brandAmenitie = new BrandAmenitie();
        brandAmenitie.setBrandId(brandId);
        brandAmenitie.setAmenitieName(amenitieName);
        brandAmenitie.setPhotoUrl(photoUrl);
        brandAmenitie.setDescription(description);
        brandAmenitie.setStatus(1);
        brandAmenitieService.createBrandAmenitie(brandAmenitie);
        return "redirect:/brand-owner/service/list";
    }

    //Gym Owner Management
    @GetMapping("/gym-owner/list")
    public String getListOfGymOwner(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userInfo");
        // Get brandId by brandOwnerId
        Brand brand = brandService.getBrandDetail(user.getUserId());
        int brandId = brand.getBrandId();

        List<GymOwnerListDTO> gymOwnerList = userService.getAllAccountByBrandId(brandId);
        model.addAttribute("gymOwnerList", gymOwnerList);

        return "brand-owner/gym-brand-owner-list";
    }

    @GetMapping("/gym-owner/details")
    public String getGymOwnerDetails(@RequestParam("id1") int userId, @RequestParam("id2") int userDetailId, Model model,
                                     HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        // Get brandId by brandOwnerId
        Brand brand = brandService.getBrandDetail(user.getUserId());
        int brandId = brand.getBrandId();
        List<DepartmentListByBrandDTO> departmentList = departmentService.getAllDepartmentListOfBrand(brandId);


        UserDetail userDetail = userService.getUserDetailByUserDetailId(userDetailId);

        List<DepartmentListByBrandDTO> filteredList = departmentList.stream()
                .filter(dto -> dto.getUserName() == null || dto.getDepartmentId() == userDetail.getGymDepartmentId())
                .collect(Collectors.toList());

        model.addAttribute("oldDepartmentId", userDetail.getGymDepartmentId());
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("userId", userId);
        model.addAttribute("filteredList", filteredList);
        return "brand-owner/gym-brand-owner-detail";
    }

    @PostMapping("/gym-owner/update")
    public String updateGymOwnerDetails(@RequestParam String firstName, @RequestParam String email,
                                        @RequestParam String address, @RequestParam LocalDate dateOfBirth,
                                        @RequestParam String active, @RequestParam String lastName,
                                        @RequestParam String phone, @RequestParam String idCard,
                                        @RequestParam String gender, @RequestParam("department") int departmentId,
                                        @RequestParam int userId, @RequestParam("oldDepartmentId") int oldDepartmentId,
                                        @RequestParam int userDetailId,
                                        @RequestParam String imageUrl) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserDetailId(userDetailId);
        userDetail.setFirstName(firstName);
        userDetail.setLastName(lastName);
        userDetail.setEmail(email);
        userDetail.setDateOfBirth(dateOfBirth);
        userDetail.setAddress(address);
        userDetail.setPhoneNumber(phone);
        userDetail.setGender(gender);
        userDetail.setImageUrl(imageUrl);
        // Update user detail
        userService.updateUserDetail(userDetail);

        userService.updateUserStatusByUserId(userId, active.equals("true") ? 0 : 1);
        if(departmentId == -1 ) {
            if(oldDepartmentId != 0) {
                departmentService.updateDepartmentGymOwner(oldDepartmentId, 0);
            }
            return "redirect:/brand-owner/gym-owner/list";
        }
        // Nếu trạng thái là 0 thì đá khỏi cơ sở
        if(active.equals("false") && oldDepartmentId != 0) {
            departmentService.updateDepartmentGymOwner(oldDepartmentId, 0);
        }

        // Update user deparment
        if(oldDepartmentId != departmentId) {
            departmentService.updateDepartmentGymOwner(departmentId, userId);
        }
        if(oldDepartmentId != 0 && oldDepartmentId != departmentId) {
            departmentService.updateDepartmentGymOwner(oldDepartmentId, 0);
        }
        return "redirect:/brand-owner/gym-owner/list";
    }

    @GetMapping("/gym-owner/add")
    public String addGymOwner() {
        return "brand-owner/gym-brand-owner-add";
    }

    @PostMapping("/gym-owner/add")
    public String createGymOwner(@RequestParam String firstName, @RequestParam String lastName,
                                 @RequestParam String username, @RequestParam String email,
                                 @RequestParam String phone, @RequestParam String address,
                                 @RequestParam LocalDate dateOfBirth, @RequestParam String idCard,
                                 @RequestParam String gender, @RequestParam String imageUrl, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(firstName);
        userDetail.setLastName(lastName);
        userDetail.setEmail(email);
        userDetail.setPhoneNumber(phone);
        userDetail.setAddress(address);
        userDetail.setDateOfBirth(dateOfBirth);
        userDetail.setImageUrl(imageUrl);
        userDetail.setGender(gender);

        // Insert into User Detail
        userService.insertIntoUserDetail(userDetail);
        // Get last insert user detail id
        int userDetailId = userService.getLastInsertUserDetailId(userDetail);
        // Get brandId by brandOwnerId
        Brand brand = brandService.getBrandDetail(user.getUserId());
        int brandId = brand.getBrandId();
        // Get number of account gym-owner by brand id
        int numberOfAccountCreated = userService.getNumberOfAccountCreatedByBrandId(brandId);
        // Create userName
        String accountName = "fp_" + brandId + "_" + ++numberOfAccountCreated + "_" + username;
        // Create password random
        String randomPassword = WebUtil.generateRandomPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(randomPassword);
        // Create user create time
        Long userCreateTimeLong = System.currentTimeMillis();
        String createTime = userCreateTimeLong.toString();
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
        // Insert into User
        userService.insertIntoUser(newUser);
        // Get last user insert id
        int userInsertId = userService.getLastUserInsertId(newUser);
        // Insert into user_role
        userService.insertIntoUserRole(userInsertId, 2);
        // Insert into wallet
        walletService.insertWallet(userInsertId, 0);

        // Send email to user email
        emailService.send("Test", "Account: " + accountName + ", Password: " + randomPassword, userDetail.getEmail());
        return "redirect:/brand-owner/gym-owner/list";
    }

    //Gym Plans Management
    //Flexible Plans
    @GetMapping("/gym-plans/flexible/list")
    public String getListOfFlexibleGymPlans() {
        // Get List Of flexible gym plan by brand id

        // Send to front
        return "brand-owner/gym-brand-plan-flexible-list";
    }

    @GetMapping("/gym-plans/flexible/details")
    public String getFlexibleGymPlanDetails() {
        return "brand-owner/gym-brand-plan-flexible-detail";
    }

    @PostMapping("/gym-plans/flexible/update")
    public String updateFlexibleGymPlanDetails() {

        return "brand-owner/gym-brand-plan-flexible-detail";
    }

    @GetMapping("/gym-plans/flexible/add")
    public String addFlexibleGymPlan() {
        return "brand-owner/gym-brand-plan-flexible-add";
    }

    @PostMapping("/gym-plans/flexible/add")
    public String createFlexibleGymPlan(@RequestParam String gymPlanName, @RequestParam int pricePerHours,
                                        @RequestParam int planBeforeActive, @RequestParam int planAfterActive,
                                        @RequestParam String description) {
        // Create gym plan flexible

        // Add to db
        return "redirect:/gym-plans/flexible/list";
    }
    //Fixed Plans
    @GetMapping("/gym-plans/fixed/list")
    public String getListOfFixedGymPlans() {
        return "brand-owner/gym-brand-plan-fixed-list";
    }

    @GetMapping("/gym-plans/fixed/details")
    public String getFixedGymPlanDetails() {
        return "brand-owner/gym-brand-plan-fixed-detail";
    }

    @GetMapping("/gym-plans/fixed/add")
    public String addFixedGymPlan() {
        return "brand-owner/gym-brand-plan-fixed-add";
    }

    @GetMapping("/withdrawal/list")
    public String getListOfWithdrawal() { return "brand-owner/gym-brand-withdrawal-list";
    }
}
