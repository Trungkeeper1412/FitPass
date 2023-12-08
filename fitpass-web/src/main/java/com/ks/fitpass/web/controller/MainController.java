package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.entity.UserUpdateDTO;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final WalletService walletService;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @InitBinder("userUpdateDTO")
    public void initUserUpdateDTOBinder(WebDataBinder binder) {
        logger.info("Customizing data binding for userUpdateDTO");
        binder.setDisallowedFields("imageUrl", "address");
    }

    @GetMapping("/")
    public String checkFirstTimeLogin(HttpSession session){
        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            String userRole = (String) session.getAttribute("userRole");
            if (userRole != null){
                switch (userRole){
                    case "ADMIN":
                        return "redirect:/admin/index";
                    case "GYM_OWNER":
                        return "redirect:/gym-owner/index";
                    case "EMPLOYEE":
                        try {
                            Integer departmentId = userRepository.getDepartmentIdByEmployeeId(user.getUserId());
                            if (departmentId == null || departmentId == 0) {
                                return "error/403";
                            } else {
                                return "redirect:/employee/history?id=" + departmentId;
                            }
                        } catch (EmptyResultDataAccessException e) {
                            return "error/403";
                        }
                    case "USER":
                        return "redirect:/homepage";
                    case "BRAND_OWNER":
                        return "redirect:/brand-owner/index";
                }
            }
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
    public String registered(@ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO,
                             BindingResult bindingResult,Model model, RedirectAttributes redirectAttributes) {
        logger.warn("The register has been initiated");

        if (userService.checkEmailExist(userUpdateDTO.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email đã tồn tại !");
            logger.error("Email already exists: {}", userUpdateDTO.getEmail());
        }

        if (!userUpdateDTO.getUserPassword().equals(userUpdateDTO.getReUserPassword())) {
            bindingResult.rejectValue("reUserPassword", "error.reUserPassword", "Mật khẩu và xác nhận mật khẩu không khớp !");
            logger.error("Password and Confirm Password do not match");
        }

        if (bindingResult.hasErrors()) {
            logger.error("Errors in form submission: {}", bindingResult.getAllErrors());
            model.addAttribute("userUpdateDTO", userUpdateDTO);
            return "register";
        }

        if(!bindingResult.hasErrors()){
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
            redirectAttributes.addFlashAttribute("successRegister", true);
            return "redirect:/login";
        }
        return "register";
    }
}
