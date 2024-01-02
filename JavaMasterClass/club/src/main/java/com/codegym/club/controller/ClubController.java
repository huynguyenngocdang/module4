package com.codegym.club.controller;

import com.codegym.club.dto.ClubDto;
import com.codegym.club.model.Club;
import com.codegym.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
//    @GetMapping("/clubs")
//    public String displayAllClub(Model model){
//        List<ClubDto> clubs = clubService.findAllClub();
//        model.addAttribute("clubs", clubs);
//        return "clubs-list";
//    }

    @GetMapping("/clubs")
    public String getClubsByPage(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 3; // Number of clubs per page

        Page<Club> clubPage = clubService.getClubsByPage(page, pageSize);

        model.addAttribute("clubs", clubPage.getContent());
        model.addAttribute("currentPage", clubPage.getNumber() + 1);
        model.addAttribute("totalPages", clubPage.getTotalPages());

        return "clubs-list";
    }
    @GetMapping("/clubs/new")
    public String displayNewClubForm(Model model) {
        model.addAttribute("club", new ClubDto());
        return "clubs-create";
    }
    @PostMapping("/clubs/new")
    public String createClub(@ModelAttribute("club") ClubDto clubDto){
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }
}
