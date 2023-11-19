package com.ks.fitpass.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin")
public class AdminController {
    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getAdminIndex() {
        return "admin/index";
    }

    //Banner
    @GetMapping("/banner")
    public String getListBanner() {
        return "admin/admin-banner";
    }

    @GetMapping("/feature")
    public String getFeature() {
        return "admin/admin-feature";
    }

    @GetMapping("/brand/list")
    public String getBrandList() {
        return "admin/admin-brand-list";
    }

    @GetMapping("/account/brand")
    public String getAccountBrandList() {
        return "admin/admin-account-brand";
    }

    @GetMapping("/account/brand/add")
    public String getAccountBrandAdd() {
        return "admin/admin-account-brand-add";
    }

    @GetMapping("/account/user")
    public String getAccountUserList() {
        return "admin/admin-account-user";
    }

    @GetMapping("/withdrawal")
    public String getWithdrawalList() {
        return "admin/admin-withdrawal-list";
    }
}
