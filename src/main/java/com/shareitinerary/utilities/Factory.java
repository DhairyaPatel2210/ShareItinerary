package com.shareitinerary.utilities;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shareitinerary.dto.ActivityDto;
import com.shareitinerary.dto.DayDto;
import com.shareitinerary.dto.ImageDto;
import com.shareitinerary.dto.ItineraryDto;
import com.shareitinerary.entities.Activity;
import com.shareitinerary.entities.Day;
import com.shareitinerary.entities.Image;
import com.shareitinerary.entities.Itinerary;
import com.shareitinerary.entities.Location;

@Component
public class Factory {

    @Autowired
    private ModelMapper modelMapper;

    public Itinerary convertToItinerary(ItineraryDto dto) {
        Itinerary itinerary_entity = Itinerary.builder()
                .name(dto.getName())
                .summary(dto.getSummary())
                .build();

        List<Day> days = new ArrayList<>();

        for (DayDto d : dto.getDays()) {
            Day day_entity = Day.builder()
                    .day_no(d.getDay_no())
                    .itinerary(itinerary_entity)
                    .date(d.getDate())
                    .build();

            List<Activity> activities = new ArrayList<>();

            for (ActivityDto a : d.getActivities()) {
                Activity activity_entity = Activity.builder()
                        .description(a.getDescription())
                        .title(a.getTitle())
                        .day(day_entity)
                        .build();

                Location location_entity = Location.builder()
                        .latitude(a.getLocation().getLatitude())
                        .longitude(a.getLocation().getLongitude())
                        .name(a.getLocation().getName())
                        .activity(activity_entity)
                        .build();

                List<Image> images = new ArrayList<>();

                for (ImageDto i : a.getImages()) {
                    Image image_entity = Image.builder()
                            .link(i.getLink())
                            .activity(activity_entity)
                            .build();
                    images.add(image_entity);
                }

                activity_entity.setImages(images);
                activity_entity.setLocation(location_entity);
                activities.add(activity_entity);
            }

            day_entity.setActivities(activities);
            days.add(day_entity);
        }

        itinerary_entity.setDays(days);

        return itinerary_entity;

    }

    public ItineraryDto convertToItineraryDTO(Itinerary itinerary) {
        return modelMapper.map(itinerary, ItineraryDto.class);
    }
}
