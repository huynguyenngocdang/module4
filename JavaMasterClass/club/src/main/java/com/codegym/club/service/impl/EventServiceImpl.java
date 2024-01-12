package com.codegym.club.service.impl;

import com.codegym.club.dto.EventDto;
import com.codegym.club.model.Club;
import com.codegym.club.model.Event;
import com.codegym.club.repository.ClubRepository;
import com.codegym.club.repository.EventRepository;
import com.codegym.club.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }



    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    private Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();
        return event;
    }
}
