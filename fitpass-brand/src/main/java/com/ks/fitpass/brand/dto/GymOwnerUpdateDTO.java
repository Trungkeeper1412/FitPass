package com.ks.fitpass.brand.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class GymOwnerUpdateDTO {
    @NotNull
    private Integer userDetailId;

    @Size(min = 6, max = 25, message = "Họ của bạn không được vượt quá 25 kí tự !")
    @NotEmpty(message = "Vui lòng nhập họ của bạn !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Họ của bạn không được chứa kí tự đặc biệt !")
    private String firstName;

    @Size(min = 6, max = 50, message = "Tên của bạn không được vượt quá 25 kí tự !")
    @NotEmpty(message = "Vui lòng nhập tên của bạn !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Tên của bạn không được chứa kí tự đặc biệt !")
    private String lastName;

    @NotEmpty(message = "Vui lòng nhập email !")
    @Email (message = "Vui lòng nhập địa chỉ email hợp lệ !")
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
//    @Size(min = 9, max = 12, message = "ID Card length must be between 9 and 12 characters")
    private String idCard;

    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    @NotEmpty(message = "Image url cannot be empty")
    private String imageUrl;


    private String active;
    private boolean userDeleted;

    private Integer oldDepartmentId;

    private Integer departmentId;
    private Integer userId;
}
