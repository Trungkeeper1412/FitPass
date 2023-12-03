package com.ks.fitpass.web.controller;

import com.ks.fitpass.become_a_partner.dto.BecomePartnerForm;
import com.ks.fitpass.become_a_partner.dto.BecomePartnerRequest;
import com.ks.fitpass.become_a_partner.service.BecomePartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/become-a-partner")
@RequiredArgsConstructor
public class BORegisterController {
    private final BecomePartnerService becomePartnerService;

    @GetMapping("")
    public String getBrandOwnerRegister(Model model){
        model.addAttribute("becomePartnerForm", new BecomePartnerForm());
        return "gym-brand-registration";
    }

    @PostMapping("/submitForm")
    public String postBrandOwnerRegister(@Valid  @ModelAttribute BecomePartnerForm becomePartnerForm,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "gym-brand-registration";
        }
        BecomePartnerRequest becomePartnerRequest = new BecomePartnerRequest();
        becomePartnerRequest.setBrandName(becomePartnerForm.getBrandName());
        becomePartnerRequest.setBrandOwnerName(becomePartnerForm.getBrandOwnerName());
        becomePartnerRequest.setContactNumber(becomePartnerForm.getContactNumber());
        becomePartnerRequest.setAddress(becomePartnerForm.getAddress());
        becomePartnerRequest.setWebUrl(becomePartnerForm.getWebUrl());
        becomePartnerRequest.setContactEmail(becomePartnerForm.getContactEmail());
        becomePartnerRequest.setSendRequestTime(new Timestamp(System.currentTimeMillis()));
        becomePartnerRequest.setStatus("Đang chờ xử lý");
        int row = becomePartnerService.create(becomePartnerRequest);
        return "redirect:/become-a-partner/successful";
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
