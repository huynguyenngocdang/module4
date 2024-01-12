package com.codegym.clubv2.controller;

import com.codegym.clubv2.constant.VarConstant;
import com.codegym.clubv2.dto.ClubDto;
import com.codegym.clubv2.model.Club;
import com.codegym.clubv2.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String displayClubs(Model model) {
        List<Club> clubs = clubService.getAllClub();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

//    @GetMapping("/clubs")
//    public String displayClubs(@RequestParam(defaultValue = "1") int pageNumber,
//                               @RequestParam(defaultValue = "") String query,
//                               Model model) {
//        int pageSize = VarConstant.CLUB_PER_PAGE;
//        Page<ClubDto> clubsPage = clubService.getClubsByPage(pageNumber, pageSize);
//        model.addAttribute("clubs", clubsPage.getContent());
//        model.addAttribute("currentPage", clubsPage.getNumber() + 1);
//        model.addAttribute("totalPages", clubsPage.getTotalPages());
//        model.addAttribute("query", query);
//        model.addAttribute("paginationUrl", "/clubs");
//        return "clubs-list";
//    }
    @GetMapping("/clubs/new")
    public String displayNewClubForm(Model model) {
        ClubDto clubDto = new ClubDto();
        model.addAttribute("newClub", clubDto);
        return "clubs-new";
    }
    @PostMapping("/clubs/new")
    public String createNewClub(@ModelAttribute("club") ClubDto clubDto,@RequestParam("file") MultipartFile file) {
        clubService.createClub(clubDto, file);
        return "redirect:/clubs";
    }
}
