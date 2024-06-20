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
    public Response<ItineraryDTO> createItinerary(ItineraryDTO itinerary) {
        
        try {
            Itinerary entity = converter.convertToItinerary(itinerary);
            entity = itineraryRepo.save(entity);
            ItineraryDTO savedEntityDTO = converter.convertToItineraryDTO(entity);
            Response<ItineraryDTO> res = new Response<ItineraryDTO>("Added Successfully!",savedEntityDTO);
            return res;
        } catch (Exception e) {
            throw new DatabaseError(e.getMessage());
        }
    }
    
}