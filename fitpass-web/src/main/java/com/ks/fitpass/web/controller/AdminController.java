package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandAdminList;
import com.ks.fitpass.brand.service.BrandService;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.credit_card.service.CreditCardService;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryAdmin;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistoryWithBrandName;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getAdminIndex() {
        return "admin/index";
    }

    @GetMapping("/feature")
    public String getFeature(Model model) {
        try {
            List<Feature> featureList = departmentFeatureService.getAllFeatureNoStatus();
            model.addAttribute("featureList", featureList);
            return "admin/admin-feature";
        }catch (DuplicateKeyException ex) {
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
        }catch (DuplicateKeyException ex) {
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

    @GetMapping("/feature/detail/{featureId}")
    public ResponseEntity<Feature> getFeatureDetail(@PathVariable int featureId) {
        try {
            Feature feature = departmentFeatureService.getByFeatureId(featureId);
            return ResponseEntity.ok(feature);
        }catch (EmptyResultDataAccessException e) {
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
        try {


            List<BrandAdminList> brandList = brandService.getAllBrand();
            model.addAttribute("brandList", brandList);
            return "admin/admin-brand-list";

        }catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }

    }

    @PostMapping("/brand/number-percentage")
    public String updateBrandNumberPercentage(@RequestParam int brandId, @RequestParam int numberPercentage) {
        try {
            int updateRow = brandService.updateBrandMoneyPercent(brandId, numberPercentage);
            if(updateRow == 0) {
                throw new DataAccessException("Update fail", null) {};
            }
        } catch (DataAccessException e) {
            return "error/data-access-error";
        }
        return "redirect:/admin/brand/list";
    }

    @GetMapping("/account/brand")
    public String getAccountBrandList(Model model) {
        try {
            List<BrandAdminList> brandList = brandService.getAllBrand();
            model.addAttribute("brandList", brandList);
            return "admin/admin-account-brand";
        }catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @GetMapping("/account/brand/add")
    public String getAccountBrandAdd() {
        return "admin/admin-account-brand-add";
    }

    @GetMapping("/account/user")
    public String getAccountUserList(Model model) {
        try {


            List<UserDTO> userDTOList = userService.getAllAccountUser();
            model.addAttribute("userDTOList", userDTOList);
            return "admin/admin-account-user";
        }
        catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @GetMapping("/withdrawal")
    public String getWithdrawalList(Model model) {
        try {
            List<RequestWithdrawHistoryWithBrandName> requestWithdrawHistoryListPending = requestWithdrawHistoryService.getAllByStatusWithBrandName("Đang xử lý");
            List<RequestWithdrawHistoryWithBrandName> requestWithdrawHistoryListAll = requestWithdrawHistoryService.getAllWithBrandName();
            RequestHistoryStats requestHistoryStats = requestWithdrawHistoryService.getAllStats();

            model.addAttribute("requestWithdrawHistoryListPending", requestWithdrawHistoryListPending);
            model.addAttribute("requestWithdrawHistoryListAll", requestWithdrawHistoryListAll);
            model.addAttribute("requestHistoryStats", requestHistoryStats);

            return "admin/admin-withdrawal-list";
        }catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
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
            return "error/data-access-error";
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
