package com.ks.fitpass.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gym-owner")
public class GymOwnerController {
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
        return "gym-owner/gym-department-add";
    }

    @GetMapping("/department/info")
    public String getDepartmentInfo() {
        return "gym-owner/gym-department-update-info";
    }

    @GetMapping("/department/amenities")
    public String getDepartmentAmenities() {
        return "gym-owner/gym-department-update-amenities";
    }

    @GetMapping("/department/features")
    public String getDepartmentFeatures() {
        return "gym-owner/gym-department-update-features";
    }

    @GetMapping("/department/image")
    public String getDepartmentImages() {
        return "gym-owner/gym-department-update-image";
    }

    @GetMapping("/department/location")
    public String getDepartmentLocation() {
        return "gym-owner/gym-department-update-location";
    }

    @GetMapping("/department/gym-plans")
    public String getDepartmentGymPlans() {
        return "gym-owner/gym-department-update-plan";
    }
}
