package com.shareitinerary.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ImageDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Testing ImageDto with valid AWS s3 bucket URLs")
    public void testValidImageLinks() {
        // Array of valid links including both HTTP and HTTPS
        String[] validLinks = {
                "http://example-bucket.s3.us-east-1.amazonaws.com/my-image.jpg",
                "https://example-bucket.s3.us-east-1.amazonaws.com/my-image.jpg",
                "http://example-bucket.s3.dualstack.us-east-1.amazonaws.com/my-image.jpg",
                "https://example-bucket.s3.dualstack.us-east-1.amazonaws.com/my-image.jpg",
                "http://example-bucket.s3-fips.us-east-1.amazonaws.com/my-image.jpg",
                "https://example-bucket.s3-fips.us-east-1.amazonaws.com/my-image.jpg",
                "http://example-bucket.s3-fips.dualstack.us-east-1.amazonaws.com/my-image.jpg",
                "https://example-bucket.s3-fips.dualstack.us-east-1.amazonaws.com/my-image.jpg"
        };

        for (String link : validLinks) {
            ImageDto imageDto = ImageDto.builder()
                    .link(link)
                    .build();

            Set<ConstraintViolation<ImageDto>> violations = validator.validate(imageDto);
            assertTrue(violations.isEmpty(), "Should be valid: " + link);
        }
    }

    @Test
    @DisplayName("Testing ImageDto with invalid AWS s3 bucket URLs")
    public void testInvalidImageLinks() {
        // Array of invalid links that should not pass validation
        String[] invalidLinks = {
                "ftp://example-bucket.s3.us-east-1.amazonaws.com/my-image.jpg", // Invalid protocol
                "http://example-bucket.s3.amazonaws.com/my-image.jpg", // Missing region
                "https://example-bucket.s3.amazonaws.com/my-image.jpg", // Missing region
                "http://example-bucket.s3-fips.amazonaws.com/my-image.jpg", // Missing region
                "https://example-bucket.s3-fips.amazonaws.com/my-image.jpg", // Missing region
                "http://example-bucket.s3-fips.dualstack.amazonaws.com/my-image.jpg", // Missing region
                "https://example-bucket.s3-fips.dualstack.amazonaws.com/my-image.jpg", // Missing region
                "http://example-bucket.s3-fips.us-east-1.amazonaws.com/", // Empty key name
                "https://example-bucket.s3.us-east-1.amazonaws.com/" // Empty key name
        };

        for (String link : invalidLinks) {
            ImageDto imageDto = ImageDto.builder()
                    .link(link)
                    .build();

            Set<ConstraintViolation<ImageDto>> violations = validator.validate(imageDto);
            assertFalse(violations.isEmpty(), "Should be invalid: " + link);
        }
    }
}
