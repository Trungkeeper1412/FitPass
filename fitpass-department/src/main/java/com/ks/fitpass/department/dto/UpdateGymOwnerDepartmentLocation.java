package com.ks.fitpass.department.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateGymOwnerDepartmentLocation {
    @NotEmpty(message = "Vui lòng nhập kinh độ !")
    @Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$", message = "Kinh độ của bạn đang sai!")
    private String latitude;

    @NotEmpty(message = "Vui lòng nhập vĩ độ !")
    @Pattern(regexp = "^[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$", message = "Vĩ độ của bạn đang sai !")
    private String longitude;
}
