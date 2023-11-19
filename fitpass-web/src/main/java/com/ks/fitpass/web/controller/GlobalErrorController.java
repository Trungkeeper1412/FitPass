package com.ks.fitpass.web.controller;

import com.ks.fitpass.web.util.WebUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class GlobalErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            }
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            }
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            }
        }
        return "error/error";
    }

    @GetMapping("/403")
    public String accessDenied(Principal principal) {
        if (principal != null) {
            User currentUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtil.toString(currentUser);
            String message = "Hi " + principal.getName() + ": You do not have permission to access this page!";
            System.err.println(userInfo);
            System.err.println(message);
        }
        return "error/403";
    }
}
