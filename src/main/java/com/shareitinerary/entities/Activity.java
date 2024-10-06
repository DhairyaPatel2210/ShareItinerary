package com.shareitinerary.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "Activity title can't be empty")
    private String title;

    @NotEmpty(message = "Activity description can't be empty")
    private String description;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "activity")
    @JsonManagedReference("activity-location")
    private Location location;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    @JsonManagedReference("activity-image")
    private List<Image> images;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "day_id")
    @JsonBackReference
    private Day day;

    public Activity(String title, String description) {
        this.title = title;
        this.description = description;
        
    }
}
