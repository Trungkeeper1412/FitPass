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

    @NotEmpty(message = "Gym Plan Name cannot be blank")
    @Size(max = 100, message = "Gym Plan Name must not exceed 100 characters")
    private String gymPlanName;

    @NotNull(message = "Price Per Hour cannot be null")
    @Positive(message = "Price Per Hour must be a positive number")
    private double pricePerHours;

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
