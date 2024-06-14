package com.shareitinerary.services;

import org.springframework.stereotype.Component;

import com.shareitinerary.dto.ItineraryDTO;
import com.shareitinerary.dto.Response;

@Component
public interface ItineraryService {
    public Response createItinerary(ItineraryDTO itinerary);
}

