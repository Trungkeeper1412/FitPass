package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.entity.UserUpdateDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final WalletService walletService;

    @GetMapping("/")
    public String checkFirstTimeLogin(HttpSession session){
        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            return "redirect:/homepage";
        }
        return "landing";
    }

    @GetMapping("/landing-page")
    public String getLandingPage(){
        return "landing";
    }


    @GetMapping("/login")
    public String login(HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            return "redirect:/homepage";
        }
        return "login-register";
    }

    @GetMapping("/show-info")
    public String admin(Model model, HttpSession session, Authentication authentication) {
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("userInfo", user);
        model.addAttribute("userRole", authentication.getAuthorities());
        return "show-info";
    }

    @GetMapping("/forgot-password")
    public String forgotPw() {
        return "forgot-password";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userUpdateDTO", new UserUpdateDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registered(@Valid @ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO,
                             BindingResult bindingResult,Model model, RedirectAttributes redirectAttributes) {

        if (userService.checkEmailExist(userUpdateDTO.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email đã tồn tại !");
        }

        if(!userUpdateDTO.getUserPassword().equals(userUpdateDTO.getReUserPassword())){
            bindingResult.rejectValue("reUserPassword", "error.reUserPassword", "Mật khẩu và xác nhận mật khẩu không khớp !");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("userUpdateDTO", userUpdateDTO);
            return "register";
        }

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(userUpdateDTO.getFirstName());
        userDetail.setLastName(userUpdateDTO.getLastName());
        userDetail.setEmail(userUpdateDTO.getEmail());
        userDetail.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        userDetail.setDateOfBirth(userUpdateDTO.getDateOfBirth());
        userDetail.setGender(userUpdateDTO.getGender());
        // Insert into User Detail
        userService.insertIntoUserDetailRegister(userDetail);

        int userDetailId = userService.getLastInsertUserDetailIdRegister(userDetail);

        String password = userUpdateDTO.getUserPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        Long userCreateTimeLong = System.currentTimeMillis();
        String createTime = userCreateTimeLong.toString();
        // Create user_deleted = 0;
        boolean userDelete = false;

        // Create new User
        User newUser = new User();
        newUser.setUserAccount(userUpdateDTO.getUserAccount());
        newUser.setUserCreateTime(userCreateTimeLong);
        newUser.setUserPassword(hashedPassword);
        newUser.setUserDeleted(userDelete);
        newUser.setUserDetailId(userDetailId);
        newUser.setCreatedBy(1);
        // Insert into User
        userService.insertIntoUser(newUser);

        // Get last user insert id
        int userInsertId = userService.getLastUserInsertId(newUser);
        // Insert into user_role
        userService.insertIntoUserRole(userInsertId, 4);
        // Insert into wallet
        walletService.insertWallet(userInsertId, 0);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/register";
    }
}
