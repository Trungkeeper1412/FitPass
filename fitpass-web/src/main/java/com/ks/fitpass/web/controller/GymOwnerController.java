package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.service.DepartmentAlbumsService;
import com.ks.fitpass.department.service.DepartmentScheduleService;
import com.ks.fitpass.department.service.DepartmentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gym-owner")
@RequiredArgsConstructor
public class GymOwnerController {

    private final DepartmentScheduleService departmentScheduleService;
    private final DepartmentService departmentService;
    private final DepartmentAlbumsService departmentAlbumsService;
    private final BrandAmenitieService brandAmenitieService;

    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getGOIndex() {
        return "gym-owner/index";
    }

    //Gym Owner Profile (a person) & Change Password
    @GetMapping("/profile")
    public String getGymOwnerProfile() {
        return "gym-owner/gym-department-profile";
    }

    //Employee Management
    @GetMapping("/employee/list")
    public String getListOfEmployee() {
        return "gym-owner/gym-department-employee-list";
    }

    @GetMapping("/employee/details")
    public String getEmployeeDetails() {
        return "gym-owner/gym-department-employee-detail";
    }

    @GetMapping("/employee/add")
    public String addEmployee() {
        return "gym-owner/gym-department-employee-add";
    }

    //Feedback Management
    @GetMapping("/feedback/list")
    public String getListOfFeedback() {
        return "gym-owner/gym-department-feedback";
    }

    //Department Management
    @GetMapping("/department/update-details")
    public String addDepartmentDetails() {
        return "gym-owner/gym-department-update-detail";
    }

    @GetMapping("/department/info")
    public String getDepartmentInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userInfo");
        Department departmentDetails = departmentService.getByUserId(user.getUserId());
        int departmentId = departmentDetails.getDepartmentId();

        List<DepartmentSchedule> departmentSchedules = departmentScheduleService.getAllByDepartmentID(departmentId);
        model.addAttribute("departmentDetails", departmentDetails);
        model.addAttribute("departmentSchedules", departmentSchedules);
        return "gym-owner/gym-department-update-info";
    }

    @GetMapping("/department/amenities")
    public String getDepartmentAmenities(HttpSession session, Model model) {

        User user = (User) session.getAttribute("userInfo");
        Department departmentDetails = departmentService.getByUserId(user.getUserId());
        int brandId = departmentDetails.getBrandId();

        List<BrandAmenitie> brandAmenitie = brandAmenitieService.getAllByBrandIDActivate(brandId);
        model.addAttribute("brandAmenitie", brandAmenitie);
        return "gym-owner/gym-department-update-amenities";
    }

    @GetMapping("/department/features")
    public String getDepartmentFeatures() {
        return "gym-owner/gym-department-update-features";
    }

    @GetMapping("/department/image")
    public String getDepartmentImages(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userInfo");
        Department departmentDetails = departmentService.getByUserId(user.getUserId());
        int departmentId = departmentDetails.getDepartmentId();
        // Get Department Album

        List<DepartmentAlbums> departmentAlbums = departmentAlbumsService.getAllByDepartmentID(departmentId);
        model.addAttribute("departmentDetails", departmentDetails);
        model.addAttribute("departmentAlbums", departmentAlbums);
        return "gym-owner/gym-department-update-image";
    }

    @GetMapping("/department/location")
    public String getDepartmentLocation(HttpSession session, Model model) {

        User user = (User) session.getAttribute("userInfo");
        Department departmentDetails = departmentService.getByUserId(user.getUserId());

        model.addAttribute("departmentDetails", departmentDetails);
        return "gym-owner/gym-department-update-location";
    }

    @GetMapping("/department/gym-plans")
    public String getDepartmentGymPlans() {



        return "gym-owner/gym-department-update-plan";
    }
}
