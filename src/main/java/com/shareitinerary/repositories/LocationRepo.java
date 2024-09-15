package com.shareitinerary.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareitinerary.entities.Location;

public interface LocationRepo extends JpaRepository<Location, UUID>{
}
