package com.codegym.cms.service;

import com.codegym.cms.model.Role;
import com.codegym.cms.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService extends GeneralService<User> {
    Iterable<User> findAllByRole(Role role);
    Page<User> findAll(Pageable pageable);
    Page<User> findAllByFullNameContaining(String fullName, Pageable pageable);

    boolean isAuthenticated();
}
