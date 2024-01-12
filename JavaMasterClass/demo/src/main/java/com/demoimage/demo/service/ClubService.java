package com.demoimage.demo.service;

import com.demoimage.demo.model.Club;

import java.util.List;

public interface ClubService {
    List<Club> findAll();
     Club save(Club club);
     Club findById(Long id);
}
