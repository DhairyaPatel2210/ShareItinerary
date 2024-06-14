package com.shareitinerary.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shareitinerary.dto.ItineraryDTO;
import com.shareitinerary.dto.Response;
import com.shareitinerary.entities.Itinerary;
import com.shareitinerary.exceptions.DatabaseError;
import com.shareitinerary.repositories.ItineraryRepo;
import com.shareitinerary.services.ItineraryService;
import com.shareitinerary.utilities.ConverterFactory;

@Service
public class ItineraryServImpl implements ItineraryService {
    
    @Autowired
    private ItineraryRepo itineraryRepo;

    @Autowired
    private ConverterFactory converter;
    
    @Override
    public Response createItinerary(ItineraryDTO itinerary) {
        Response res = new Response();
        try {
            Itinerary entity = converter.convertToItinerary(itinerary);
            itineraryRepo.save(entity);
            res.setMessage("Added Successfully!");
            return res;
        } catch (Exception e) {
            throw new DatabaseError(e.getMessage());
        }
    }
    
}