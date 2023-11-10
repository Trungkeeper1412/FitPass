package com.ks.fitpass.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brand-owner")
public class BrandOwnerController {

    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getBOIndex() {
        return "brand-owner/index";
    }

    //Brand Profile (Not a person)
    @GetMapping("/profile")
    public String getBrandProfile() {
        return "brand-owner/gym-brand-update-profile";
    }

    //Change password (for brand owner account)
    @GetMapping("/password")
    public String updateBrandPassword() {
        return "brand-owner/gym-brand-update-password";
    }

    //Department Management
    @GetMapping("/department/list")
    public String getListOfDepartment() {
        return "brand-owner/gym-brand-department-list";
    }

    @GetMapping("/department/details")
    public String getDepartmentDetails() {
        return "brand-owner/gym-brand-department-detail";
    }

    @GetMapping("/department/add")
    public String addDepartment() {
        return "brand-owner/gym-brand-department-add";
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
}
