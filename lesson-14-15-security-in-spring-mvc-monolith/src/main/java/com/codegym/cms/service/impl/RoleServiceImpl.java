package com.codegym.cms.service.impl;

import com.codegym.cms.service.RoleService;
import com.codegym.cms.model.Role;
import com.codegym.cms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void save(Role province) {
        roleRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }
}
