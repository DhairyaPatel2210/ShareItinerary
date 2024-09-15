package com.shareitinerary.servicesimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shareitinerary.dto.*;
import com.shareitinerary.entities.*;
import com.shareitinerary.exceptions.DatabaseError;
import com.shareitinerary.repositories.ItineraryRepo;
import com.shareitinerary.utilities.ConverterFactory;

@ExtendWith(MockitoExtension.class)
public class ItineraryServImplTest {

    @Mock
    private ItineraryRepo itineraryRepo;

    @Mock
    private ConverterFactory converter;

    @InjectMocks
    private ItineraryServImpl itineraryServImpl;

    private ItineraryDTO itineraryDTO;
    private Itinerary itinerary;

    @BeforeEach
    void setUp() {
        // Set up test data
        LocationDTO locationDTO = new LocationDTO("Test Location", 40.7128, -74.0060);
        ImageDTO imageDTO = new ImageDTO("https://example.s3.amazonaws.com/image.jpg");
        ActivitiyDTO activityDTO = new ActivitiyDTO("Test Activity", List.of(imageDTO), "Test Description", locationDTO);
        DaysDTO daysDTO = new DaysDTO(1, Date.valueOf("2024-09-15"), List.of(activityDTO));
        itineraryDTO = new ItineraryDTO("Test Itinerary", "Test Summary", 1, List.of(daysDTO));

        Location location = new Location(UUID.randomUUID(), -74.0060, 40.7128, "Test Location", null);
        Image image = new Image(UUID.randomUUID(), "https://example.s3.amazonaws.com/image.jpg", null);
        Activity activity = new Activity(UUID.randomUUID(), "Test Activity", "Test Description", location, List.of(image), null);
        Day day = new Day(UUID.randomUUID(), 1, Date.valueOf("2024-09-15"), List.of(activity), null);
        itinerary = new Itinerary(UUID.randomUUID(), "Test Itinerary", "Test Summary", List.of(day));
    }

    @Test
    @DisplayName("Test createItinerary with valid input")
    void testCreateItinerary() {
        when(converter.convertToItinerary(itineraryDTO)).thenReturn(itinerary);
        when(itineraryRepo.save(itinerary)).thenReturn(itinerary);
        when(converter.convertToItineraryDTO(itinerary)).thenReturn(itineraryDTO);

        Response<ItineraryDTO> response = itineraryServImpl.createItinerary(itineraryDTO);

        assertNotNull(response);
        assertEquals("Added Successfully!", response.getMessage());
        assertEquals(ItineraryDTO.class, response.getObject().getClass());
        assertEquals("Test Itinerary", response.getObject().getName());
        assertEquals("Test Summary", response.getObject().getSummary());
        assertEquals(1, response.getObject().getDay_count());
    }

    @Test
    @DisplayName("Test createItinerary with database error")
    void testCreateItineraryDatabaseError() {
        when(converter.convertToItinerary(itineraryDTO)).thenReturn(itinerary);
        when(itineraryRepo.save(itinerary)).thenThrow(new RuntimeException("Database Error"));

        assertThrows(DatabaseError.class, () -> itineraryServImpl.createItinerary(itineraryDTO));
    }

    @Test
    @DisplayName("Test createItinerary with null input")
    void testCreateItineraryNullInput() {
        assertThrows(DatabaseError.class, () -> itineraryServImpl.createItinerary(null));
    }

    @Test
    @DisplayName("Test createItinerary with empty days list")
    void testCreateItineraryEmptyDays() {
        itineraryDTO.setDays(new ArrayList<>());
        when(converter.convertToItinerary(itineraryDTO)).thenReturn(itinerary);
        when(itineraryRepo.save(itinerary)).thenReturn(itinerary);
        when(converter.convertToItineraryDTO(itinerary)).thenReturn(itineraryDTO);

        Response<ItineraryDTO> response = itineraryServImpl.createItinerary(itineraryDTO);

        assertNotNull(response);
        assertEquals(0, response.getObject().getDay_count());
    }

    @Test
    @DisplayName("Test createItinerary with invalid location coordinates")
    void testCreateItineraryInvalidLocation() {
        LocationDTO invalidLocation = new LocationDTO("Invalid", 91.0, 181.0);
        ActivitiyDTO activityDTO = itineraryDTO.getDays().get(0).getActivities().get(0);
        activityDTO.setLocation(invalidLocation);

        when(converter.convertToItinerary(itineraryDTO)).thenThrow(new IllegalArgumentException("Invalid coordinates"));

        assertThrows(DatabaseError.class, () -> itineraryServImpl.createItinerary(itineraryDTO));
    }
}