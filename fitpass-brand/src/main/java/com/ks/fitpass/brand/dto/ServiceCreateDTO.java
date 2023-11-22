package com.ks.fitpass.brand.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServiceCreateDTO {
    @NotEmpty(message = "Vui lòng chọn ảnh !")
    private String photoUrl;

    @NotEmpty(message = "Vui lòng nhập tên dịch vụ !")
    @Size(min = 2, max = 50, message = "Tên dịch vụ phải nằm trong khoảng 2 đến 50 kí tự")
    private String amenitieName;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 2, max = 250, message = "Mô tả gói tập phải nằm trong khoảng 2 đến 250 kí tự !")
    private String description;
}
