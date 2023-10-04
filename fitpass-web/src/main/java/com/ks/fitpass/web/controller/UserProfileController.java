package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.repository.UserDetailRepository;
import com.ks.fitpass.core.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserProfileController {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserProfileController(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    @GetMapping("/user-profile")
    public String userProfile(Model model, Principal principal, Authentication authentication, HttpSession session) {
        User user = getUser(principal);
        UserDetail userDetail = userDetailRepository.findByUserId(user.getUserId());
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("userRole", authentication.getAuthorities());
        return "user-profile";
    }

    @PostMapping("/user-profile/update")
    public String updateUserProfile(@RequestParam("userId") int userId, @ModelAttribute("userDetail") UserDetail userDetail, Model model) {
        UserDetail existingUserDetail = userDetailRepository.findByUserId(userId);
        existingUserDetail.setFirstName(userDetail.getFirstName());
        existingUserDetail.setLastName(userDetail.getLastName());
        existingUserDetail.setEmail(userDetail.getEmail());
        existingUserDetail.setPhoneNumber(userDetail.getPhoneNumber());
        existingUserDetail.setAddress(userDetail.getAddress());
        existingUserDetail.setGender(userDetail.getGender());
        existingUserDetail.setRole(userDetail.getRole());

        boolean updated = userDetailRepository.updateUserDetail(existingUserDetail);
        if (updated) {
            model.addAttribute("successMessage", "Profile updated successfully.");
        } else {
            model.addAttribute("errorMessage", "Failed to update profile.");
        }
        return "user-profile";
    }

    private User getUser(Principal principal) {
        String email = principal.getName();
        return userRepository.findByAccount(email);
    }
}