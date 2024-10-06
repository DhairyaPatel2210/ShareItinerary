package com.shareitinerary.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @NotEmpty(message = "Itinerary name can't be empty")
    private String name;

    @NotEmpty(message = "Itinerary summary can't be empty")
    private String summary;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Day> days;

    public Itinerary(String name, String summary) {
        this.name = name;
        this.summary = summary;
    }
}
