package com.ks.fitpass.web.controller;

import com.ks.fitpass.become_a_partner.dto.BecomePartnerForm;
import com.ks.fitpass.become_a_partner.dto.BecomePartnerRequest;
import com.ks.fitpass.become_a_partner.service.BecomePartnerService;
import com.ks.fitpass.core.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/become-a-partner")
@RequiredArgsConstructor
public class BORegisterController {
    private final BecomePartnerService becomePartnerService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
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
                                         BindingResult bindingResult){
        try {
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
