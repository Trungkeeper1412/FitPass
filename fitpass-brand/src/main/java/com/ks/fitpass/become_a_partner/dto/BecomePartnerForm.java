package com.ks.fitpass.become_a_partner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BecomePartnerForm {
    @NotBlank(message = "Brand Name is required")
    private String brandName;

    @NotBlank(message = "Brand Owner Name is required")
    private String brandOwnerName;

    @NotBlank(message = "Contact Number is required")
    @Pattern(regexp = "\\d{10,15}", message = "Invalid Contact Number")
    private String contactNumber;

    @NotBlank(message = "Address is required")
    private String address;

    private String webUrl;

    @NotBlank(message = "Contact Email is required")
    @Email(message = "Invalid Email Format")
    private String contactEmail;
}
