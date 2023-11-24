package com.ks.fitpass.department.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Data
public class EmployeUpdateDTO {
    private int userDetailId;
    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    @Pattern(regexp = "^[0-9]*$", message = "Invalid phone number format")
    private String phone;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotNull(message = "Date of Birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    //    @NotEmpty(message = "ID Card cannot be empty")
//    @Pattern(regexp = "^[0-9]*$", message = "Invalid ID Card format")

    @ValidateIdCard(message = "Số căn cước công dân không hợp lệ !")
    private String idCard;

    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    @NotEmpty(message = "Image url cannot be empty")
    private String imageUrl;

    private boolean userDeleted;


    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = ValidateIdCardValidator.class)
    public @interface ValidateIdCard {
        String message() default "Số căn cước công dân không hợp lệ !";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

}
