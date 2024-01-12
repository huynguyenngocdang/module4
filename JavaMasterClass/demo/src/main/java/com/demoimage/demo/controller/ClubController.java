package com.demoimage.demo.controller;

import com.demoimage.demo.model.Club;
import com.demoimage.demo.repository.ClubRepository;
import com.demoimage.demo.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClubController {

    private ClubService clubService;
    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String showClubs(Model model) {
        List<Club> clubs = clubService.findAll();
        List<String> imagesBase64 = clubs.stream()
                .map(club -> Base64.getEncoder().encodeToString(club.getClubImage()))
                .collect(Collectors.toList());

        model.addAttribute("clubs", clubs);
        model.addAttribute("imagesBase64", imagesBase64); // List of Base64-encoded strings
        return "display";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Club club = clubService.findById(id); // Implement this method to retrieve the club by id
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(club.getClubImage());
    }
    @GetMapping("/clubs/new")
    public String newClub(Model model) {
        model.addAttribute("club", new Club());
        return "create"; // Thymeleaf template name for creating new club
    }

    @PostMapping("/clubs/new")
    public String addClub(@ModelAttribute Club club, @RequestParam("image") MultipartFile file) throws IOException {
        club.setClubImage(file.getBytes());
        clubService.save(club);
        return "redirect:/clubs";
    }
}
