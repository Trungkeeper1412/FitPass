package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.entity.*;
import com.ks.fitpass.brand.service.*;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.web.enums.PageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;
    private final BrandAmenitieService brandAmenitieService;
    private  final BrandAlbumsService brandAlbumsService;
    private final DepartmentService departmentService;
    private final DepartmentFeatureService departmentFeatureService;


    @Autowired
    public BrandController(BrandService brandService, BrandAmenitieService brandAmenitieService, BrandAlbumsService brandAlbumsService, DepartmentService departmentService, DepartmentFeatureService departmentFeatureService) {
        this.brandService = brandService;
        this.brandAmenitieService = brandAmenitieService;
        this.brandAlbumsService = brandAlbumsService;
        this.departmentService = departmentService;
        this.departmentFeatureService = departmentFeatureService;
    }

    @GetMapping("/brand-detail/{brand_id}")
    public String getDepartment(@PathVariable("brand_id") int brandId, Model model) {
        try {
            Brand brand = brandService.getOne(brandId);
            model.addAttribute("brand", brand);
            model.addAttribute("page", PageEnum.XXX_FIRST_PAGE.getCode());

            List<BrandAmenitie> brandAmenitie = brandAmenitieService.getAllByBrandID(brandId);
            model.addAttribute("brandAmenitie", brandAmenitie);

            List<BrandAlbums> brandAlbums = brandAlbumsService.getAllByBrandID(brandId);
            model.addAttribute("brandAlbums", brandAlbums);

            List<DepartmentDTO> departmentDTOList = departmentService.getDepartmentByBrandID(brandId);
            model.addAttribute("departments", departmentDTOList);

            List<DepartmentFeature> departmentFeatures = new ArrayList<>();

            // Loop through departmentDTOList to get departmentId values and fetch department features
            for (DepartmentDTO departmentDTO : departmentDTOList) {
                int departmentId = departmentDTO.getDepartmentId();
                List<DepartmentFeature> features = departmentFeatureService.getDepartmentFeatures(departmentId);
                departmentFeatures.addAll(features);
            }

            model.addAttribute("departmentFeatures", departmentFeatures);

            return "gym-brand-details";
        } catch (EmptyResultDataAccessException e) {
            return "error/no-data";
        }
    }
}
