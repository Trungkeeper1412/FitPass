package com.ks.fitpass.web.controller;

import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentDetailsFeedback;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.*;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.web.enums.PageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
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
    private final DepartmentFeatureService departmentFeatureService;
    private  final DepartmentAmenitieService departmentAmenitieService;
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
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
        } catch (DataAccessException exception){
            return "error/error";
        }
    }

    @GetMapping("/department-feedback")
    public ResponseEntity<DepartmentDetailsFeedback> getDepartmentFeedback(@RequestParam int departmentId,
                                                                           @RequestParam(defaultValue = "1") int page,
                                                                           @RequestParam(defaultValue = "7") int size,
                                                                           @RequestParam(required = false) String sortRating) {
        try {
            // Get list of user feedback for the department
            List<UserFeedback> userFeedbacks = departmentService.getDepartmentFeedback(departmentId, page, size, sortRating);

            // Get total number of query results
            int total = departmentService.countAllFeedback(departmentId, sortRating);

            int totalPages = (int) Math.ceil((double) total / size);

            DepartmentDetailsFeedback departmentDetailsFeedback = new DepartmentDetailsFeedback();
            departmentDetailsFeedback.setUserFeedbacks(userFeedbacks);
            departmentDetailsFeedback.setTotalPage(totalPages);
            departmentDetailsFeedback.setCurrentPage(page);

            return ResponseEntity.ok(departmentDetailsFeedback);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

