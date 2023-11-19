package com.ks.fitpass.web.controller;

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

    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getBOIndex(Principal principal, HttpSession session, Model model) {
        com.ks.fitpass.core.entity.User user = userRepository.findByAccount(principal.getName());
        session.setAttribute("userInfo", user);
        return "brand-owner/index";
    }
}
