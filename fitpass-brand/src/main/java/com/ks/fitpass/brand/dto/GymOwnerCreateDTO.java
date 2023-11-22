package com.ks.fitpass.brand.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Data
public class GymOwnerCreateDTO {
    // s allows for whitespace characters, including spaces.
    // u00C0-\\u1EF9 covers the Unicode range for Vietnamese characters.

    @Size(min = 6, max = 25, message = "Họ của bạn không được vượt quá 25 kí tự !")
    @NotEmpty(message = "Vui lòng nhập họ của bạn !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Họ của bạn không được chứa kí tự đặc biệt !")
    private String firstName;

    @Size(min = 6, max = 50, message = "Tên của bạn không được vượt quá 25 kí tự !")
    @NotEmpty(message = "Vui lòng nhập tên của bạn !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Tên của bạn không được chứa kí tự đặc biệt !")
    private String lastName;

    @Size(min = 6, max = 50, message = "Tên đăng nhập phải nằm trong khoảng 6 đến 50 kí tự !")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Tên đăng nhập không bao gồm khoảng trắng và ký tự đặc biệt !")
    @NotEmpty(message = "Vui lòng nhập tên đăng nhập !")
    private String username;

    @NotEmpty(message = "Vui lòng nhập email !")
    @Email (message = "Vui lòng nhập địa chỉ email hợp lệ !")
    private String email;

    @NotEmpty(message = "Vui lòng nhập số điện thoại !")
    @Pattern(regexp = "^(0|84)(9|3|7|8|5)\\d{8,9}$", message = "Số điện thoại không đúng định dạng !")
    private String phone;

    @NotEmpty(message = "Address cannot be empty")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotNull(message = "Date of Birth cannot be null")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Vui lòng nhập số căn cước công dân !")
    @Pattern(regexp = "^[0-9]{12}$", message = "Invalid ID Card format")
    @Size(min = 12, max = 12, message = "ID Card length must be 12 characters")
    @ValidateIdCard(message = "Invalid ID Card")
    private String idCard;

    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    @NotEmpty(message = "Vui lòng chọn ảnh !")
    private String imageUrl;


    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = ValidateIdCardValidator.class)
    public @interface ValidateIdCard {
        String message() default "Invalid ID Card";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
