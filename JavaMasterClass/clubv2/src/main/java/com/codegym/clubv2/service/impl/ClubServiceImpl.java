package com.codegym.clubv2.service.impl;

import com.codegym.clubv2.dto.ClubDto;
import com.codegym.clubv2.model.Club;
import com.codegym.clubv2.repository.ClubRepository;
import com.codegym.clubv2.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }
    @Override
    public List<Club> getAllClub(){
        return  clubRepository.findAll();
    }
    @Override
    public Page<ClubDto> getClubsByPage(int pageNumber, int pageSize) {
        Page<Club> clubs = clubRepository.findAll(PageRequest.of(pageNumber, pageSize));
        System.out.println(clubs.getContent());

        return clubs.map(this::mapToClubDto);
    }

    @Override
    public void createClub(ClubDto clubDto, MultipartFile file) {
        try {
            if(file!= null && !file.isEmpty()){
                clubDto.setClubImage(file.getBytes());
            }
            Club club = mapToClub(clubDto);
            clubRepository.save(club);
        } catch (IOException e){

        }

    }

    private ClubDto mapToClubDto(Club club) {
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .clubImage(club.getClubImage())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }
    private Club mapToClub(ClubDto clubDto) {
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .clubImage(clubDto.getClubImage())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
        return club;
    }
}
