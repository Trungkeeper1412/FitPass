package com.ks.fitpass.department.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class EmployeeCreateDTO {
    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    @Pattern(regexp = "^[0-9]*$", message = "Invalid phone number format")
    private String phone;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotNull(message = "Date of Birth cannot be null")
    private LocalDate dateOfBirth;

    //    @NotEmpty(message = "ID Card cannot be empty")
//    @Pattern(regexp = "^[0-9]*$", message = "Invalid ID Card format")
//    @Size(min = 9, max = 12, message = "ID Card length must be between 9 and 12 characters")
    private String idCard;

    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    @NotEmpty(message = "Image url cannot be empty")
    private String imageUrl;
}
