package com.ks.fitpass.gymplan.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BrandUpdateGymPlanFixedDTO {
    @NotNull
    private Integer gymPlanId;

    @NotEmpty(message = "Vui lòng nhập tên gói tập !")
    @Size(min = 2, max = 32, message = "Tên gói tập phải nằm trong khoảng 2 đến 32 kí tự !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Tên gói tập không được chứa kí tự đặc biệt !")
    private String gymPlanName;

    @NotNull(message = "Vui lòng nhập số credits !")
    @Positive(message = "Số credits phải lớn hơn 0 !")
    private Double price;

    @NotNull(message = "ui lòng nhập nhập số ngày sau khi kích hoạt !")
    @Min(value = 0, message = "Số ngày phải lớn hơn 0 !")
    private Integer planBeforeActive;

    @NotNull(message = "Plan After Active cannot be null")
    @Min(value = 0, message = "Plan After Active must be 0 or a positive number")
    private Integer planAfterActive;

    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Duration must be > 0 ")
    private Integer duration;

    @NotNull
    private Integer status;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}
