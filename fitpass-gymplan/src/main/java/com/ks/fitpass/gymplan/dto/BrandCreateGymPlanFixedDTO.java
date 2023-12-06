package com.ks.fitpass.gymplan.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BrandCreateGymPlanFixedDTO {
    private int brandId;

    @NotEmpty(message = "Vui lòng nhập tên gói tập !")
    @Size(min = 2, max = 32, message = "Tên gói tập phải nằm trong khoảng 2 đến 32 kí tự !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF90-9\\s]*$", message = "Tên gói tập không được chứa kí tự đặc biệt !")
    private String gymPlanName;

    @NotNull(message = "Vui lòng nhập số credits !")
    @Positive(message = "Số credits phải lớn hơn 0 !")
    @Max(value = 10000, message = "Số credits không vượt quá 10000 !")
    private Double price;

    @NotNull(message = "Vui lòng nhập nhập số ngày sau khi kích hoạt !")
    @Min(value = 1, message = "Số ngày phải lớn hơn 0 !")
    @Max(value = 36500, message = "Số ngày không vượt quá 36500 ngày")
    private Integer planBeforeActive;

    @NotNull(message = "Vui lòng nhập nhập số ngày trước khi kích hoạt !")
    @Min(value = 1, message = "Số ngày phải lớn hơn 0 !")
    @Max(value = 36500, message = "Số ngày không vượt quá 36500 ngày")
    private Integer planAfterActive;

    @NotNull(message = "Vui lòng nhập nhập số ngày sử dụng gói tập !")
    @Min(value = 1, message = "Số ngày ít nhất phải bằng 1 !")
    @Max(value = 36500, message = "Số ngày không vượt quá 36500 ngày")
    private Integer duration;

    @NotNull(message = "Vui lòng nhập nhập mô tả gói tập !")
    @Size(min = 2, max = 250, message = "Mô tả gói tập phải nằm trong khoảng 2 đến 250 kí tự !")
    private String description;

    private int status;
}
