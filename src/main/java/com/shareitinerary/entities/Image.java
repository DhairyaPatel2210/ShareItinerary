package com.shareitinerary.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "Image link can't be empty")
    @Pattern(regexp = "https?:\\/\\/[^\\/]+\\.s3(?:-fips)?(?:\\.dualstack)?\\.[^\\/]+\\.amazonaws\\.com\\/[^\\/]+$", message = "Invalid S3 bucket URL")
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("activity-image")
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Image(String link) {
        this.link = link;
    }
}
