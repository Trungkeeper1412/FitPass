package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/brand-owner")
@RequiredArgsConstructor
public class BrandOwnerController {
    private final UserRepository userRepository;
    private final BrandService brandService;

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
}
