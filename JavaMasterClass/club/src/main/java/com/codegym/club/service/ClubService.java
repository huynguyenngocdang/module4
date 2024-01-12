package com.codegym.club.service;

import com.codegym.club.dto.ClubDto;
import com.codegym.club.model.Club;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClub();
    Page<Club> getClubsByPage(int pageNumber, int pageSize);
    ClubDto getClubById(Long id);
    Club createClub(ClubDto clubDto);
    void updateClub(ClubDto clubDto);

    void deleteClub(Long clubId);
//    List<ClubDto> searchClubs(String query);
    Page<Club> searchClubs(String query, int page, int pageSize);
}
