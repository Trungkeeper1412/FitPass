package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.ReCaptchaResponse;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.entity.UserRegisterDTO;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.wallet.service.WalletService;
import jakarta.servlet.http.HttpServletRequest;
import com.ks.fitpass.web.util.Email;
import com.ks.fitpass.web.util.WebUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ks.fitpass.web.util.Email;
import com.ks.fitpass.web.util.WebUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final WalletService walletService;
    private final UserRepository userRepository;
    private final Email emailService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

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

    @GetMapping("/forgot-password/reset")
    public ResponseEntity<?> forgotPwPost(@RequestParam("email") String email) {

        String randomPassword = WebUtil.generateRandomPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(randomPassword);

        boolean emailExists = userService.checkEmailExist(email);
        if(!emailExists){
            return ResponseEntity.badRequest().build();
        }

        userService.resetPassword(email, hashedPassword);

        emailService.send( "FitPass - Reset Password", "Your new password is: " +  randomPassword, email);
        return ResponseEntity.ok(email);
    }

    @GetMapping("/forgot-password")
    public String getPasswordPage(){
        return "forgot-password";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registered(@Valid @ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO,
                             @RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
                             BindingResult bindingResult,Model model,
                             RedirectAttributes redirectAttributes, HttpServletRequest request) {

        String recaptchaVerifyUrl = "https://www.google.com/recaptcha/api/siteverify";
        MultiValueMap<String, String> recaptchaRequestMap = new LinkedMultiValueMap<>();
        recaptchaRequestMap.add("secret", recaptchaSecret);
        recaptchaRequestMap.add("response", recaptchaResponse);
        recaptchaRequestMap.add("remoteip", request.getRemoteAddr());

        ResponseEntity<ReCaptchaResponse> recaptchaResponseEntity =
                restTemplate.postForEntity(recaptchaVerifyUrl, recaptchaRequestMap, ReCaptchaResponse.class);

        if (!Objects.requireNonNull(recaptchaResponseEntity.getBody()).isSuccess()) {
            bindingResult.reject("recaptchaError", "Captcha validation failed.");
            logger.error("Captcha validation failed");

        }

        if (userService.checkEmailExist(userRegisterDTO.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email đã tồn tại !");
            logger.error("Email already exists: {}", userRegisterDTO.getEmail());
        }

        if (userService.checkUsernameExist(userRegisterDTO.getUserAccount())) {
            bindingResult.rejectValue("userAccount", "error.userAccount", "Tên đăng nhập đã tồn tại !");
            logger.error("User Name already exists: {}", userRegisterDTO.getUserAccount());
        }

        if (!userRegisterDTO.getUserPassword().equals(userRegisterDTO.getReUserPassword())) {
            bindingResult.rejectValue("reUserPassword", "error.reUserPassword", "Mật khẩu và xác nhận mật khẩu không khớp !");
            logger.error("Password and Confirm Password do not match");
        }

        if (bindingResult.hasErrors()) {
            logger.error("Errors in form submission: {}", bindingResult.getAllErrors());
            model.addAttribute("userRegisterDTO", userRegisterDTO);
            return "register";
        }

        if(!bindingResult.hasErrors()){
            UserDetail userDetail = new UserDetail();
            userDetail.setFirstName(userRegisterDTO.getFirstName());
            userDetail.setLastName(userRegisterDTO.getLastName());
            userDetail.setEmail(userRegisterDTO.getEmail());
            userDetail.setPhoneNumber(userRegisterDTO.getPhoneNumber());
            userDetail.setDateOfBirth(userRegisterDTO.getDateOfBirth());
            userDetail.setGender(userRegisterDTO.getGender());
            // Insert into User Detail
            userService.insertIntoUserDetailRegister(userDetail);

            int userDetailId = userService.getLastInsertUserDetailIdRegister(userDetail);

            String password = userRegisterDTO.getUserPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            Long userCreateTimeLong = System.currentTimeMillis();
            String createTime = userCreateTimeLong.toString();
            // Create user_deleted = 0;
            boolean userDelete = false;

            // Create new User
            User newUser = new User();
            newUser.setUserAccount(userRegisterDTO.getUserAccount());
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
