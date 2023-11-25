package com.ks.fitpass.department.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateGymOwnerDepartmentLocation {

    @NotEmpty(message = "Vui lòng nhập kinh độ !")
    @Pattern(regexp = "^-?([0-8]?[0-9]|90)(\\.\\d{1,8})?$", message = "Kinh độ của bạn đang sai!")
    private String latitude;

    @NotEmpty(message = "Vui lòng nhập vĩ độ !")
    @Pattern(regexp = "^-?((1[0-7][0-9]|[0-9]{1,2})|180)(\\.\\d{1,8})?$", message = "Vĩ độ của bạn đang sai !")
    private String longitude;
}
