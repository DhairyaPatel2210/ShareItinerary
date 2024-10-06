package com.shareitinerary.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {

    @NotEmpty
    String name;

    @NotNull(message = "Latitude cannot be null")
    @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be greater than or equal to -90")
    @DecimalMax(value = "90.0", inclusive = true, message = "Latitude must be less than or equal to 90")
    double latitude;

    @NotNull(message = "Longitude cannot be null")
    @DecimalMin(value = "-180.0", inclusive = true, message = "Longitude must be greater than or equal to -180")
    @DecimalMax(value = "180.0", inclusive = true, message = "Longitude must be less than or equal to 180")
    double longitude;
    
}
