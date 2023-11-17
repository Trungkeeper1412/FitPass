package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("profile")
public class ProfileController {
    private final TransactionService transactionService;

    public ProfileController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/my-profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        List<TransactionDTO> transactionDTOList = transactionService.getListTransactionByUserId(user.getUserId());
        model.addAttribute("transactionList", transactionDTOList);
        model.addAttribute("contentName", "user-profile");

        return "user/profile";
    }

    @GetMapping("/notification-page")
    public String showNotificationPage(){
        return "user/profile :: notifications";
    }
}
