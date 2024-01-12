package com.codegym.club.controller;

import com.codegym.club.dto.ClubDto;
import com.codegym.club.model.Club;
import com.codegym.club.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String getClubsByPage(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "") String query,
                                 Model model) {
        int pageSize = 3; // Number of clubs per page

        Page<Club> clubPage = clubService.getClubsByPage(page, pageSize);

        model.addAttribute("clubs", clubPage.getContent());
        model.addAttribute("currentPage", clubPage.getNumber() + 1);
        model.addAttribute("totalPages", clubPage.getTotalPages()); // Set totalPages based on total number of clubs
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
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.createClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String displayEditClubForm(Model model, @PathVariable("clubId") Long clubId) {
        ClubDto clubDto = clubService.getClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto clubDto,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "clubs-edit";
        }
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        clubService.deleteClub(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query", defaultValue = "") String query,
                             @RequestParam(defaultValue = "1") int page,
                             Model model) {
        if (query.isBlank()) {
            // If the query is blank, perform regular paginated retrieval
            return getClubsByPage(page, "", model);
        } else {
            // If there's a non-blank query, perform paginated search
            int pageSize = 3;
            Page<Club> clubPage = clubService.searchClubs(query, page, pageSize);

            model.addAttribute("clubs", clubPage.getContent());
            model.addAttribute("currentPage", clubPage.getNumber() + 1);
            model.addAttribute("totalPages", clubPage.getTotalPages()); // Set totalPages based on total number of search results

            model.addAttribute("query", query);
            model.addAttribute("paginationUrl", "/clubs/search");
            return "clubs-list";
        }
    }
    @GetMapping("/clubs/{clubId}/display")
    public String displayClubDetail(@PathVariable(value = "clubId") Long clubId, Model model) {
        ClubDto clubDto = clubService.getClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-detail";
    }
}
