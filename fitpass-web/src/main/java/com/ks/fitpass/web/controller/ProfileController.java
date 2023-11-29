package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public void populateModel(HttpSession session, Model model) {
        Object userInfo = session.getAttribute("userInfo");
        if (userInfo != null) {
            User user = (User) userInfo;

            Double userTotalDeposit = transactionService.getTotalAmountOfTransactionByUserId(user.getUserId());
            String userEmail = userService.getUserEmailByUserId(user.getUserId());

            model.addAttribute("userEmail", userEmail);
            session.setAttribute("userTotalDeposit", userTotalDeposit);
        }
    }

    @GetMapping("/my-profile")
    public String showProfile() {
        return "user/user-profile";
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
