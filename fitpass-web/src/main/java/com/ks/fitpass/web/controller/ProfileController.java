package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.entity.UserUpdateDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final TransactionService transactionService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    public ProfileController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @ModelAttribute
    public void populateModel(HttpSession session) {
        Object userInfo = session.getAttribute("userInfo");
        if (userInfo != null) {
            User user = (User) userInfo;

            Double userTotalDeposit = transactionService.getTotalAmountOfTransactionByUserId(user.getUserId());
            UserDetail userDetail = userService.getUserDetailByUserId(user.getUserId());

            session.setAttribute("userEmail", userDetail.getEmail());
            session.setAttribute("userAvatar", userDetail.getImageUrl());
            session.setAttribute("userTotalDeposit", userTotalDeposit);
        }
    }

    @GetMapping("/my-profile")
    public String showProfile(HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("userInfo");
            UserDetail ud = userService.getUserDetailByUserId(user.getUserId());

            UserUpdateDTO userUpdateDTO = new UserUpdateDTO();

            userUpdateDTO.setUserDetailId(ud.getUserDetailId());
            userUpdateDTO.setFirstName(ud.getFirstName());
            userUpdateDTO.setLastName(ud.getLastName());
            userUpdateDTO.setEmail(ud.getEmail());
            userUpdateDTO.setPhoneNumber(ud.getPhoneNumber());
            userUpdateDTO.setAddress(ud.getAddress());
            userUpdateDTO.setDateOfBirth(ud.getDateOfBirth());
            userUpdateDTO.setGender(ud.getGender());
            userUpdateDTO.setImageUrl(ud.getImageUrl());
            userUpdateDTO.setUserDeleted(ud.isUserDeleted());
            userUpdateDTO.setUserId(user.getUserId());
            userUpdateDTO.setOldEmail(ud.getEmail());

            model.addAttribute("userUpdateDTO", userUpdateDTO);
            return "user/user-profile";
        }  catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return "error/no-data";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @PostMapping("/my-profile/update")
    public String updateGymOwnerDetails(@Valid @ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO,
                                        BindingResult bindingResult) {
        try {
            if (!userUpdateDTO.getEmail().equals(userUpdateDTO.getOldEmail())) {
                if (userService.checkEmailExist(userUpdateDTO.getEmail())) {
                    bindingResult.rejectValue("email", "error.email", "Email đã tồn tại");
                }
            }

            if (bindingResult.hasErrors()) {
                return "user/user-profile";
            }

            UserDetail userDetail = new UserDetail();
            userDetail.setUserDetailId(userUpdateDTO.getUserDetailId());
            userDetail.setFirstName(userUpdateDTO.getFirstName());
            userDetail.setLastName(userUpdateDTO.getLastName());
            userDetail.setEmail(userUpdateDTO.getEmail());
            userDetail.setDateOfBirth(userUpdateDTO.getDateOfBirth());
            userDetail.setAddress(userUpdateDTO.getAddress());
            userDetail.setPhoneNumber(userUpdateDTO.getPhoneNumber());
            userDetail.setGender(userUpdateDTO.getGender());
            userDetail.setImageUrl(userUpdateDTO.getImageUrl());

            // Update user detail
            userService.updateUserDetail(userDetail);

            return "redirect:/profile/my-profile";
        }catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return "error/no-data";
        }  catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }


    @GetMapping("/my-notifications")
    public String showNotificationPage() {
        return "user/user-notification";
    }

    @GetMapping("/deposit")
    public String showDepositPage() {
        return "user/user-deposit";
    }

    @GetMapping("/transaction-history")
    public String showTransactionHistoryPage(Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("userInfo");
            List<TransactionDTO> transactionDTOList = transactionService.getListTransactionByUserId(user.getUserId());
            model.addAttribute("transactionList", transactionDTOList);
            return "user/user-transaction-history";
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

    @GetMapping("/change-password")
    public String showChangePwPage() {
        return "user/user-change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("userInfo");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Kiểm tra mật khẩu hiện tại
            if (!passwordEncoder.matches(currentPassword, user.getUserPassword())) {
                model.addAttribute("error", "Mật khẩu hiện tại không đúng");
                return "user/user-change-password";
            }
            // Kiểm tra mật khẩu mới và xác nhận mật khẩu
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp");
                return "user/user-change-password";
            }
            String hashedPassword = passwordEncoder.encode(newPassword);
            // Cập nhật mật khẩu mới
            userService.updatePassword(hashedPassword, user.getUserId());

            // Redirect hoặc hiển thị thông báo thành công
            model.addAttribute("success", true);
            return "redirect:/profile/change-password";
        }
        catch (Exception e) {
            // Handle the exception, you can log it or return an error response
            logger.error("Exception occurred", e);
            model.addAttribute("error", "Có lỗi xảy ra khi thay đổi mật khẩu");
            return "user/user-change-password";
        }
    }

}
