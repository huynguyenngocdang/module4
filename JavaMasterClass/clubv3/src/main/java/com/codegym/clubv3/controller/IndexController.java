package com.codegym.clubv3.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class IndexController {
    @GetMapping("/")
    public String displayIndex(){
        return "index";
    }
}
