package com.shareitinerary.services;

import org.springframework.stereotype.Component;

import com.shareitinerary.dto.ItineraryDto;
import com.shareitinerary.dto.Response;

@Component
public interface ItineraryService {
    public Response<ItineraryDto> createItinerary(ItineraryDto itinerary);
}

