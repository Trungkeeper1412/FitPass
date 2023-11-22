package com.ks.fitpass.brand.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateIdCardValidatorForCreateDTO implements ConstraintValidator<GymOwnerCreateDTO.ValidateIdCard, String>{

    @Override
    public void initialize(GymOwnerCreateDTO.ValidateIdCard constraintAnnotation) {
        // Initialization, if needed
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() != 12) {
            return false; // Invalid length
        }

        // Lấy mã tỉnh từ số căn cước
        String provinceCode = value.substring(0, 3);

        // Lấy số tiếp theo sau mã tỉnh
        char validCenturyGenderCodes = value.charAt(3);

        // Danh sách mã tỉnh hợp lệ
        String[] validProvinceCodes = {"001", "002", "004", "006", "008", "010", "011", "012", "014",
                "015", "017", "019", "020", "022", "024", "025", "026", "027", "030", "031", "033", "034",
                "035", "036", "037", "038", "040", "042", "044", "045", "046", "048", "049", "051", "052",
                "054", "056", "058", "060", "062", "064", "066", "067", "068", "070", "072", "074", "075",
                "077", "079", "080", "082", "083", "084", "086", "087", "089", "091", "092", "093", "094",
                "095", "096"};

        // Kiểm tra mã tỉnh
        if (!isValidProvinceCode(provinceCode, validProvinceCodes)) {
            return false; // Mã tỉnh không hợp lệ
        }

        // Kiểm tra mã giới tính
        if (!String.valueOf(validCenturyGenderCodes).matches("^[0-3]$")) {
            return false;
        }

        return true; // Các mã tỉnh và số tiếp theo đều hợp lệ
    }

    private boolean isValidProvinceCode(String provinceCode, String[] validProvinceCodes) {
        for (String validCode : validProvinceCodes) {
            if (validCode.equals(provinceCode)) {
                return true;
            }
        }
        return false;
    }
}
