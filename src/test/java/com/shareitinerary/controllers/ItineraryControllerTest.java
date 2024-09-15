package com.shareitinerary.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
@Tag("integration")
public class ItineraryControllerTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

            
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Testing /itineraryservice/v1/itineraries with correct input")
    public void testCreateItinerary() throws Exception {
        String itineraryDTO = "{\n" +
                            "    \"name\": \"Dubai\",\n" +
                            "    \"summary\" : \"Visited Dubai\",\n" +
                            "    \"day_count\" : 1,\n" +
                            "    \"days\" : [\n" +
                            "        {\n" +
                            "            \"day_no\" : 1,\n" +
                            "            \"date\": \"2024-09-14\",\n" +
                            "            \"activities\" : [\n" +
                            "                {\n" +
                            "                    \"title\" : \"Dubai Mall Visit\",\n" +
                            "                    \"images\" : [\n" +
                            "                        {\n" +
                            "                            \"link\" : \"https://bda-project.s3.us-west-1.amazonaws.com/image_temp.jpg\"\n" +
                            "                        }\n" +
                            "                    ],\n" +
                            "                    \"description\" : \"There were 40 international shops inside Dubai Mall\",\n" +
                            "                    \"location\" : {\n" +
                            "                        \"name\" : \"Dubai Mall\",\n" +
                            "                        \"latitude\" : 25.199514,\n" +
                            "                        \"longitude\" : 55.277397\n" +
                            "                    }\n" +
                            "                }\n" +
                            "            ]\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/itineraryservice/v1/itineraries")
                .content(itineraryDTO)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
