package com.codegym.clubv3.controller;

import com.codegym.clubv3.service.LyricsGatherer;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class LyricController {
    private LyricsGatherer lyricsGatherer;

    public LyricController(LyricsGatherer lyricsGatherer) {
        this.lyricsGatherer = lyricsGatherer;
    }

    @GetMapping("/lyric")
    public String lyricsForm() {
        return "lyrics";
    }

    @PostMapping("/lyric")
    public String findLyrics(@RequestParam String band, @RequestParam String songTitle, Model model) {
        try {
            List<String> lyrics = lyricsGatherer.getSongLyrics(band, songTitle);
            model.addAttribute("lyrics", lyrics);
        } catch (IOException e) {
            model.addAttribute("error", "Error fetching lyrics");
            e.printStackTrace();
        }
        return "lyrics";
    }
}
