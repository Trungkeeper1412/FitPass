package com.ks.fitpass.department.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FeatureUpdateDTO {
    @NotNull(message = "Tên tiện ích không được để trống !")
    private Integer featureID;

    @NotEmpty(message = "Vui lòng chọn ảnh !")
    private String featureIcon;

    @NotEmpty(message = "Vui lòng nhập tên tiện ích !")
    @Size(min = 2, max = 50, message = "Tên tiện ích phải nằm trong khoảng 2 đến 50 kí tự")
    private String featureName;
}
