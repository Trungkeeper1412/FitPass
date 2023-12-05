package com.ks.fitpass.become_a_partner.dto;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Data
public class BecomePartnerForm {
    @NotEmpty(message = "Vui lòng nhập tên cơ sở !")
    @Size(max = 150, message = "Tên cơ sở không được vượt quá 150 kí tự !")
    private String brandName;

    @Size(max = 100, message = "Tên của bạn không được vượt quá 100 kí tự !")
    @NotEmpty(message = "Vui lòng nhập họ của bạn !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Tên của bạn không được chứa kí tự đặc biệt !")
    private String brandOwnerName;

    @NotEmpty(message = "Vui lòng nhập số điện thoại !")
    @ValidatePhone(message = "Số điện thoại không đúng định dạng !")
    private String contactNumber;

    @NotEmpty(message = "Vui lòng nhập địa chỉ !")
    @Size(max = 150, message = "Địa chỉ không được vượt quá 150 kí tự !")
    private String address;

    @Size(max = 250, message = "Địa chỉ trang wed không được vượt quá 250 kí tự !")
    private String webUrl;

    @NotEmpty(message = "Vui lòng nhập email !")
    @Email (message = "Vui lòng nhập địa chỉ email hợp lệ !")
    private String contactEmail;


    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = PhoneNumberValidator.class)
    public @interface ValidatePhone {
        String message() default "Số điện thoại không đúng định dạng !";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
