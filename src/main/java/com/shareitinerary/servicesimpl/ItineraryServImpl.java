package com.shareitinerary.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shareitinerary.dto.ItineraryDto;
import com.shareitinerary.dto.Response;
import com.shareitinerary.entities.Itinerary;
import com.shareitinerary.exceptions.DatabaseError;
import com.shareitinerary.repositories.ItineraryRepo;
import com.shareitinerary.services.ItineraryService;
import com.shareitinerary.utilities.Factory;

@Service
public class ItineraryServImpl implements ItineraryService {

    @Autowired
    private ItineraryRepo itineraryRepo;

    @Autowired
    private Factory converter;

    @Override
    @Transactional
    public Response<ItineraryDto> createItinerary(ItineraryDto itinerary) {

        try {
            Itinerary entity = converter.convertToItinerary(itinerary);
            entity = itineraryRepo.save(entity);
            ItineraryDto savedEntityDTO = converter.convertToItineraryDTO(entity);
            savedEntityDTO.setDay_count(savedEntityDTO.getDays().size());
            Response<ItineraryDto> res = new Response<ItineraryDto>("Added Successfully!", savedEntityDTO);
            return res;
        } catch (Exception e) {
            throw new DatabaseError(e.getMessage());
        }
    }

}