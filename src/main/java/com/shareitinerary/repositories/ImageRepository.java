package com.shareitinerary.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareitinerary.entities.Image;

public interface ImageRepository extends JpaRepository<Image, UUID> {

}
