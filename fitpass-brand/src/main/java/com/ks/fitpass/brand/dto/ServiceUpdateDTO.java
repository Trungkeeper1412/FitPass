package com.ks.fitpass.brand.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceUpdateDTO {
    @NotNull(message = "AmenitieId cannot be empty")
    private Integer amenitieId;

    @NotEmpty(message = "Photo URL cannot be empty")
    private String photoUrl;

    @NotEmpty(message = "Amenitie Name cannot be empty")
    private String amenitieName;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

}
