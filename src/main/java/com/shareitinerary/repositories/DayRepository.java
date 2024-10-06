package com.shareitinerary.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shareitinerary.entities.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, UUID> {

}
