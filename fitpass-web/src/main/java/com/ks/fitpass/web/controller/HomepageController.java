package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.brand.service.*;
import com.ks.fitpass.brand.entity.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class HomepageController {
    private final UserRepository userRepository;
    private final DepartmentService departmentService;
    private final BrandService brandService;

    @GetMapping("/homepage")
    public String getHomepage(Principal principal, HttpSession session, Model model) {
        com.ks.fitpass.core.entity.User user = userRepository.findByAccount(principal.getName());
        List<Brand> brandList = brandService.getAllByStatus(1);

        // Create a map to store brand ID and corresponding department list
        Map<Integer, List<DepartmentDTO>> brandDepartmentsMap = new HashMap<>();
        for (Brand brand : brandList) {
            List<DepartmentDTO> departmentList = departmentService.getAllDepartmentByBrandId(brand.getBrandId(), 1, 5);
            brandDepartmentsMap.put(brand.getBrandId(), departmentList);
        }

        // Get list of departments, default sorted by rating
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartmentTopRatingForHome(1, 5);

        model.addAttribute("brands", brandList);
        model.addAttribute("brandDepartmentsMap", brandDepartmentsMap);
        model.addAttribute("departments", departmentDTOList);
        session.setAttribute("userInfo", user);
        return "homepage/homepage-user";
    }

    @PostMapping("/homepage")
    public String getNearByDepartmentList(@RequestParam("userLatitude") double userLatitude,
                                          @RequestParam("userLongitude") double userLongitude, Model model) {
        Map<DepartmentDTO, Double> departmentDistanceMap = departmentService.getAllDepartmentByNearbyLocation(
                1, 5, userLatitude, userLongitude, 5);
        model.addAttribute("departmentDistanceMap",departmentDistanceMap);
        return "homepage/fragments/list-gym-fr";
    }

    @GetMapping("/profile/calendar")
    public String getCalendar(){
        return "user/calendar";
    }

    @GetMapping("profile/my-profile")
    public String showProfile() {
        return "user-profile2";
    }
}
