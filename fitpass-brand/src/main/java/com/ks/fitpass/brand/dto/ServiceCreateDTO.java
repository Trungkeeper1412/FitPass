package com.ks.fitpass.brand.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ServiceCreateDTO {
    @NotEmpty(message = "Photo URL cannot be empty")
    private String photoUrl;

    @NotEmpty(message = "Amenitie Name cannot be empty")
    private String amenitieName;

    @NotEmpty(message = "Description cannot be empty")
    private String description;
}
