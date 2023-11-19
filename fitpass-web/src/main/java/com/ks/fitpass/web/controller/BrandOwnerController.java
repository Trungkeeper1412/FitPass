package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.dto.ServiceCreateDTO;
import com.ks.fitpass.brand.dto.ServiceUpdateDTO;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.department.dto.*;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.web.enums.PageEnum;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private final DepartmentFeatureService departmentFeatureService;
    private final BrandAmenitieService brandAmenitieService;


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

    @PostMapping("/department/updateStatus")
    public ResponseEntity<Integer> updateStatusDepartment(@RequestParam int status,@RequestParam int departmentId) {
        int update = departmentService.updateDepartmentStatus(status, departmentId);
        return ResponseEntity.ok(update);
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

    //Feedback Management
    @GetMapping("/feedback/list")
    public String getListOfDepartmentFeedback(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        // Get brandId by brandOwnerId
        Brand brand = brandService.getBrandDetail(user.getUserId());
        int brandId = brand.getBrandId();
        List<ListBrandDepartmentFeedback> departments = departmentService.getDepartmentFeedbackOfBrandOwner(brandId);

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
        model.addAttribute("brandAmenitiesList", brandAmenitieList);
        return "brand-owner/gym-brand-service-list";
    }

    @GetMapping("/service/details")
    public String getServiceDetails(@RequestParam("id") int id, Model model) {
        BrandAmenitie brandAmenitie = brandAmenitieService.getAmenitieDetail(id);
        model.addAttribute("brandAmenitie", brandAmenitie);
        return "brand-owner/gym-brand-service-detail";
    }

    @PostMapping("/service/update")
    public String updateServiceDetails(@Valid @ModelAttribute("brandAmenitie") ServiceUpdateDTO serviceUpdateDTO,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "brand-owner/gym-brand-service-detail"; // Trả về trang form và hiển thị thông báo lỗi
        }

        BrandAmenitie brandAmenitie = new BrandAmenitie();
        brandAmenitie.setPhotoUrl(serviceUpdateDTO.getPhotoUrl());
        brandAmenitie.setAmenitieName(serviceUpdateDTO.getAmenitieName());
        brandAmenitie.setDescription(serviceUpdateDTO.getDescription());
        brandAmenitie.setStatus(serviceUpdateDTO.getStatus() ? 1 : 0);
        brandAmenitie.setAmenitieId(serviceUpdateDTO.getAmenitieId());

        // Thực hiện cập nhật dịch vụ trong CSDL
        brandAmenitieService.updateBrandAmenitie(brandAmenitie);
        return "redirect:/brand-owner/service/list";
    }

    @GetMapping("/service/add")
    public String addService(@ModelAttribute("createService") ServiceCreateDTO serviceCreateDTO) {
        return "brand-owner/gym-brand-service-add";
    }

   

}
