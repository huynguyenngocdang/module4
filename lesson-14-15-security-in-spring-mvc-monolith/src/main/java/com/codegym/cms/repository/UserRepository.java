package com.codegym.cms.repository;

import com.codegym.cms.model.Role;
import com.codegym.cms.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Iterable<User> findAllByRole(Role role);
    User findByUsername(String username);
    Page<User> findAllByFullNameContaining(String fullName, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT r.name FROM roles r " +
                    "INNER JOIN users u ON r.id = u.role_id " +
                    "WHERE u.username = :username")
    List<String> findRolesByUsername(@Param("username") String username);
}
