package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandDetailFeedback;
import com.ks.fitpass.brand.dto.BrandDetailFeedbackPaginition;
import com.ks.fitpass.brand.dto.BrandDetailFeedbackStat;
import com.ks.fitpass.brand.entity.*;
import com.ks.fitpass.brand.service.*;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.ListBrandDepartmentFeedback;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.web.enums.PageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;
    private final BrandAmenitieService brandAmenitieService;
    private final DepartmentService departmentService;
    private final DepartmentFeatureService departmentFeatureService;

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    public BrandController(BrandService brandService, BrandAmenitieService brandAmenitieService, DepartmentService departmentService, DepartmentFeatureService departmentFeatureService) {
        this.brandService = brandService;
        this.brandAmenitieService = brandAmenitieService;
        this.departmentService = departmentService;
        this.departmentFeatureService = departmentFeatureService;
    }

    @GetMapping("/brand-detail/{brand_id}")
    public String getDepartment(@PathVariable("brand_id") int brandId, Model model) {
        try {
            Brand brand = brandService.getOne(brandId);
            model.addAttribute("brand", brand);
            model.addAttribute("page", PageEnum.XXX_FIRST_PAGE.getCode());

            List<BrandAmenitie> brandAmenitie = brandAmenitieService.getAllByBrandIDActivate(brandId);
            model.addAttribute("brandAmenitie", brandAmenitie);

            List<DepartmentDTO> departmentDTOList = departmentService.getDepartmentByBrandID(brandId);
            model.addAttribute("departments", departmentDTOList);


            List<ListBrandDepartmentFeedback> departmentFeedback = departmentService.getDepartmentFeedbackOfBrandOwner(brandId);
            model.addAttribute("departmentFeedback", departmentFeedback);

            BrandDetailFeedbackStat brandDetailFeedbackStat = brandService.getFeedbackOfBrandDetailStat(brandId);
            model.addAttribute("feedbackStat", brandDetailFeedbackStat);

            List<DepartmentFeature> departmentFeatures = new ArrayList<>();

            for (DepartmentDTO departmentDTO : departmentDTOList) {
                int departmentId = departmentDTO.getDepartmentId();
                List<DepartmentFeature> features = departmentFeatureService.getDepartmentFeatures(departmentId);
                departmentFeatures.addAll(features);
            }

            model.addAttribute("departmentFeatures", departmentFeatures);

            return "gym-brand-details";
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return "error/duplicate-key-error";
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return "error/no-data";
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return "error/incorrect-result-size-error";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @GetMapping("/feedback")
    public ResponseEntity<BrandDetailFeedbackPaginition> getBrandFeedback(@RequestParam int brandId,
                                                                          @RequestParam(defaultValue = "1") int page,
                                                                          @RequestParam(defaultValue = "7") int size,
                                                                          @RequestParam("sortRating") String sortRating) {
        try {
            List<BrandDetailFeedback> brandDetailFeedbacks = brandService.getFeedbackOfBrandDetail(brandId,
                    page, size, sortRating);

            int total = brandService.countTotalFeedback(brandId, sortRating);

            int totalPages = (int) Math.ceil((double) total / size);

            BrandDetailFeedbackPaginition brandDetailFeedbackPaginition = new BrandDetailFeedbackPaginition();
            brandDetailFeedbackPaginition.setFeedbackList(brandDetailFeedbacks);
            brandDetailFeedbackPaginition.setTotalPage(totalPages);
            brandDetailFeedbackPaginition.setCurrentPage(page);

            return ResponseEntity.ok(brandDetailFeedbackPaginition);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return ResponseEntity.badRequest().build();
        }  catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        }
    }
}
