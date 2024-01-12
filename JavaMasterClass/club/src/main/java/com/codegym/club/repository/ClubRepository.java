package com.codegym.club.repository;

import com.codegym.club.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Page<Club> findAll(Pageable pageable);

//    @Query("select c from Club c where c.title like concat('%', :query, '%') ")
//    List<Club> searchClubs(String query);
@Query("SELECT c FROM Club c WHERE c.title LIKE CONCAT('%', :query, '%') OR c.content LIKE CONCAT('%', :query, '%')")
Page<Club> searchClubs(String query, Pageable pageable);
}
