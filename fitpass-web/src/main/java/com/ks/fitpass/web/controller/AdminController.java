package com.ks.fitpass.web.controller;

import com.ks.fitpass.become_a_partner.dto.BecomePartnerRequest;
import com.ks.fitpass.become_a_partner.dto.BecomePartnerUpdateStatus;
import com.ks.fitpass.become_a_partner.dto.BrandRatingStatAdmin;
import com.ks.fitpass.become_a_partner.dto.BrandStatAdmin;
import com.ks.fitpass.become_a_partner.service.BecomePartnerService;
import com.ks.fitpass.brand.dto.BrandAdminList;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryBrandAdmin;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.credit_card.service.CreditCardService;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.order.dto.OrderDetailStatAdmin;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryAdmin;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistory;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistoryWithBrandName;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.util.Email;
import com.ks.fitpass.web.util.WebUtil;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final BrandService brandService;
    private final RequestWithdrawHistoryService requestWithdrawHistoryService;
    private final CreditCardService creditCardService;
    private final DepartmentFeatureService departmentFeatureService;
    private final BecomePartnerService becomePartnerService;
    private final UserService userService;
    private final Email emailService;
    private final WalletService walletService;
    private final DepartmentService departmentService;
    private final TransactionService transactionService;
    private final OrderDetailService orderDetailService;

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getAdminIndex(Model model) {
        int totalAccount = userService.countAllUsersAccount();

        int totalBrand = brandService.countAllBrand();

        int totalDepartment = departmentService.countAllDepartment();

        double totalCredit =  transactionService.countAllCredit();

        double totalBrandCredit = requestWithdrawHistoryService.countAllBrandCredit();

        OrderDetailStatAdmin orderDetailStatAdmin = orderDetailService.getAdminStat();

        List<BrandStatAdmin> brandStatAdmin = brandService.getAdminStat();

        brandStatAdmin.forEach(brandStat -> brandStat.setTotalAmount(brandStat.getTotalAmount() * 1000));

        List<BrandRatingStatAdmin> brandRatingStatAdmin = brandService.getAdminRatingStat();

        model.addAttribute("totalAccount", totalAccount);
        model.addAttribute("totalBrand", totalBrand);
        model.addAttribute("totalDepartment", totalDepartment);
        model.addAttribute("totalCredit", totalCredit);
        model.addAttribute("totalBrandCredit", totalBrandCredit);
        model.addAttribute("orderDetailStatAdmin", orderDetailStatAdmin);
        model.addAttribute("brandStatAdmin", brandStatAdmin);
        model.addAttribute("brandRatingStatAdmin", brandRatingStatAdmin);
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
    public String getAccountBrandAdd(Model model) {
        List<BecomePartnerRequest> becomePartnerRequestList =
                becomePartnerService.getAllBecomePartnerRequestByStatus("Thành công");
        model.addAttribute("becomePartnerRequestList", becomePartnerRequestList);
        return "admin/admin-account-brand-add";
    }

    @PostMapping("/account/brand/create")
    public String createBrandAccount(@RequestParam String requestId, @RequestParam String brandEmail,HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        int userId = user.getUserId();

        BecomePartnerRequest becomePartnerRequest = becomePartnerService.getById(Integer.parseInt(requestId));
        String brandName = becomePartnerRequest.getBrandName();
        // Create userName
        String accountName = "fp_" + brandName.replaceAll("\\s+", "");
        // Create password random
        String randomPassword = WebUtil.generateRandomPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(randomPassword);
        // Create user create time
        Long userCreateTimeLong = System.currentTimeMillis();
        String createTime = userCreateTimeLong.toString();
        // Create user_deleted = 0;
        boolean userDelete = false;
        // Create new User
        User newUser = new User();
        newUser.setUserAccount(accountName);
        newUser.setUserCreateTime(userCreateTimeLong);
        newUser.setUserPassword(hashedPassword);
        newUser.setUserDeleted(userDelete);
        newUser.setUserDetailId(null);
        newUser.setCreatedBy(user.getUserId());
        userService.insertIntoUser(newUser);

        // Get last user insert id
        int userInsertId = userService.getLastUserInsertId(newUser);
        // Insert into user_role
        userService.insertIntoUserRole(userInsertId, 5);

        walletService.insertWallet(userInsertId, 0);

        emailService.send("Test", "Account: " + accountName + ", Password: " + randomPassword,
                brandEmail);

        brandService.createBrandWithBrandName(userInsertId, brandName);
        return "redirect:/admin/account/brand";
    }

    @GetMapping("/account/requestEmail/{becomeAPartnerRequestId}")
    public ResponseEntity<String> getAccountRequestEmail(@PathVariable int becomeAPartnerRequestId) {
        try{
            BecomePartnerRequest becomePartnerRequest = becomePartnerService.getById(becomeAPartnerRequestId);
            return ResponseEntity.ok(becomePartnerRequest.getContactEmail());
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
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
            int userId = requestWithdrawHistoryService.getUserIdByRequestHistoryId(requestHistoryId);
            double userBalance = walletService.getBalanceByUserId(userId);
            requestWithdrawHistory.setBrandBalance(userBalance);

            if(userBalance < requestWithdrawHistory.getAmountCredit()) {
                int updateRow = requestWithdrawHistoryService.updateStatus(requestHistoryId, "Giao dịch bị hủy");
            }

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
    public String updateWithdrawalStatus(@RequestParam int requestHistoryId, HttpSession session) {
        try {
            User admin = (User) session.getAttribute("userInfo");
            String status = "Thành công";
            int updateRow = requestWithdrawHistoryService.updateStatus(requestHistoryId, status);
            if(updateRow == 0) {
                throw new DataAccessException("Update fail", null) {};
            }

            int userId = requestWithdrawHistoryService.getUserIdByRequestHistoryId(requestHistoryId);
            int adminId = admin.getUserId();

            double userBalance = walletService.getBalanceByUserId(userId);
            double adminBalance = walletService.getBalanceByUserId(adminId);

            RequestHistoryAdmin requestWithdrawHistory = requestWithdrawHistoryService.getById(requestHistoryId);
            double amountCredit = requestWithdrawHistory.getAmountCredit();

            walletService.updateBalanceByUderId(userId, userBalance - amountCredit);
            walletService.updateBalanceByUderId(adminId, adminBalance + amountCredit);

        } catch (DataAccessException e) {
            return "error/data-access-error";
        }
        return "redirect:/admin/withdrawal";
    }

    @GetMapping("/withdrawal/history")
    public String getWithdrawalHistory(Model model) {
        List<RequestHistoryBrandAdmin> listHisBrand = requestWithdrawHistoryService.getAllRequestHistoryBrandAdmin();
        model.addAttribute("listHisBrand", listHisBrand);
        return "admin/admin-withdrawal-history";
    }

    @GetMapping("/registration/list")
    public String getRegistrationList(Model model) {
        try {
            List<BecomePartnerRequest> becomePartnerRequestListPending = becomePartnerService.getAllBecomePartnerRequestByStatus("Đang chờ xử lý");
            List<BecomePartnerRequest> becomePartnerRequestListHandle = becomePartnerService.getAllBecomePartnerRequestByStatus("Đang xử lý");
            List<BecomePartnerRequest> becomePartnerRequestListSuccess = becomePartnerService.getAllBecomePartnerRequestByStatus("Thành công");
            List<BecomePartnerRequest> becomePartnerRequestListFail = becomePartnerService.getAllBecomePartnerRequestByStatus("Từ chối đơn");

            List<BecomePartnerRequest> becomePartnerRequestListUp =
                    Stream.concat(becomePartnerRequestListPending.stream(),
                                    becomePartnerRequestListHandle.stream()).distinct()
                            .toList();

            List<BecomePartnerRequest> becomePartnerRequestListDown =
                    Stream.concat(becomePartnerRequestListSuccess.stream(),
                                    becomePartnerRequestListFail.stream()).distinct()
                            .toList();

            model.addAttribute("becomePartnerRequestListUp", becomePartnerRequestListUp);
            model.addAttribute("becomePartnerRequestListDown", becomePartnerRequestListDown);
            return "admin/admin-registration-list";
        }
        catch (DuplicateKeyException ex) {
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

    @GetMapping("/registration/detail/{becomeAPartnerRequestId}")
    public ResponseEntity<BecomePartnerRequest> getRegistrationDetail(@PathVariable int becomeAPartnerRequestId) {
        try{
            BecomePartnerRequest becomePartnerRequest = becomePartnerService.getById(becomeAPartnerRequestId);
            return ResponseEntity.ok(becomePartnerRequest);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/registration/update-status")
    public ResponseEntity<String> updateRegistrationStatus(@RequestBody BecomePartnerUpdateStatus b) {
        try {
            if(b.getStatus().equals("Đang xử lý")) {
                becomePartnerService.updateStartRequestTime(b.getBecomeAPartnerRequestId(), new Timestamp(System.currentTimeMillis()));
            } else if(b.getStatus().equals("Từ chối đơn")) {
                becomePartnerService.updateCancelRequestTime(b.getBecomeAPartnerRequestId(), new Timestamp(System.currentTimeMillis()), b.getCancelReason());
            } else if(b.getStatus().equals("Thành công")) {
                becomePartnerService.updateApproveRequestTime(b.getBecomeAPartnerRequestId(), new Timestamp(System.currentTimeMillis()));
            }
            int updateRow = becomePartnerService.updateStatus(b.getBecomeAPartnerRequestId(), b.getStatus());
            if(updateRow == 0) {
                throw new DataAccessException("Update fail", null) {};
            }
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Cập nhật thành công");
    }
}
