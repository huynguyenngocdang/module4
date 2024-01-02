package com.codegym.club.repository;

import com.codegym.club.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Page<Club> findAll(Pageable pageable);
}
