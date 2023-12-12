package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.ReCaptchaResponse;
import com.ks.fitpass.partner.register.dto.BecomePartnerForm;
import com.ks.fitpass.partner.register.dto.BecomePartnerRequest;
import com.ks.fitpass.partner.register.service.BecomePartnerService;
import com.ks.fitpass.core.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Objects;

@Controller
@RequestMapping("/become-a-partner")
@RequiredArgsConstructor
public class BORegisterController {
    private final BecomePartnerService becomePartnerService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;
    @GetMapping("")
    public String getBrandOwnerRegister(Model model){
        try {
            model.addAttribute("becomePartnerForm", new BecomePartnerForm());
            return "gym-brand-registration";
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

    @PostMapping("/submitForm")
    public String postBrandOwnerRegister(@Valid  @ModelAttribute BecomePartnerForm becomePartnerForm,
                                         BindingResult bindingResult, HttpServletRequest request,
                                         @RequestParam(name = "g-recaptcha-response") String recaptchaResponse){
        try {
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

            if (userService.checkEmailExist(becomePartnerForm.getContactEmail())) {
                bindingResult.rejectValue("contactEmail", "error.contactEmail", "Email đã tồn tại");
            }

            if (bindingResult.hasErrors()) {
                return "gym-brand-registration";
            }

            BecomePartnerRequest becomePartnerRequest = new BecomePartnerRequest();
            becomePartnerRequest.setBrandName(becomePartnerForm.getBrandName());
            becomePartnerRequest.setBrandOwnerName(becomePartnerForm.getBrandOwnerName());
            becomePartnerRequest.setContactNumber(becomePartnerForm.getContactNumber());
            becomePartnerRequest.setAddress(becomePartnerForm.getAddress());
            becomePartnerRequest.setWebUrl(becomePartnerForm.getWebUrl());
            becomePartnerRequest.setContactEmail(becomePartnerForm.getContactEmail());
            becomePartnerRequest.setSendRequestTime(new Timestamp(System.currentTimeMillis()));
            becomePartnerRequest.setStatus("Đang chờ xử lý");
            int row = becomePartnerService.create(becomePartnerRequest);
            return "redirect:/become-a-partner/successful";
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
    @GetMapping("/successful")
    public String getRegistrationSuccessful() {
        return "gym-brand-registration-successful";
    }

    @GetMapping("/famous-brands")
    public String getFamousBrands() {
        return "famous-brands";
    }
}
