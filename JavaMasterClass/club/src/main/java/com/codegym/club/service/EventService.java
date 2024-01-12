package com.codegym.club.service;

import com.codegym.club.dto.EventDto;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
}
