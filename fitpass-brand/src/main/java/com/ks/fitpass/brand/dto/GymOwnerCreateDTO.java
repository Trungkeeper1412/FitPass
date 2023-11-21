package com.ks.fitpass.brand.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GymOwnerCreateDTO {


    @Size(min = 6, max = 50, message = "Username must be between 6 and 50 characters")
    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;

    @Size(min = 6, max = 50, message = "Username must be between 6 and 50 characters")
    @NotEmpty(message = "Last Name cannot be empty")
    @Pattern(regexp = "^(0|84)(9|3|7|8|5)\\d{8,9}$", message = "Invalid phone number format")
    private String lastName;
    

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Invalid phone number format")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email (message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    @Pattern(regexp = "^(0|84)(9|3|7|8|5)\\d{8,9}$", message = "Invalid phone number format")
    private String phone;


    @NotEmpty(message = "Address cannot be empty")
    @Size(max = 150, message = "Address must not exceed 150 characters")
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
