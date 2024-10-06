package com.shareitinerary.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDto {
    @NotEmpty (message = "Activity title can't be empty")
    String title;

    @Valid
    List<ImageDto> images;

    @NotEmpty (message = "Activity Description can't be null")
    String description;

    @NotNull(message = "Location can't be empty")
    @Valid
    LocationDto location;
}
