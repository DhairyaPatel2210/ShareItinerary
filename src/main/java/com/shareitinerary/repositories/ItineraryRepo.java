package com.shareitinerary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shareitinerary.entities.Itinerary;

@Repository
public interface ItineraryRepo extends JpaRepository<Itinerary, Long> {
}
