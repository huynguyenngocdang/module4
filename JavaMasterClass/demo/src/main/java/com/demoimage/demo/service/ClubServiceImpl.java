package com.demoimage.demo.service;

import com.demoimage.demo.model.Club;
import com.demoimage.demo.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;
    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<Club> findAll() {
        return clubRepository.findAll();
    }
@Override
    public Club save(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public Club findById(Long id) {
        return clubRepository.findById(id).get();
    }
}
