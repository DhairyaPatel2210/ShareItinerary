package com.shareitinerary.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Latitude cannot be null")
    @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be greater than or equal to -90")
    @DecimalMax(value = "90.0", inclusive = true, message = "Latitude must be less than or equal to 90")
    private double longitude;

    @NotNull(message = "Longitude cannot be null")
    @DecimalMin(value = "-180.0", inclusive = true, message = "Longitude must be greater than or equal to -180")
    @DecimalMax(value = "180.0", inclusive = true, message = "Longitude must be less than or equal to 180")
    private double latitude;

    @NotBlank
    private String name;

    @OneToOne()
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @JsonBackReference("activity-location")
    private Activity activity;

    public Location(double longitude, double latitude, String name) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }
}
