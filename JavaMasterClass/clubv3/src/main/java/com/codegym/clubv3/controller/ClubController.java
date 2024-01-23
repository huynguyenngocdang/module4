package com.codegym.clubv3.controller;

import com.codegym.clubv3.constant.VarConstant;
import com.codegym.clubv3.dto.ClubDto;
import com.codegym.clubv3.dto.EventDto;
import com.codegym.clubv3.entity.UserEntity;
import com.codegym.clubv3.security.SecurityUtil;
import com.codegym.clubv3.service.ClubService;
import com.codegym.clubv3.service.EventService;
import com.codegym.clubv3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClubController {
    private ClubService clubService;
    private EventService eventService;
    private UserService userService;


    public ClubController(ClubService clubService, EventService eventService, UserService userService) {
        this.clubService = clubService;
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String displayClubList(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "") String query){
        int pageSize = VarConstant.CLUB_PER_PAGE;
        Page<ClubDto> clubDtoPage = clubService.getClubsByPage(page, pageSize);

        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username!=null) {
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubDtoPage.getContent());
        model.addAttribute("currentPage", clubDtoPage.getNumber() + 1);
        model.addAttribute("totalPages", clubDtoPage.getTotalPages());
        model.addAttribute("query", query);
        model.addAttribute("paginationUrl", "/clubs");
        return "clubs-list";
    }
    @GetMapping("/clubs/new")
    public String displayNewClubForm(Model model) {
        model.addAttribute("club", new ClubDto());
        return "clubs-create";
    }
    @PostMapping("/clubs/new")
    public String createClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.createClub(clubDto);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/{clubId}/edit")
    public String displayEditClub(@PathVariable("clubId") Long clubId, Model model) {
        model.addAttribute("club", clubService.findClubDtoById(clubId));
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String editClub(@Valid @ModelAttribute("club") ClubDto clubDto,
                           Model model,
                           @PathVariable("clubId") Long clubId,
                           BindingResult result) {
        if(result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-edit";
        }
        clubDto.setId(clubId);
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId, Model model) {
        clubService.deleteClubById(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/display")
    public String displayClubDetail(@PathVariable("clubId") Long clubId,
                                    @RequestParam(defaultValue = "1") int page,
                                    Model model) {
        ClubDto clubDto = clubService.findClubDtoById(clubId);
        int pageSize = 2;
        Page<EventDto> eventDtos = eventService.getEventsPageByClubId(clubId, page, pageSize);


        model.addAttribute("club", clubDto);
        model.addAttribute("events", eventDtos.getContent());
        model.addAttribute("currentPage", eventDtos.getNumber() + 1);
        model.addAttribute("totalPages", eventDtos.getTotalPages());
        model.addAttribute("paginationUrl", "/clubs/{clubId}/display");
        return "clubs-detail";
    }
}
