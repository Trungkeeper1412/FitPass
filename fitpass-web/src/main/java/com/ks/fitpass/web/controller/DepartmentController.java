package com.ks.fitpass.web.controller;

import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.department.service.GymPlanService;
import com.ks.fitpass.web.enums.PageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final GymPlanService gymPlanService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, GymPlanService gymPlanService) {
        this.departmentService = departmentService;
        this.gymPlanService = gymPlanService;
    }

    @GetMapping("/department-detail/{department_id}")
    public String getDepartment(@PathVariable("department_id") int departmentId, Model model) {
        try {
            Department department = departmentService.getOne(departmentId);
            model.addAttribute("department", department);
            model.addAttribute("page", PageEnum.XXX_FIRST_PAGE.getCode());

            // Get list of gym plans for the department
            List<GymPlanDto> gymPlans = gymPlanService.getGymPlanDetailsByDepartmentId(departmentId);
            model.addAttribute("gymPlans", gymPlans);

            return "gym-department-details";
        } catch (EmptyResultDataAccessException e) {
            return "error/no-data";
        }
    }
}