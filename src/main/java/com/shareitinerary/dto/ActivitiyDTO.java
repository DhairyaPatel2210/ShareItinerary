package com.shareitinerary.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivitiyDTO {
    @NotEmpty (message = "Activity title can't be empty")
    String title;

    @Valid
    List<ImageDTO> images;

    @NotEmpty (message = "Activity Description can't be null")
    String description;

    @NotNull(message = "Location can't be empty")
    @Valid
    LocationDTO location;
}
