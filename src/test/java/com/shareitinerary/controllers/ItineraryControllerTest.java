package com.shareitinerary.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest()
@AutoConfigureMockMvc
@Testcontainers
public class ItineraryControllerTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

            
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateItinerary() throws Exception {
        String itineraryDTO = "{\"name\":\"Test 1 Hi\",\"transportationMode\":\"Hello\",\"travelDays\":7}";

        mockMvc.perform(MockMvcRequestBuilders.post("/itineraryservice/v1/itineraries")
                .content(itineraryDTO)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
