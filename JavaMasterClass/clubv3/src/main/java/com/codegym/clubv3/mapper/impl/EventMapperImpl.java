package com.codegym.clubv3.mapper.impl;

import com.codegym.clubv3.dto.EventDto;
import com.codegym.clubv3.entity.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codegym.clubv3.mapper.impl.ClubMapperImpl.mapToClub;
import static com.codegym.clubv3.mapper.impl.ClubMapperImpl.mapToClubDto;


public class EventMapperImpl  {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .title(eventDto.getTitle())
                .content(eventDto.getContent())
                .photoUrl(eventDto.getPhotoUrl())
                .startTime(LocalDateTime.parse(eventDto.getStartTime()))
                .endTime(LocalDateTime.parse(eventDto.getEndTime()))
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .club(eventDto.getClub())
                .build();
    }

    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .content(event.getContent())
                .photoUrl(event.getPhotoUrl())
                .startTime(event.getStartTime().format(formatter))
                .endTime(event.getEndTime().format(formatter))
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .club(event.getClub())
                .build();
    }
}
