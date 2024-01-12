package com.codegym.clubv3.controller;

import com.codegym.clubv3.dto.EventDto;
import com.codegym.clubv3.service.ClubService;
import com.codegym.clubv3.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.format.DateTimeFormatter;

@Controller
public class EventController {
    private ClubService clubService;
    private EventService eventService;

    public EventController(ClubService clubService, EventService eventService) {
        this.clubService = clubService;
        this.eventService = eventService;
    }
    @GetMapping("/events/{clubId}/new")
    public String displayNewEventForm(@PathVariable("clubId") Long clubId, Model model){
        model.addAttribute("event", new EventDto());
        model.addAttribute("clubId", clubId);
        return "events-new";
    }
    @PostMapping("/events/{clubId}/new")
    public String createNewEvent(@PathVariable("clubId") Long clubId,
                                 @Valid @ModelAttribute("event") EventDto eventDto,
                                 BindingResult result,
                                 Model model) {
        if(result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "events-new";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs";
    }

    @GetMapping("/events/{eventId}/detail")
    public String displayEventDetail(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findEventDtoById(eventId);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }
    @PostMapping("/events/{eventId}/detail")
    public String editEvent(@PathVariable("eventId") Long eventId,
                            Model model,
                            @Valid @ModelAttribute("event") EventDto eventDto,
                            BindingResult result) {
        if(result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "events-detail";
        }
        eventDto.setId(eventId);
        eventService.editEvent(eventDto);
        return "redirect:/clubs";
    }

    @PostMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId, Model model) {
        eventService.deleteEvent(eventId);
        return "redirect:/clubs";
    }
}
