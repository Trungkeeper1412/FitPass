package com.ks.fitpass.core.entity;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserRegisterDTO {

    @NotEmpty(message = "Vui lòng nhập tên đăng nhập!")
    private String userAccount;

    @NotEmpty(message = "Vui lòng nhập mật khẩu !")
    private String userPassword;

    @NotEmpty(message = "Vui lòng xác nhận mật khẩu !")
    private String reUserPassword;

    @Size(max = 25, message = "Họ của bạn không được vượt quá 25 kí tự !")
    @NotEmpty(message = "Vui lòng nhập họ của bạn !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Họ của bạn không được chứa kí tự đặc biệt !")
    private String firstName;

    @Size(max = 25, message = "Tên của bạn không được vượt quá 25 kí tự !")
    @NotEmpty(message = "Vui lòng nhập tên của bạn !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Tên của bạn không được chứa kí tự đặc biệt !")
    private String lastName;

    @NotEmpty(message = "Vui lòng nhập email !")
    @Email (message = "Vui lòng nhập địa chỉ email hợp lệ !")
    private String email;

    @NotEmpty(message = "Vui lòng nhập số điện thoại !")
    @Pattern(regexp = "^(0|84)(9|3|7|8|5)\\d{8,9}$", message = "Số điện thoại không đúng định dạng !")
    private String phoneNumber;

    @NotNull(message = "Vui lòng nhập ngày sinh !")
    @Past(message = "Vui lòng nhập ngày sinh hợp lệ !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Vui lòng chọn giới tính !")
    private String gender;
}
