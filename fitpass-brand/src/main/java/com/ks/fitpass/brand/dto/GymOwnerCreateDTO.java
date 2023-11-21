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
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Invalid First Name format")
    private String firstName;

    @Size(min = 6, max = 50, message = "Last Name must be between 6 and 50 characters")
    @NotEmpty(message = "Last Name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Invalid First Name format")
    private String lastName;

    @Size(min = 6, max = 50, message = "Username must be between 6 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Invalid phone number format")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Email (message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    @Pattern(regexp = "^(0|84)(9|3|7|8|5)\\d{8,9}$", message = "Invalid phone number format")
    private String phone;

    @NotEmpty(message = "Address cannot be empty")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotNull(message = "Date of Birth cannot be null")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "ID Card cannot be empty")
    @NotEmpty(message = "ID Card cannot be empty")
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
