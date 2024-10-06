package com.shareitinerary.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareitinerary.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
}
