package com.ks.fitpass.core.config;

import com.ks.fitpass.core.entity.CustomUser;
import com.ks.fitpass.core.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
        com.ks.fitpass.core.entity.User userSession;
        switch (userRole) {
            case "ADMIN":
            case "GYM_OWNER":
            case "EMPLOYEE":
            case "USER":
            case "BRAND_OWNER":
                userSession = userRepository.findByAccount(customUser.getUsername());
                session.setAttribute("userInfo", userSession);
                session.setAttribute("userRole", userRole);
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
                try {
                    Integer departmentId = userRepository.getDepartmentIdByEmployeeId(userSession.getUserId());
                    if (departmentId == null || departmentId == 0) {
                        response.sendRedirect("/403");
                    } else {
                        response.sendRedirect("/employee/history?id=" + departmentId);
                    }
                } catch (EmptyResultDataAccessException e) {
                    response.sendRedirect("/403");
                }
                break;
            case "USER":
                response.sendRedirect("/homepage");
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
