package com.shareitinerary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itinerary_generator")
    @SequenceGenerator(name = "itinerary_generator", sequenceName = "itinerary_seq", allocationSize = 1)
    Long id;
    
    @NotEmpty
    String name;

    @NotEmpty
    String transportationMode;
    
    @Min(1)
    int travelDays;
}
