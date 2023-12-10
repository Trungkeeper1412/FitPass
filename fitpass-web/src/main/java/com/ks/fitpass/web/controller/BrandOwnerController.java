package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.*;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.credit_card.dto.CreditCard;
import com.ks.fitpass.credit_card.service.CreditCardService;
import com.ks.fitpass.department.dto.*;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.dto.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.order.service.OrderService;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistory;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.enums.PageEnum;
import com.ks.fitpass.web.util.Email;
import com.ks.fitpass.web.util.WebUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/brand-owner")
@RequiredArgsConstructor
public class BrandOwnerController {
    private final UserService userService;
    private final WalletService walletService;
    private final Email emailService;
    private final BrandService brandService;
    private final DepartmentService departmentService;
    private final GymPlanService gymPlanService;
    private final DepartmentScheduleService departmentScheduleService;
    private final DepartmentAlbumsService departmentAlbumsService;
    private final DepartmentAmenitieService departmentAmenitieService;
    private final DepartmentFeatureService departmentFeatureService;
    private final BrandAmenitieService brandAmenitieService;
    private final CreditCardService creditCardService;
    private final RequestWithdrawHistoryService requestWithdrawHistoryService;
    private final OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    //Index (Statistic Dashboard)
    @GetMapping("/index")
    public String getBOIndex(HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("userInfo");
            Brand brandDetails = brandService.getBrandDetail(user.getUserId());

            int numberOfGymplan = gymPlanService.getNumberOfGymPlan(brandDetails.getBrandId());

            int numberOfOrder = orderService.getNumberOfOrder(brandDetails.getBrandId());

            int totalRevenue = orderService.getTotalRevenue(brandDetails.getBrandId());

            int totalRating = brandService.getTotalRating(brandDetails.getBrandId());

            List<DepartmentStatBrandOwner> departmentStatBrandOwnerList = departmentService.getDepartmentStatBrandOwner(brandDetails.getBrandId());

            List<DepartmentRatingStatBrandOwner> departmentRatingStatBrandOwnerList = departmentService.getDepartmentRatingStatBrandOwner(brandDetails.getBrandId());

            model.addAttribute("numberOfGymplan", numberOfGymplan);
            model.addAttribute("numberOfOrder", numberOfOrder);
            model.addAttribute("totalRevenue", totalRevenue);
            model.addAttribute("totalRating", totalRating);
            model.addAttribute("departmentStatBrandOwnerList", departmentStatBrandOwnerList);
            model.addAttribute("departmentRatingStatBrandOwnerList", departmentRatingStatBrandOwnerList);
            model.addAttribute("brandDetails", brandDetails);

            return "brand-owner/index";
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

    //Brand Profile (Not a person)
    @GetMapping("/profile")
    public String getBrandProfile(HttpSession session, Model model) {
        try {


            User user = (User) session.getAttribute("userInfo");
            Brand brandDetails = brandService.getBrandDetail(user.getUserId());
            model.addAttribute("brandDetails", brandDetails);
            return "brand-owner/gym-brand-update-profile";
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

    @PostMapping("/updateProfile")
    public ResponseEntity<Integer> updateBrandProfile(@RequestBody BrandOwnerProfile brandOwnerProfile) {
        try {
            int updateResult = brandService.updateBrandDetail(brandOwnerProfile);
            return ResponseEntity.ok(updateResult);
        }catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        }
    }

    //Change password (for brand owner account)
    @GetMapping("/password")
    public String updateBrandPassword() {
        return "brand-owner/gym-brand-update-password";
    }

    @PostMapping("/password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            User user = (User) session.getAttribute("userInfo");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Kiểm tra mật khẩu hiện tại
            if (!passwordEncoder.matches(currentPassword, user.getUserPassword())) {
                model.addAttribute("currentPasswordError", "Mật khẩu hiện tại không đúng");
                return "brand-owner/gym-brand-update-password";
            }
            // Kiểm tra mật khẩu mới và xác nhận mật khẩu
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("passwordMismatchError", "Mật khẩu mới và xác nhận mật khẩu không khớp");
                return "brand-owner/gym-brand-update-password";
            }
            String hashedPassword = passwordEncoder.encode(newPassword);
            // Cập nhật mật khẩu mới
            userService.updatePassword(hashedPassword, user.getUserId());

            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/brand-owner/password?success=true";
        } catch (Exception ex) {
            // Handle other exceptions
            logger.error("Exception occurred in changePassword", ex);
            model.addAttribute("error", "Đã xảy ra lỗi khi thay đổi mật khẩu");
            return "brand-owner/gym-brand-update-password";
        }
    }

    //Department Management
    @GetMapping("/department/list")
    public String getListOfDepartment(Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("userInfo");
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            List<DepartmentListByBrandDTO> departmentDTOList = departmentService.getAllDepartmentListOfBrand(brandId);

            model.addAttribute("brandId", brandId);
            model.addAttribute("departmentList", departmentDTOList);
            return "brand-owner/gym-brand-department-list";
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

    @PostMapping("/department/details")
    public String getDepartmentDetails(@RequestParam("id") int departmentId, Model model) {
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

            List<DepartmentAmenitie> departmentAmenitieList = departmentAmenitieService.getAllAmenitieOfDepartment(departmentId);
            model.addAttribute("departmentAmenitieList", departmentAmenitieList);

            // Get department features
            List<DepartmentFeature> departmentFeatures = departmentFeatureService.getDepartmentFeatures(departmentId);
            model.addAttribute("departmentFeatures", departmentFeatures);

            model.addAttribute("departmentId", departmentId);
            return "brand-owner/gym-brand-department-detail";
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

    @PostMapping("/department/updateStatus")
    public ResponseEntity<Integer> updateStatusDepartment(@RequestParam int status,@RequestParam int departmentId) {
      try {
          int update = departmentService.updateDepartmentStatus(status, departmentId);
          if (status == 0) {
              departmentService.updateDepartmentGymOwner(departmentId, 0);
          }
          return ResponseEntity.ok(update);
      }catch (DuplicateKeyException ex) {
          // Handle duplicate key violation
          logger.error("DuplicateKeyException occurred", ex);
          return ResponseEntity.badRequest().build();
      } catch (EmptyResultDataAccessException ex) {
          // Handle empty result set
          logger.error("EmptyResultDataAccessException occurred", ex);
          return ResponseEntity.badRequest().build();
      } catch (IncorrectResultSizeDataAccessException ex) {
          // Handle incorrect result size
          logger.error("IncorrectResultSizeDataAccessException occurred", ex);
          return ResponseEntity.badRequest().build();
      } catch (DataAccessException ex) {
          // Handle other data access issues
          logger.error("DataAccessException occurred", ex);
          return ResponseEntity.badRequest().build();
      }
    }

    @GetMapping("/department/add")
    public String addDepartment(@RequestParam("id") int brandId, Model model) {
        try {


            model.addAttribute("brandId", brandId);
            return "brand-owner/gym-brand-department-add";
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
    @PostMapping("/department/add")
    public String createDepartment(@RequestParam int brandId,
                                   @RequestParam String brandName,
                                   Model model) {
        try {


            if (brandName == null || brandName.isEmpty()) {
                String errorMessage = "Brand Name can't be empty";
                model.addAttribute("errorMessage", errorMessage);
                return "brand-owner/gym-brand-department-add";
            }
            departmentService.createDepartmentWithBrandId(brandId, brandName);
            Thread.sleep(1500);

            return "redirect:/brand-owner/department/list?id=" + brandId;
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Feedback Management
    @GetMapping("/feedback/list")
    public String getListOfDepartmentFeedback(Model model, HttpSession session) {
        try {

            User user = (User) session.getAttribute("userInfo");
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            List<ListBrandDepartmentFeedback> departments = departmentService.getDepartmentFeedbackOfBrandOwner(brandId);

            model.addAttribute("listDepartment", departments);
            return "brand-owner/gym-brand-feedback-list";
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

    @PostMapping("/feedback/details")
    public String getDepartmentFeedbackDetails(@RequestParam("id") int departmentId, Model model) {
        try {
            Department department = departmentService.getOne(departmentId);
            model.addAttribute("department", department);

            List<UserFeedbackOfBrandOwner> userFeedbackList = departmentService.getAllDepartmentFeedbackOfBrandOwner(departmentId);
            model.addAttribute("userFeedbackList", userFeedbackList);
            return "brand-owner/gym-brand-feedback-list-detail";
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

    //Service Management
    @GetMapping("/service/list")
    public String getListOfService(Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("userInfo");
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            // Get all list service
            List<BrandAmenitie> brandAmenitieList = brandAmenitieService.getAllByBrandID(brandId);
            model.addAttribute("brandAmenitiesList", brandAmenitieList);
            return "brand-owner/gym-brand-service-list";
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

    @PostMapping("/service/details")
    public String getServiceDetails(@RequestParam("id") int id, Model model) {
        try {
            BrandAmenitie brandAmenitie = brandAmenitieService.getAmenitieDetail(id);
            model.addAttribute("brandAmenitie", brandAmenitie);
            return "brand-owner/gym-brand-service-detail";
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

    @PostMapping("/service/update")
    public String updateServiceDetails(@Valid @ModelAttribute("brandAmenitie") ServiceUpdateDTO serviceUpdateDTO,
                                       BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "brand-owner/gym-brand-service-detail"; // Trả về trang form và hiển thị thông báo lỗi
            }

            BrandAmenitie brandAmenitie = new BrandAmenitie();
            brandAmenitie.setPhotoUrl(serviceUpdateDTO.getPhotoUrl());
            brandAmenitie.setAmenitieName(serviceUpdateDTO.getAmenitieName());
            brandAmenitie.setDescription(serviceUpdateDTO.getDescription());
            brandAmenitie.setStatus(serviceUpdateDTO.getStatus() ? 1 : 0);
            brandAmenitie.setAmenitieId(serviceUpdateDTO.getAmenitieId());

            // Thực hiện cập nhật dịch vụ trong CSDL
            brandAmenitieService.updateBrandAmenitie(brandAmenitie);
            Thread.sleep(1300);
            return "redirect:/brand-owner/service/list";
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/service/add")
    public String addService(@ModelAttribute("createService") ServiceCreateDTO serviceCreateDTO) {
        return "brand-owner/gym-brand-service-add";
    }

    @PostMapping("/service/add")
    public String createService(@Valid @ModelAttribute("createService") ServiceCreateDTO serviceCreateDTO, BindingResult bindingResult, HttpSession session) {
      try {
          if (bindingResult.hasErrors()) {
              return "brand-owner/gym-brand-service-add"; // Trả về trang form và hiển thị thông báo lỗi
          }
          User user = (User) session.getAttribute("userInfo");
          // Get brandId by brandOwnerId
          Brand brand = brandService.getBrandDetail(user.getUserId());
          int brandId = brand.getBrandId();

          BrandAmenitie brandAmenitie = new BrandAmenitie();
          brandAmenitie.setBrandId(brandId);
          brandAmenitie.setAmenitieName(serviceCreateDTO.getAmenitieName());
          brandAmenitie.setPhotoUrl(serviceCreateDTO.getPhotoUrl());
          brandAmenitie.setDescription(serviceCreateDTO.getDescription());
          brandAmenitie.setStatus(1);
          brandAmenitieService.createBrandAmenitie(brandAmenitie);
          Thread.sleep(1300);

          return "redirect:/brand-owner/service/list";
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
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
    }

    //Gym Owner Management
    @GetMapping("/gym-owner/list")
    public String getListOfGymOwner(HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("userInfo");
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();

            List<GymOwnerListDTO> gymOwnerList = userService.getAllAccountByBrandId(brandId);
            model.addAttribute("gymOwnerList", gymOwnerList);

            return "brand-owner/gym-brand-owner-list";
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

    @PostMapping("/gym-owner/details")
    public String getGymOwnerDetails(@RequestParam("id1") int userId, @RequestParam("id2") int userDetailId,
                                     Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("userInfo");
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            List<DepartmentListByBrandDTO> departmentList = departmentService.getAllDepartmentListOfBrand(brandId);


            UserDetail ud = userService.getUserDetailByUserDetailId(userDetailId);

            List<DepartmentListByBrandDTO> filteredList = departmentList.stream()
                    .filter(dto -> dto.getUserName() == null || dto.getDepartmentId() == ud.getGymDepartmentId())
                    .collect(Collectors.toList());

            GymOwnerUpdateDTO gymOwnerUpdateDTO = new GymOwnerUpdateDTO();
            gymOwnerUpdateDTO.setUserDetailId(ud.getUserDetailId());
            gymOwnerUpdateDTO.setFirstName(ud.getFirstName());
            gymOwnerUpdateDTO.setLastName(ud.getLastName());
            gymOwnerUpdateDTO.setEmail(ud.getEmail());
            gymOwnerUpdateDTO.setPhone(ud.getPhoneNumber());
            gymOwnerUpdateDTO.setAddress(ud.getAddress());
            gymOwnerUpdateDTO.setDateOfBirth(ud.getDateOfBirth());
            gymOwnerUpdateDTO.setGender(ud.getGender());
            gymOwnerUpdateDTO.setImageUrl(ud.getImageUrl());
            gymOwnerUpdateDTO.setIdCard(ud.getSecurityId());
            gymOwnerUpdateDTO.setUserDeleted(ud.isUserDeleted());
            gymOwnerUpdateDTO.setDepartmentId(ud.getGymDepartmentId());
            gymOwnerUpdateDTO.setOldDepartmentId(ud.getGymDepartmentId());
            gymOwnerUpdateDTO.setUserId(userId);
            gymOwnerUpdateDTO.setOldEmail(ud.getEmail());

            model.addAttribute("gymOwner", gymOwnerUpdateDTO);
            model.addAttribute("filteredList", filteredList);
            return "brand-owner/gym-brand-owner-detail";
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

    @PostMapping("/gym-owner/update")
    public String updateGymOwnerDetails(@Valid @ModelAttribute("gymOwner")GymOwnerUpdateDTO gymOwnerUpdateDTO,
                                        BindingResult bindingResult) {
try {
    if (!gymOwnerUpdateDTO.getEmail().equals(gymOwnerUpdateDTO.getOldEmail())) {
        if (userService.checkEmailExist(gymOwnerUpdateDTO.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email đã tồn tại");
        }
    }

    if (bindingResult.hasErrors()) {
        return "brand-owner/gym-brand-owner-detail";
    }
    UserDetail userDetail = new UserDetail();
    userDetail.setUserDetailId(gymOwnerUpdateDTO.getUserDetailId());
    userDetail.setFirstName(gymOwnerUpdateDTO.getFirstName());
    userDetail.setLastName(gymOwnerUpdateDTO.getLastName());
    userDetail.setEmail(gymOwnerUpdateDTO.getEmail());
    userDetail.setDateOfBirth(gymOwnerUpdateDTO.getDateOfBirth());
    userDetail.setAddress(gymOwnerUpdateDTO.getAddress());
    userDetail.setPhoneNumber(gymOwnerUpdateDTO.getPhone());
    userDetail.setGender(gymOwnerUpdateDTO.getGender());
    userDetail.setImageUrl(gymOwnerUpdateDTO.getImageUrl());
    userDetail.setSecurityId(gymOwnerUpdateDTO.getIdCard());

    // Update user detail
    userService.updateUserDetail(userDetail);

    userService.updateUserStatusByUserId(gymOwnerUpdateDTO.getUserId(), gymOwnerUpdateDTO.isUserDeleted() ? 1 : 0);

    if (gymOwnerUpdateDTO.getDepartmentId() == -1) {
        if (gymOwnerUpdateDTO.getOldDepartmentId() != 0) {
            departmentService.updateDepartmentGymOwner(gymOwnerUpdateDTO.getOldDepartmentId(), 0);
        }
        return "redirect:/brand-owner/gym-owner/list";
    }
    // Nếu trạng thái là 0 thì đá khỏi cơ sở
    if (gymOwnerUpdateDTO.isUserDeleted() && gymOwnerUpdateDTO.getOldDepartmentId() != 0) {
        departmentService.updateDepartmentGymOwner(gymOwnerUpdateDTO.getOldDepartmentId(), 0);
    }

    // Update user department
    if (!Objects.equals(gymOwnerUpdateDTO.getOldDepartmentId(), gymOwnerUpdateDTO.getDepartmentId())) {
        departmentService.updateDepartmentGymOwner(gymOwnerUpdateDTO.getDepartmentId(), gymOwnerUpdateDTO.getUserId());
    }

    if (gymOwnerUpdateDTO.getOldDepartmentId() != 0 && !gymOwnerUpdateDTO.getOldDepartmentId().equals(gymOwnerUpdateDTO.getDepartmentId())) {
        departmentService.updateDepartmentGymOwner(gymOwnerUpdateDTO.getOldDepartmentId(), 0);
    }
    Thread.sleep(1300);
    return "redirect:/brand-owner/gym-owner/list";
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
} catch (InterruptedException e) {
    throw new RuntimeException(e);
}
    }

    @GetMapping("/gym-owner/add")
    public String addGymOwner(@ModelAttribute("gymOwner")GymOwnerCreateDTO gymOwnerCreateDTO) {
        return "brand-owner/gym-brand-owner-add";
    }

    @PostMapping("/gym-owner/add")
    public String createGymOwner(@Valid @ModelAttribute("gymOwner") GymOwnerCreateDTO gymOwnerCreateDTO,
                                 BindingResult bindingResult, HttpSession session) {
        try {
            if (userService.checkEmailExist(gymOwnerCreateDTO.getEmail())) {
                bindingResult.rejectValue("email", "error.email", "Email đã tồn tại");
            }

            if (bindingResult.hasErrors()) {
                return "brand-owner/gym-brand-owner-add";
            }
            User user = (User) session.getAttribute("userInfo");
            UserDetail userDetail = new UserDetail();
            userDetail.setFirstName(gymOwnerCreateDTO.getFirstName());
            userDetail.setLastName(gymOwnerCreateDTO.getLastName());
            userDetail.setEmail(gymOwnerCreateDTO.getEmail());
            userDetail.setPhoneNumber(gymOwnerCreateDTO.getPhone());
            userDetail.setAddress(gymOwnerCreateDTO.getAddress());
            userDetail.setDateOfBirth(gymOwnerCreateDTO.getDateOfBirth());
            userDetail.setImageUrl(gymOwnerCreateDTO.getImageUrl());
            userDetail.setSecurityId(gymOwnerCreateDTO.getIdCard());
            userDetail.setGender(gymOwnerCreateDTO.getGender());

            // Insert into User Detail
            userService.insertIntoUserDetail(userDetail);
            // Get last insert user detail id
            int userDetailId = userService.getLastInsertUserDetailId(userDetail);
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            String brandName = brand.getBrandName();
            // Get number of account gym-owner by brand id
            int numberOfAccountCreated = userService.getNumberOfAccountCreatedByBrandId(brandId);
            // Create userName
            String accountName = "fp_" + brandName.replaceAll("\\s+", "") + "_" + ++numberOfAccountCreated + "_" + gymOwnerCreateDTO.getUsername();
            // Create password random
            String randomPassword = WebUtil.generateRandomPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(randomPassword);
            // Create user create time
            long userCreateTimeLong = System.currentTimeMillis();
            String createTime = Long.toString(userCreateTimeLong);
            // Create user_deleted = 0;
            boolean userDelete = false;
            // Create new User
            User newUser = new User();
            newUser.setUserAccount(accountName);
            newUser.setUserCreateTime(userCreateTimeLong);
            newUser.setUserPassword(hashedPassword);
            newUser.setUserDeleted(userDelete);
            newUser.setUserDetailId(userDetailId);
            newUser.setCreatedBy(user.getUserId());
            // Insert into User
            userService.insertIntoUser(newUser);
            // Get last user insert id
            int userInsertId = userService.getLastUserInsertId(newUser);
            // Insert into user_role
            userService.insertIntoUserRole(userInsertId, 2);
            // Insert into wallet
            walletService.insertWallet(userInsertId, 0);

            // Send email to user email
            // Todo: trang tri trang html
            emailService.send("Test", "Account: " + accountName + ", Password: " + randomPassword,
                    userDetail.getEmail());
            return "redirect:/brand-owner/gym-owner/list";
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

    //Gym Plans Management
    //Flexible Plans
    @GetMapping("/gym-plans/flexible/list")
    public String getListOfFlexibleGymPlans(HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("userInfo");
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            // Get List Of flexible gym plan by brand id
            List<BrandGymPlanFlexDTO> listFlexGymPlan = gymPlanService.getAllGymPlanFlexByBrandId(brandId);
            // Send to front
            model.addAttribute("listFlexGymPlan", listFlexGymPlan);
            return "brand-owner/gym-brand-plan-flexible-list";
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

    @PostMapping("/gym-plans/flexible/details")
    public String getFlexibleGymPlanDetails(@RequestParam("id") int gymPlanId, Model model) {
        try {
            BrandUpdateGymPlanFlexDTO brandUpdateGymPlanFlexDTO = gymPlanService.getGymPlanFlexDetail(gymPlanId);
            model.addAttribute("b", brandUpdateGymPlanFlexDTO);
            return "brand-owner/gym-brand-plan-flexible-detail";
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

    @PostMapping("/gym-plans/flexible/update")
    public String updateFlexibleGymPlanDetails(@Valid @ModelAttribute("b") BrandUpdateGymPlanFlexDTO brandDetails,
                                               BindingResult bindingResult) {
        try {
            if (gymPlanService.checkGymPlanInDepartmentUse(brandDetails.getGymPlanId()) > 0 && brandDetails.getStatus() == 2) {
                bindingResult.rejectValue("status", "error.status", "Đang có cơ sở chọn gói tập này, không thể ngưng hoạt động gói tập");
            }

            if (bindingResult.hasErrors()) {
                return "brand-owner/gym-brand-plan-flexible-detail";
            }

            gymPlanService.updateGymPlanFlex(brandDetails);

            Thread.sleep(1500);
            return "redirect:/brand-owner/gym-plans/flexible/list";
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/gym-plans/flexible/add")
    public String addFlexibleGymPlan(Model model) {
        try {
            model.addAttribute("brandCreateGymPlanFlexDTO", new BrandCreateGymPlanFlexDTO());
            Thread.sleep(1500);
            return "brand-owner/gym-brand-plan-flexible-add";
        }catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/gym-plans/flexible/add")
    public String createFlexibleGymPlan(@Valid BrandCreateGymPlanFlexDTO brandCreateGymPlanFlexDTO,
                                        BindingResult bindingResult, HttpSession session) {
        try {
            if (bindingResult.hasErrors()) {
                return "brand-owner/gym-brand-plan-flexible-add";
            }

            // Create gym plan flexible
            User user = (User) session.getAttribute("userInfo");
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            brandCreateGymPlanFlexDTO.setBrandId(brandId);
            brandCreateGymPlanFlexDTO.setStatus(1);
            gymPlanService.createGymPlanFlex(brandCreateGymPlanFlexDTO);
            Thread.sleep(1500);
            return "redirect:/brand-owner/gym-plans/flexible/list";
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Fixed Plans
    @GetMapping("/gym-plans/fixed/list")
    public String getListOfFixedGymPlans(HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("userInfo");
            // Get brandId by brandOwnerId
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            // Get List Of flexible gym plan by brand id
            List<BrandGymPlanFixedDTO> listFixedGymPlan = gymPlanService.getAllGymPlanFixedByBrandId(brandId);
            // Send to front
            model.addAttribute("listFixedGymPlan", listFixedGymPlan);
            return "brand-owner/gym-brand-plan-fixed-list";
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

    @PostMapping("/gym-plans/fixed/details")
    public String getFixedGymPlanDetails(@RequestParam("id") int gymPlanId, Model model) {
        try {
            BrandUpdateGymPlanFixedDTO brandUpdateGymPlanFixedDTO = gymPlanService.getGymPlanFixedDetail(gymPlanId);
            model.addAttribute("b", brandUpdateGymPlanFixedDTO);
            return "brand-owner/gym-brand-plan-fixed-detail";
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

    @PostMapping("/gym-plans/fixed/update")
    public String updateFixedGymPlanDetails(@Valid @ModelAttribute("b") BrandUpdateGymPlanFixedDTO brandDetails,
                                            BindingResult bindingResult) {
        try {
            if (gymPlanService.checkGymPlanInDepartmentUse(brandDetails.getGymPlanId()) > 0 && brandDetails.getStatus() == 2) {
                bindingResult.rejectValue("status", "error.status", "Đang có cơ sở chọn gói tập này, không thể ngưng hoạt động gói tập");
            }

            if (bindingResult.hasErrors()) {
                return "brand-owner/gym-brand-plan-fixed-detail";
            }

            gymPlanService.updateGymPlanFixed(brandDetails);
            Thread.sleep(1500);
            return "redirect:/brand-owner/gym-plans/fixed/list";
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/gym-plans/fixed/add")
    public String addFixedGymPlan(Model model) {
        try {
            model.addAttribute("brandCreateGymPlanFixedDTO", new BrandCreateGymPlanFixedDTO());
            return "brand-owner/gym-brand-plan-fixed-add";
        }catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @PostMapping("/gym-plans/fixed/add")
    public String createFixedGymPlan(@Valid BrandCreateGymPlanFixedDTO brandCreateGymPlanFixedDTO,
                                     BindingResult bindingResult, HttpSession session) {
        try {
            if (bindingResult.hasErrors()) {
                return "brand-owner/gym-brand-plan-fixed-add";
            }

            User user = (User) session.getAttribute("userInfo");
            Brand brand = brandService.getBrandDetail(user.getUserId());
            int brandId = brand.getBrandId();
            brandCreateGymPlanFixedDTO.setBrandId(brandId);
            brandCreateGymPlanFixedDTO.setStatus(1);
            gymPlanService.createGymPlanFixed(brandCreateGymPlanFixedDTO);
            Thread.sleep(1500);
            return "redirect:/brand-owner/gym-plans/fixed/list";
        }catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/withdrawal/list")
    public String getWithdrawal(Model model, HttpSession session){
        try {
            User user = (User) session.getAttribute("userInfo");
            List<RequestWithdrawHistory> requestWithdrawHistoryListPending = requestWithdrawHistoryService.getAllByUserIdAndStatus(user.getUserId(), "Đang xử lý");
            List<RequestWithdrawHistory> requestWithdrawHistoryListAll = requestWithdrawHistoryService.getAllByUserId(user.getUserId());
            RequestHistoryStats requestHistoryStats = requestWithdrawHistoryService.getStatsByUserId(user.getUserId());
            List<CreditCard> creditCardList = creditCardService.getAllByUserId(user.getUserId());
            double userBalance = walletService.getBalanceByUserId(user.getUserId());

            Brand b = brandService.getBrandDetail(user.getUserId());
            int moneyPercent = brandService.getBrandMoneyPercent(b.getBrandId());

            model.addAttribute("moneyPercent", moneyPercent);
            model.addAttribute("userBalance", userBalance);
            model.addAttribute("requestWithdrawHistoryListPending", requestWithdrawHistoryListPending);
            model.addAttribute("requestWithdrawHistoryListAll", requestWithdrawHistoryListAll);
            model.addAttribute("requestHistoryStats", requestHistoryStats);
            model.addAttribute("creditCardList", creditCardList);
            return "brand-owner/gym-brand-withdrawal-list";
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

    @PostMapping("/withdrawal/add")
    public String addWithdrawal(@RequestParam int cardId, @RequestParam double creditAmount,
                                @RequestParam  double moneyAmount, HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("userInfo");
            double userBalance = walletService.getBalanceByUserId(user.getUserId());

            Brand b = brandService.getBrandDetail(user.getUserId());
            int moneyPercent = brandService.getBrandMoneyPercent(b.getBrandId());
            if (creditAmount > userBalance) {
                model.addAttribute("errorCredit", "Số Credit muốn rút không được lớn hơn số dư hiện tại (Credit)");
            }else {
                RequestWithdrawHistory requestWithdrawHistory = new RequestWithdrawHistory();
                requestWithdrawHistory.setCreditCardId(cardId);
                requestWithdrawHistory.setAmountCredit((long) creditAmount);
                requestWithdrawHistory.setActualMoney((long) moneyAmount);
                requestWithdrawHistory.setWithdrawalTime(new Timestamp(System.currentTimeMillis()));
                requestWithdrawHistory.setWithdrawalCode(WebUtil.generateUniqueTransactionCode());
                requestWithdrawHistory.setMoneyPercent(moneyPercent);
                requestWithdrawHistory.setStatus("Đang xử lý");
                requestWithdrawHistoryService.create(requestWithdrawHistory);
            }
            TimeUnit.SECONDS.sleep(2);
            return "redirect:/brand-owner/withdrawal/list";
        } catch (InterruptedException ex) {
            // Handle InterruptedException
            logger.error("InterruptedException occurred", ex);
            return "error/sleep-error";
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

    @GetMapping("/withdrawal/card/add")
    public String getWithdrawalCard(Model model, HttpSession session){
        try {
            User user = (User) session.getAttribute("userInfo");
            // Get all credit card by user id
            List<CreditCard> creditCardList = creditCardService.getAllByUserId(user.getUserId());
            model.addAttribute("creditCardList", creditCardList);
            return "brand-owner/gym-brand-withdrawal-card-add";
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


    @PostMapping("/withdrawal/card/add-bank-card")
    public ResponseEntity<Integer> addBankCard(@Valid @RequestBody CreditCard creditCard,
                                               HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.badRequest().build();
        }

        try {
            User user = (User) session.getAttribute("userInfo");
            creditCard.setUserId(user.getUserId());
            creditCard.setStatus("Đang hoạt động");
            if(creditCardService.checkCreditCardExist(creditCard, user.getUserId())) {
                return ResponseEntity.ok(-1);
            }

            int rowAffect = creditCardService.createCreditCard(creditCard);
            if(rowAffect > 0) {
                int lastCreditCardId = creditCardService.getLastCreditCardId();
                return ResponseEntity.ok(lastCreditCardId);
            }
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/withdrawal/card/details")
    public ResponseEntity<CreditCard> getBankCardDetails(@RequestParam("id") int creditCardId) {
        try {
            CreditCard creditCard = creditCardService.getOne(creditCardId);
            return ResponseEntity.ok(creditCard);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/withdrawal/card/update-bank-card")
    public ResponseEntity<Integer> updateBankCard(@Valid @RequestBody CreditCard creditCard,
                                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.badRequest().build();
        }
        int rowAffect;
        try {
            if(creditCardService.checkCreditCardExist(creditCard, creditCard.getUserId())) {
                return ResponseEntity.ok(-1);
            }

            rowAffect = creditCardService.updateCreditCard(creditCard);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rowAffect);
    }

    @PostMapping("/withdrawal/card/delete-bank-card")
    public ResponseEntity<Integer> deleteBankCard(@RequestBody Integer creditCardId) {
        int rowAffect;
        try {
            rowAffect = creditCardService.deleteCreditCard(creditCardId);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rowAffect);
    }
}
