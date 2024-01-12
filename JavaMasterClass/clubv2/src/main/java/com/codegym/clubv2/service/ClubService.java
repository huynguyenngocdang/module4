package com.codegym.clubv2.service;

import com.codegym.clubv2.dto.ClubDto;
import com.codegym.clubv2.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClubService {
    Page<ClubDto> getClubsByPage(int pageNumber, int pageSize);

    void createClub(ClubDto clubDto, MultipartFile file);
    List<Club> getAllClub();
}
