package com.ks.fitpass.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping("/check-in/fixed")
    public String getCheckInListOfFixedCustomer(){
        return "employee/employee-check-in-fixed";
        }
    @GetMapping("/check-in/flexible")
    public String getCheckInListOfFlexibleCustomer(){
        return "employee/employee-check-in-flexible";
    }
    @GetMapping("/history")
    public String getCheckInHistory(){
        return "employee/employee-check-in-history";
    }
}
