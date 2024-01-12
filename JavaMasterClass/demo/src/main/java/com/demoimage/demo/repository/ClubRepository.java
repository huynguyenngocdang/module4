package com.demoimage.demo.repository;

import com.demoimage.demo.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
