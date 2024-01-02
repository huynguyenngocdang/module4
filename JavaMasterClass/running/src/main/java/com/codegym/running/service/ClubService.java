package com.codegym.running.service;

import com.codegym.running.dto.ClubDto;
import com.codegym.running.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClub();
    Club saveClub(Club club);

    Club findClubById(Long id);

    void updateClub(Club club);
}
