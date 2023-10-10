package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.service.DepartmentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartmentForHome(1,5);
        model.addAttribute("departments",departmentDTOList);
        session.setAttribute("userInfo", user);
        return "list-of-gym";
    }
    @GetMapping("/user/department-detail")
    public String gymDepartmentDetail(Principal principal, HttpSession session, Model model) {

        return "gym-department-details";
    }

}
