package com.codegym.club.service;

import com.codegym.club.dto.ClubDto;
import com.codegym.club.model.Club;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClub();
    Page<Club> getClubsByPage(int pageNumber, int pageSize);
    Club saveClub(ClubDto clubDto);

}
