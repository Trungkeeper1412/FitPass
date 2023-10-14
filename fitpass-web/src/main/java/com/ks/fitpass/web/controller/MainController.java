package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/")
//    public String index(Principal principal, HttpSession session) {
//        com.ks.fitpass.core.entity.User user = userRepository.findByAccount(principal.getName());
//        session.setAttribute("userInfo", user);
//        return "index";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @GetMapping("/show-info")
    public String admin(Model model, Principal principal, Authentication authentication) {
        String email = principal.getName();
        com.ks.fitpass.core.entity.User user = userRepository.findByAccount(email);
        model.addAttribute("userInfo", user);
        model.addAttribute("userRole", authentication.getAuthorities());
        return "show-info";
    }
}
