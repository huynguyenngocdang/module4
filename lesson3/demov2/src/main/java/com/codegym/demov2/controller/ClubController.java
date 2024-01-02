package com.codegym.demov2.controller;

import com.codegym.demov2.dto.ClubDto;
import com.codegym.demov2.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
    @GetMapping({"/clubs", "/"})
    public String listClubs(Model model) {
        List<ClubDto> clubs =clubService.findAllClub();
        model.addAttribute("clubs", clubs);
        return "club-list";
    }
}
