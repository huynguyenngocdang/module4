package com.codegym.clubv3.service;

import com.codegym.clubv3.dto.EventDto;
import org.springframework.data.domain.Page;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    Page<EventDto> getEventsPageByClubId(Long clubId, int pageNumber, int pageSize);

    EventDto findEventDtoById(Long eventId);

    void editEvent(EventDto eventDto);

    void deleteEvent(Long eventId);
}
