package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.GymOwnerUpdateDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.entity.UserUpdateDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final TransactionService transactionService;
    private final UserService userService;

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
            String userEmail = userService.getUserEmailByUserId(user.getUserId());

            session.setAttribute("userEmail", userEmail);
            session.setAttribute("userTotalDeposit", userTotalDeposit);
        }
    }

    @GetMapping("/my-profile")
    public String showProfile(HttpSession session, Model model ) {
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
    }

    @PostMapping("/my-profile/update")
    public String updateGymOwnerDetails(@Valid @ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO,
                                        BindingResult bindingResult) {

        if(!userUpdateDTO.getEmail().equals(userUpdateDTO.getOldEmail())) {
            if(userService.checkEmailExist(userUpdateDTO.getEmail())) {
                bindingResult.rejectValue("email", "error.email", "Email đã tồn tại");
            }
        }

        if(bindingResult.hasErrors()) {
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
        User user = (User) session.getAttribute("userInfo");
        List<TransactionDTO> transactionDTOList = transactionService.getListTransactionByUserId(user.getUserId());
        model.addAttribute("transactionList", transactionDTOList);
        return "user/user-transaction-history";
    }

    @GetMapping("change-password")
    public String showChangePwPage() {
        return "user/user-change-password";
    }
}
