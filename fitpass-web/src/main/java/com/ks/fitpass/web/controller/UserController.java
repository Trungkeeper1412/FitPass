package com.ks.fitpass.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user1")
public class UserController {

    @GetMapping("/profile")
    public String viewProfile() {
        return "user/profile";
    }
}
