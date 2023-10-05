package com.ks.fitpass.web.controller;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.web.enums.PageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public String getAllDepartment(Model model) {
        List<Department> list = departmentService.getAllByStatus(1);
        model.addAttribute("departments", list);
        model.addAttribute("page", PageEnum.XXX_FIRST_PAGE.getCode());
        return "department";
    }

    @GetMapping("/detail")
    public String getDepartment(@RequestParam("id") String id, Model model) {
        try {
            Department department = departmentService.getOne(Integer.parseInt(id));

            model.addAttribute("department", department);
            model.addAttribute("page", PageEnum.XXX_FIRST_PAGE.getCode());
            return "department";
        } catch (EmptyResultDataAccessException e) {
            return "error/no-data";
        }
    }


}
