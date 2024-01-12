package com.codegym.clubv3.service.impl;

import com.codegym.clubv3.dto.EventDto;
import com.codegym.clubv3.entity.Club;
import com.codegym.clubv3.entity.Event;
import com.codegym.clubv3.repository.ClubRepository;
import com.codegym.clubv3.repository.EventRepository;
import com.codegym.clubv3.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.codegym.clubv3.mapper.impl.EventMapperImpl.mapToEvent;
import static com.codegym.clubv3.mapper.impl.EventMapperImpl.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;


    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public Page<EventDto> getEventsPageByClubId(Long clubId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Event> events = eventRepository.findEventByClubIds(clubId, pageable);

        return events.map(this::mapThisToEventDto);
    }

    @Override
    public EventDto findEventDtoById(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapThisToEventDto(event);
    }

    @Override
    public void editEvent(EventDto eventDto) {
        Event eventTemp = eventRepository.findById(eventDto.getId()).get();

        eventDto.setClub(eventTemp.getClub());

        Event eventSave = mapToEvent(eventDto);

        eventRepository.save(eventSave);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    private EventDto mapThisToEventDto(Event event) {
        return mapToEventDto(event);
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }
}
