package com.ks.fitpass.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/become-a-partner")
public class BORegisterController {
    @GetMapping("")
    public String getBrandOwnerRegister(){
        return "gym-brand-registration";
    }
    @GetMapping("/successful")
    public String getRegistrationSuccessful() {
        return "gym-brand-registration-successful";
    }

    @GetMapping("/famous-brands")
    public String getFamousBrands() {
        return "famous-brands";
    }
}
