package com.codegym.clubv3.service;

import com.codegym.clubv3.dto.ClubDto;
import org.springframework.data.domain.Page;

public interface ClubService {
    Page<ClubDto> getClubsByPage(int pageNumber, int pageSize);
    void createClub(ClubDto clubDto);
    ClubDto findClubDtoById(Long clubId);
   void saveClub(ClubDto clubDto);
    void deleteClubById(Long clubId);
}
