package com.ks.fitpass.gymplan.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class BrandCreateGymPlanFlexDTO {
    private int brandId;

    @NotEmpty(message = "Gym Plan Name cannot be blank")
    @Size(max = 100, message = "Gym Plan Name must not exceed 100 characters")
    private String gymPlanName;

    @NotNull(message = "Price Per Hour cannot be null")
    @Positive(message = "Price Per Hour must be a positive number")
    private Double pricePerHours;

    @NotNull(message = "Plan Before Active cannot be null")
    @Min(value = 0, message = "Plan Before Active must be 0 or positive")
    private Integer planBeforeActive;

    @NotNull(message = "Plan After Active cannot be null")
    @Min(value = 0, message = "Plan After Active must be 0 or positive")
    private Integer planAfterActive;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private int status;
}
