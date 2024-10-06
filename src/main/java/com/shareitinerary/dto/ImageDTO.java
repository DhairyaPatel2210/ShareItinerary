package com.shareitinerary.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {

    @NotEmpty(message = "Image link can't be empty")
    @Pattern(regexp = "https?:\\/\\/[^\\/]+\\.s3(?:-fips)?(?:\\.dualstack)?\\.[^\\/]+\\.amazonaws\\.com\\/[^\\/]+$", message = "Invalid S3 bucket URL")
    String link;

}
