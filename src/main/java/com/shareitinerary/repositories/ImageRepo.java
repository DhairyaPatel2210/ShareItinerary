package com.shareitinerary.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareitinerary.entities.Image;

public interface ImageRepo extends JpaRepository<Image, UUID>{
    
}
