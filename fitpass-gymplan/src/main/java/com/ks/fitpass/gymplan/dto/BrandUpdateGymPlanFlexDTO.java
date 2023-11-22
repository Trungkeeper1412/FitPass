package com.ks.fitpass.gymplan.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BrandUpdateGymPlanFlexDTO {
    @NotNull
    private int gymPlanId;

    @NotEmpty(message = "Vui lòng nhập tên gói tập !")
    @Size(min = 2, max = 32, message = "Tên gói tập phải nằm trong khoảng 2 đến 32 kí tự !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Tên gói tập không được chứa kí tự đặc biệt !")
    private String gymPlanName;

    @NotNull(message = "Vui lòng nhập số credits !")
    @Positive(message = "Số credits/giờ phải lớn hơn 0 !")
    private Double pricePerHours;

    @NotNull(message = "Plan Before Active cannot be null")
    @Min(value = 0, message = "Plan Before Active must be 0 or a positive number")
    private int planBeforeActive;

    @NotNull(message = "Plan After Active cannot be null")
    @Min(value = 0, message = "Plan After Active must be 0 or a positive number")
    private int planAfterActive;

    @NotNull
    private int status;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}
