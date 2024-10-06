package com.shareitinerary.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shareitinerary.entities.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, UUID> {
}
