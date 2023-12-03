package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final WalletService walletService;

    @GetMapping("/")
    public String checkFirstTimeLogin(HttpSession session){
        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            return "redirect:/homepage";
        }
        return "landing";
    }

    @GetMapping("/landing-page")
    public String getLandingPage(){
        return "landing";
    }


    @GetMapping("/login")
    public String login(HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            return "redirect:/homepage";
        }
        return "login-register";
    }

    @GetMapping("/show-info")
    public String admin(Model model, HttpSession session, Authentication authentication) {
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("userInfo", user);
        model.addAttribute("userRole", authentication.getAuthorities());
        return "show-info";
    }

    @GetMapping("/forgot-password")
    public String forgotPw() {
        return "forgot-password";
    }
}
