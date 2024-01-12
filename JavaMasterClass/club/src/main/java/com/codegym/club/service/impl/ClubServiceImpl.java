package com.codegym.club.service.impl;

import com.codegym.club.dto.ClubDto;
import com.codegym.club.model.Club;
import com.codegym.club.repository.ClubRepository;
import com.codegym.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public List<ClubDto> findAllClub(){
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }
    public Page<Club> getClubsByPage(int pageNumber, int pageSize) {
        return clubRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
    }
    private ClubDto mapToClubDto(Club club) {
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }

    private Club mapToClub(ClubDto clubDto) {
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
        return club;
    }

    @Override
    public Club createClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        return clubRepository.save(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }

@Override
    public Page<Club> searchClubs(String query, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return clubRepository.searchClubs(query, pageRequest);
    }

    @Override
    public ClubDto getClubById(Long id) {
        Club club = clubRepository.findById(id).get();
        return mapToClubDto(club);
    }
}
