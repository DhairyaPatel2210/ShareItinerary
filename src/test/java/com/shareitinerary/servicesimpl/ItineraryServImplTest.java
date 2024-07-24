package com.shareitinerary.servicesimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shareitinerary.dto.ItineraryDTO;
import com.shareitinerary.dto.Response;
import com.shareitinerary.entities.Itinerary;
import com.shareitinerary.exceptions.DatabaseError;
import com.shareitinerary.repositories.ItineraryRepo;
import com.shareitinerary.utilities.ConverterFactory;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class ItineraryServImplTest {
    
    @Mock
    private ItineraryRepo itineraryRepo;

    @Mock
    private ConverterFactory converter;

    @InjectMocks
    private ItineraryServImpl itineraryServImpl;

    @Test
    @DisplayName("Unit test for Itinerary Service Impl")
    public void testCreateItinerary() {
        Itinerary itinerary = new Itinerary(10L, "Trip1", "Car", 10);
        ItineraryDTO itineraryDTO = new ItineraryDTO("Trip1", "Car", 10);

        when(converter.convertToItinerary(itineraryDTO)).thenReturn(itinerary);
        when(itineraryRepo.save(itinerary)).thenReturn(itinerary);
        when(converter.convertToItineraryDTO(itinerary)).thenReturn(itineraryDTO);
        Response<ItineraryDTO> res = itineraryServImpl.createItinerary(itineraryDTO);
        assertEquals(res.getObject().getClass(), ItineraryDTO.class);
        ItineraryDTO savedDTO = res.getObject();
        assertEquals(savedDTO.getName(), "Trip1");
        assertEquals(savedDTO.getTransportationMode(), "Car");
        assertEquals(savedDTO.getTravelDays(), 10);
    }

    @Test
    @DisplayName("testing createItinerary method in Itinerary Service Impl class while generating Database error")
    public void testCreateItineraryError() {
        ItineraryDTO itineraryDTO = new ItineraryDTO("Trip1", "Car", 10);
        when(itineraryServImpl.createItinerary(itineraryDTO)).thenThrow(new RuntimeException("Database Error"));
        
        try {
            itineraryServImpl.createItinerary(itineraryDTO);
        } catch (DatabaseError e) {
            assertEquals(e.getMessage(), "Database Error");
        }
    }
}
