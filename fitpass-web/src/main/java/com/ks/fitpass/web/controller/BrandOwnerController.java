package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentListByBrandDTO;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.*;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.web.enums.PageEnum;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String getListOfDepartment(@RequestParam("id") int brandId, Model model) {
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
    public String getListOfDepartmentFeedback() {
        return "brand-owner/gym-brand-feedback-list";
    }

    @GetMapping("/feedback/details")
    public String getDepartmentFeedbackDetails() {
        return "brand-owner/gym-brand-feedback-list-detail";
    }

    //Service Management
    @GetMapping("/service/list")
    public String getListOfService() {
        return "brand-owner/gym-brand-service-list";
    }

    @GetMapping("/service/details")
    public String getServiceDetails() {
        return "brand-owner/gym-brand-service-detail";
    }

    @GetMapping("/service/add")
    public String addService() {
        return "brand-owner/gym-brand-service-add";
    }

    //Gym Owner Management
    @GetMapping("/gym-owner/list")
    public String getListOfGymOwner() {
        return "brand-owner/gym-brand-owner-list";
    }

    @GetMapping("/gym-owner/details")
    public String getGymOwnerDetails() {
        return "brand-owner/gym-brand-owner-detail";
    }

    @GetMapping("/gym-owner/add")
    public String addGymOwner() {
        return "brand-owner/gym-brand-owner-add";
    }

    //Gym Plans Management
    //Flexible Plans
    @GetMapping("/gym-plans/flexible/list")
    public String getListOfFlexibleGymPlans() {
        return "brand-owner/gym-brand-plan-flexible-list";
    }

    @GetMapping("/gym-plans/flexible/details")
    public String getFlexibleGymPlanDetails() {
        return "brand-owner/gym-brand-plan-flexible-detail";
    }

    @GetMapping("/gym-plans/flexible/add")
    public String addFlexibleGymPlan() {
        return "brand-owner/gym-brand-plan-flexible-add";
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
    public String getListOfTransaction() {
        return "brand-owner/gym-brand-withdrawal-list";
    }
}
