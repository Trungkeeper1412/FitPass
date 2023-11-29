package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandAdminList;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.credit_card.service.CreditCardService;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryAdmin;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistoryWithBrandName;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final BrandService brandService;
    private final RequestWithdrawHistoryService requestWithdrawHistoryService;
    private final CreditCardService creditCardService;
    private final DepartmentFeatureService departmentFeatureService;

    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getAdminIndex() {
        return "admin/index";
    }

    @GetMapping("/feature")
    public String getFeature(Model model) {
        List<Feature> featureList = departmentFeatureService.getAllFeatureNoStatus();
        model.addAttribute("featureList", featureList);
        return "admin/admin-feature";
    }

    @PostMapping("/feature/add")
    public String addFeature(@RequestParam String featureName, @RequestParam String featureIcon) {
        try {
            String featureIconValue = "<i class=\""+featureIcon+"\"></i>";
            Feature feature = Feature.builder()
                    .featureName(featureName)
                    .featureIcon(featureIconValue)
                    .featureStatus(1)
                    .build();
            departmentFeatureService.insertFeature(feature);
            return "redirect:/admin/feature";
        } catch (DataAccessException e) {
            return "error/500";
        }
    }

    @GetMapping("/feature/detail/{featureId}")
    public ResponseEntity<Feature> getFeatureDetail(@PathVariable int featureId) {
        Feature feature = departmentFeatureService.getByFeatureId(featureId);
        return ResponseEntity.ok(feature);
    }

    @PostMapping("/feature/update")
    public ResponseEntity<String> updateFeature(@RequestBody Feature feature) {
        try {
            String featureIconValue = feature.getFeatureIcon();
            String featureIcon = "<i class=\""+featureIconValue+"\"></i>";
            feature.setFeatureIcon(featureIcon);
            departmentFeatureService.updateFeature(feature);
            return ResponseEntity.ok("Update feature success");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/feature/updateStatus")
    public ResponseEntity<String> updateFeatureStatus(@RequestBody Feature feature) {
        try {
            departmentFeatureService.updateFeatureStatus(feature.getFeatureID(), feature.getFeatureStatus());
            return ResponseEntity.ok("Update feature status success");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/brand/list")
    public String getBrandList(Model model) {
        List<BrandAdminList> brandList = brandService.getAllBrand();
        model.addAttribute("brandList", brandList);
        return "admin/admin-brand-list";
    }

    @PostMapping("/brand/number-percentage")
    public String updateBrandNumberPercentage(@RequestParam int brandId, @RequestParam int numberPercentage) {
        try {
            int updateRow = brandService.updateBrandMoneyPercent(brandId, numberPercentage);
            if(updateRow == 0) {
                throw new DataAccessException("Update fail", null) {};
            }
        } catch (DataAccessException e) {
            return "error/500";
        }
        return "redirect:/admin/brand/list";
    }

    @GetMapping("/account/brand")
    public String getAccountBrandList() {
        return "admin/admin-account-brand";
    }

    @GetMapping("/account/brand/add")
    public String getAccountBrandAdd() {
        return "admin/admin-account-brand-add";
    }

    @GetMapping("/account/user")
    public String getAccountUserList() {
        return "admin/admin-account-user";
    }

    @GetMapping("/withdrawal")
    public String getWithdrawalList(Model model) {
        List<RequestWithdrawHistoryWithBrandName> requestWithdrawHistoryListPending = requestWithdrawHistoryService.getAllByStatusWithBrandName("Đang xử lý");
        List<RequestWithdrawHistoryWithBrandName> requestWithdrawHistoryListAll = requestWithdrawHistoryService.getAllWithBrandName();
        RequestHistoryStats requestHistoryStats = requestWithdrawHistoryService.getAllStats();

        model.addAttribute("requestWithdrawHistoryListPending", requestWithdrawHistoryListPending);
        model.addAttribute("requestWithdrawHistoryListAll", requestWithdrawHistoryListAll);
        model.addAttribute("requestHistoryStats", requestHistoryStats);

        return "admin/admin-withdrawal-list";
    }

    @GetMapping("/withdrawal/detail/{requestHistoryId}")
    public ResponseEntity<RequestHistoryAdmin> getWithdrawalDetail(@PathVariable int requestHistoryId) {
        try{
            RequestHistoryAdmin requestWithdrawHistory = requestWithdrawHistoryService.getById(requestHistoryId);
            return ResponseEntity.ok(requestWithdrawHistory);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/withdrawal/number-percentage/{requestHistoryId}")
    public ResponseEntity<Integer> getWithdrawalNumberPercentage(@PathVariable int requestHistoryId) {
        try{
            return ResponseEntity.ok(requestWithdrawHistoryService.getNumberPercentage(requestHistoryId));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/withdrawal/update-status")
    public String updateWithdrawalStatus(@RequestParam int requestHistoryId) {
        try {
            String status = "Thành công";
            int updateRow = requestWithdrawHistoryService.updateStatus(requestHistoryId, status);
            if(updateRow == 0) {
                throw new DataAccessException("Update fail", null) {};
            }
        } catch (DataAccessException e) {
            return "error/500";
        }
        return "redirect:/admin/withdrawal";
    }

    @GetMapping("/withdrawal/history")
    public String getWithdrawalHistory() {
        return "admin/admin-withdrawal-history";
    }

    @GetMapping("/registration/list")
    public String getRegistrationList() {
        return "admin/admin-registration-list";
    }
}
