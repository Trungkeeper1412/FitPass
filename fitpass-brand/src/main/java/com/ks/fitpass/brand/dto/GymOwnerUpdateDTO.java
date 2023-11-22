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

    @NotEmpty(message = "Vui lòng nhập số điện thoại !")
    @Pattern(regexp = "^(0|84)(9|3|7|8|5)\\d{8,9}$", message = "Số điện thoại không đúng định dạng !")
    private String phone;

    @NotEmpty(message = "Vui lòng nhập địa chỉ !")
    @Size(max = 25, message = "Địa chỉ không được vượt quá 150 kí tự !")
    private String address;

    @NotNull(message = "Vui lòng nhập ngày sinh !")
    @Past(message = "Vui lòng nhập ngày sinh hợp lệ !")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Vui lòng nhập số căn cước công dân !")
    @Pattern(regexp = "^[0-9]{12}$", message = "Số căn cước công dân không hợp lệ !")
    @Size(min = 12, max = 12, message = "Số căn cước công dân phải là 12 chữ số !")
    @GymOwnerCreateDTO.ValidateIdCard(message = "Số căn cước công dân không hợp lệ !")
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
