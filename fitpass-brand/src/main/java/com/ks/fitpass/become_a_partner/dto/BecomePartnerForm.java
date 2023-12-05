package com.ks.fitpass.become_a_partner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BecomePartnerForm {
    @NotBlank(message = "Bạn cần nhập tên thương hiêu")
    private String brandName;

    @NotBlank(message = "Bạn cần nhập tên chủ thương hiệu")
    private String brandOwnerName;

    @NotBlank(message = "Bạn cần nhập số điện thoại")
    @Pattern(regexp = "\\d{10,15}", message = "Định dạng số điện thoại không chính xác")
    private String contactNumber;

    @NotBlank(message = "Bạn cần nhập dịa chỉ")
    private String address;

    private String webUrl;

    @NotBlank(message = "Bạn cần nhập email liên hệ")
    @Email(message = "Định dạng email không chính xác")
    private String contactEmail;
}
