package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.department.dto.DepartmentAmenitie;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentListByBrandDTO;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.web.enums.PageEnum;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/brand-owner")
@RequiredArgsConstructor
public class BrandOwnerController {
    private final UserRepository userRepository;
    private final BrandService brandService;
    private final DepartmentService departmentService;
    private final GymPlanService gymPlanService;
    private final DepartmentScheduleService departmentScheduleService;
    private final DepartmentAlbumsService departmentAlbumsService;
    private final DepartmentAmenitieService departmentAmenitieService;
    private  final DepartmentFeatureService departmentFeatureService;

    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getBOIndex(Principal principal, HttpSession session) {
        com.ks.fitpass.core.entity.User user = userRepository.findByAccount(principal.getName());
        session.setAttribute("userInfo", user);
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

        List<DepartmentAmenitie> departmentAmenitieList = departmentAmenitieService.getAllAmenitieOfDepartment(departmentId);
        model.addAttribute("departmentAmenitieList", departmentAmenitieList);

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
    public String createDepartment(@RequestParam int brandId,
                                   @RequestParam String brandName,
                                   Model model) {
        if (brandName == null || brandName.isEmpty()) {
            String errorMessage = "Brand Name can't be empty";
            model.addAttribute("errorMessage", errorMessage);
            return "brand-owner/gym-brand-department-add";
        }
        departmentService.createDepartmentWithBrandId(brandId, brandName);
        return "redirect:/brand-owner/department/list?id=" + brandId;
    }
}
