package com.shareitinerary.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItineraryDTO {

    @NotEmpty (message = "Itinerary Name can not be Null")
    String name;

    @NotEmpty (message = "Transportation Mode can not be Null")
    String transportationMode;

    @Positive(message = "Travel Days Must be greater than 0")
    int travelDays;
}

