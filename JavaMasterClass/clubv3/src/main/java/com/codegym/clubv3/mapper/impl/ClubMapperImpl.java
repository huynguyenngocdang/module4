package com.codegym.clubv3.mapper.impl;

import com.codegym.clubv3.dto.ClubDto;
import com.codegym.clubv3.entity.Club;

import java.util.stream.Collectors;

import static com.codegym.clubv3.mapper.impl.EventMapperImpl.mapToEvent;
import static com.codegym.clubv3.mapper.impl.EventMapperImpl.mapToEventDto;


public class ClubMapperImpl {

    public static Club mapToClub(ClubDto clubDto) {
        return Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .photoUrl(clubDto.getPhotoUrl())
                .createdBy(clubDto.getCreatedBy())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .events(clubDto.getEventsDto().stream().map((eventDto) ->mapToEvent(eventDto)).collect(Collectors.toList()))
                .build();
    }

    public static ClubDto mapToClubDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photoUrl(club.getPhotoUrl())
                .createdBy(club.getCreatedBy())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .eventsDto(club.getEvents().stream().map((event) ->mapToEventDto(event)).collect(Collectors.toList()))
                .build();
    }
}
