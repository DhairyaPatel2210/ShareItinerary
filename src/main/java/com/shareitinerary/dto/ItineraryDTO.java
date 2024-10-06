package com.shareitinerary.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItineraryDto {

    @NotBlank (message = "Itinerary Name can not be Null")
    String name;

    @NotBlank (message = "Summary can not be Null")
    String summary;

    @Positive (message = "Travel Days Must be greater than 0")
    int day_count;

    @NotNull(message = "Itinerary should have atlease one day")
    @Valid
    List<DayDto> days;
}

