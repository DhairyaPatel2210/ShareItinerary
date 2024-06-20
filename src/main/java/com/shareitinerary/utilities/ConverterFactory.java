package com.shareitinerary.utilities;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shareitinerary.dto.ItineraryDTO;
import com.shareitinerary.entities.Itinerary;

@Component
public class ConverterFactory{

    @Autowired
    private ModelMapper modelMapper;

    public Itinerary convertToItinerary(ItineraryDTO dto) {
        return modelMapper.map(dto, Itinerary.class);
    }

    public ItineraryDTO convertToItineraryDTO(Itinerary itinerary) {
        return modelMapper.map(itinerary, ItineraryDTO.class);   
    }
}
