package com.shareitinerary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shareitinerary.dto.ItineraryDTO;
import com.shareitinerary.dto.Response;
import com.shareitinerary.services.ItineraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itineraryservice")
public class ItineraryController {

    @Autowired
    private ItineraryService itinerarService;

    @PostMapping("/v1/itineraries")
    public ResponseEntity<Response> createItinerary(@Valid @RequestBody ItineraryDTO itinerary) {
        Response res = new Response();
        res = itinerarService.createItinerary(itinerary);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }
}