package com.ks.fitpass.gymplan.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class BrandCreateGymPlanFlexDTO {
    private int brandId;

    @NotEmpty(message = "Vui lòng nhập tên gói tập !")
    @Size(min = 2, max = 32, message = "Tên gói tập phải nằm trong khoảng 2 đến 32 kí tự !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Tên gói tập không được chứa kí tự đặc biệt !")
    private String gymPlanName;

    @NotNull(message = "Vui lòng nhập số credits !")
    @Positive(message = "Số credits/giờ phải lớn hơn 0 !")
    private Double pricePerHours;

    @NotNull(message = "ui lòng nhập nhập số ngày sau khi kích hoạt !")
    @Min(value = 0, message = "Số ngày phải lớn hơn 0 !")
    private Integer planBeforeActive;

    @NotNull(message = "Vui lòng nhập nhập số ngày sau khi kích hoạt !")
    @Min(value = 0, message = "Số ngày phải lớn hơn 0 !")
    private Integer planAfterActive;

    @NotNull(message = "Vui lòng nhập nhập mô tả gói tập !")
    @Size(min = 2, max = 250, message = "Mô tả gói tập phải nằm trong khoảng 2 đến 250 kí tự !")
    private String description;

    private int status;
}
