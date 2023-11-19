package com.ks.fitpass.web.controller;

import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.*;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.web.enums.PageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final GymPlanService gymPlanService;

    private final DepartmentScheduleService departmentScheduleService;

    private final DepartmentAlbumsService departmentAlbumsService;

    private  final DepartmentFeatureService departmentFeatureService;

    private  final DepartmentAmenitieService departmentAmenitieService;


    @Autowired
    public DepartmentController(DepartmentService departmentService, GymPlanService gymPlanService,
                                DepartmentScheduleService departmentScheduleService,
                                DepartmentAlbumsService departmentAlbumsService,
                                DepartmentFeatureService departmentFeatureService,
                                DepartmentAmenitieService departmentAmenitieService) {
        this.departmentService = departmentService;
        this.gymPlanService = gymPlanService;
        this.departmentScheduleService = departmentScheduleService;
        this.departmentAlbumsService = departmentAlbumsService;
        this.departmentFeatureService = departmentFeatureService;
        this.departmentAmenitieService = departmentAmenitieService;
    }

    @GetMapping("/department-detail/{department_id}")
    public String getDepartment(@PathVariable("department_id") int departmentId, Model model, @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "7") int size) {
        try {
            Department department = departmentService.getOne(departmentId);
            model.addAttribute("department", department);
            model.addAttribute("page", PageEnum.XXX_FIRST_PAGE.getCode());

            // Get list of gym plans for the department
            List<GymPlanDto> gymPlans = gymPlanService.getGymPlanDetailsByDepartmentId(departmentId);
            model.addAttribute("gymPlans", gymPlans);

            // Get department schedule
            List<DepartmentSchedule> departmentSchedules = departmentScheduleService.getAllByDepartmentID(departmentId);
            model.addAttribute("departmentSchedules", departmentSchedules);

            // Get Department Album
            List<DepartmentAlbums> departmentAlbums = departmentAlbumsService.getAllByDepartmentID(departmentId);
            model.addAttribute("departmentAlbums", departmentAlbums);

            // Get list of user feedback for the department
            List<UserFeedback> userFeedbacks = departmentService.getDepartmentFeedback(departmentId, page, size);
            model.addAttribute("userFeedbacks", userFeedbacks);

            // Calculate the rating statistics
            DepartmentDTO departmentDTO = departmentService.filterDepartmentFeedbacks(departmentId);
            model.addAttribute("departmentFeedbacks", departmentDTO);

            // Get department features
            List<DepartmentFeature> departmentFeatures = departmentFeatureService.getDepartmentFeatures(departmentId);
            model.addAttribute("departmentFeatures", departmentFeatures);

            // Get Department Amenities
            List<DepartmentAmenities> departmentAmenities = departmentAmenitieService.getAllDepartmentAmenitiesActivate(departmentId);
            model.addAttribute("departmentAmenities", departmentAmenities);

            int totalPages = (int) Math.ceil((double) departmentDTO.getTotal() / size);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("currentPage", page);
            model.addAttribute("departmentId", departmentId);

            return "gym-department-details";
        } catch (EmptyResultDataAccessException e) {
            return "error/no-data";
        }
    }


}

