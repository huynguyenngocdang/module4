package com.codegym.clubv3.repository;

import com.codegym.clubv3.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.club.id = :clubId")
    Page<Event> findEventByClubIds(Long clubId, Pageable pageable);
}
