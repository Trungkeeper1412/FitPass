package com.ks.fitpass.core.config;

import com.ks.fitpass.core.entity.CustomUser;
import com.ks.fitpass.core.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;

    public CustomAuthenticationSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String userRole = determineUserRole(authentication);
        HttpSession session = request.getSession();
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        switch (userRole) {
            case "ADMIN":
            case "GYM_OWNER":
            case "EMPLOYEE":
            case "USER":
            case "BRAND_OWNER":
                com.ks.fitpass.core.entity.User userSession = userRepository.findByAccount(customUser.getUsername());
                session.setAttribute("userInfo", userSession);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userRole);
        }

        // Redirect based on user role
        switch (userRole) {
            case "ADMIN":
                response.sendRedirect("/admin/index");
                break;
            case "GYM_OWNER":
                response.sendRedirect("/gym-owner/index");
                break;
            case "EMPLOYEE":
                String employeeId = "1"; // Replace with the actual employee ID
                response.sendRedirect("/employee/history?id=" + employeeId);
                break;
            case "USER":
                response.sendRedirect("/user/homepage");
                break;
            case "BRAND_OWNER":
                response.sendRedirect("/brand-owner/index");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userRole);
        }
    }

    private String determineUserRole(Authentication authentication) {
        return authentication.getAuthorities().iterator().next().getAuthority();
    }

}