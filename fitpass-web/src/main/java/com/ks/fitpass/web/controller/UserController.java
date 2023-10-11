package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.service.DepartmentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final DepartmentService departmentService;

    @GetMapping("/user/homepage")
    public String userHomepage(Principal principal, HttpSession session, Model model) {
        com.ks.fitpass.core.entity.User user = userRepository.findByAccount(principal.getName());
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartmentTopRatingForHome(1,5);
        model.addAttribute("departments",departmentDTOList);
        session.setAttribute("userInfo", user);
        return "list-of-gym";
    }

    @PostMapping("/user/homepage")
    public String getNearbyGyms(@RequestParam("latitude") double latitude,
                                @RequestParam("longitude") double longitude,
                                Principal principal, HttpSession session, Model model) {
        com.ks.fitpass.core.entity.User user = userRepository.findByAccount(principal.getName());
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartmentByNearbyLocation(1, 5, latitude, longitude, 5000);
        model.addAttribute("departments", departmentDTOList);
        session.setAttribute("userInfo", user);
        return "fragments/gym-card-fragment";
    }
}
