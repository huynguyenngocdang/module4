package com.codegym.clubv3.service.impl;

import com.codegym.clubv3.dto.ClubDto;
import com.codegym.clubv3.entity.Club;
import com.codegym.clubv3.entity.UserEntity;
import com.codegym.clubv3.repository.ClubRepository;
import com.codegym.clubv3.repository.UserRepository;
import com.codegym.clubv3.security.SecurityUtil;
import com.codegym.clubv3.service.ClubService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.codegym.clubv3.mapper.impl.ClubMapperImpl.mapToClub;
import static com.codegym.clubv3.mapper.impl.ClubMapperImpl.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;
    private UserRepository userRepository;


    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<ClubDto> getClubsByPage(int pageNumber, int pageSize) {
        Page<Club> clubs = clubRepository.findAll(PageRequest.of(pageNumber-1, pageSize));
        return clubs.map(this::mapThisToClubDto);
    }
    private ClubDto mapThisToClubDto(Club club) {
        return mapToClubDto(club);
    }

    @Override
    public void createClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }
    @Override
    public ClubDto findClubDtoById(Long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }
    @Override
    public void saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }


    @Override
    public void deleteClubById(Long clubId) {
        clubRepository.deleteById(clubId);
    }

}
