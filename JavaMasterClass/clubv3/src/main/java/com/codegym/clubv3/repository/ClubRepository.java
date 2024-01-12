package com.codegym.clubv3.repository;

import com.codegym.clubv3.entity.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
        Page<Club> findAll(Pageable pageable);
}
