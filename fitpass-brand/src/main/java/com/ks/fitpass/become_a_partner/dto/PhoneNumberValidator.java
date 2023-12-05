package com.ks.fitpass.become_a_partner.dto;
import com.ks.fitpass.department.dto.UpdateGymOwnerDepartmentInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<BecomePartnerForm.ValidatePhone, String> {

    private String mobileRegex = "^(0[3|5|7|8|9])+([0-9]{8})$";
    private String hotlineRegex = "^(1800|1900)\\d{6}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null || value.isEmpty()) {
            return false;
        }

        if(!value.matches(mobileRegex) && !value.matches(hotlineRegex)) {
            return false;
        }

        return true;
    }


}