package com.codegym.cms.controller;

import com.codegym.cms.model.Role;
import com.codegym.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ModelAndView listRoles() {
        ModelAndView modelAndView = new ModelAndView("/role/list");
        Iterable<Role> roles = roleService.findAll();
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/role/create");
        modelAndView.addObject("role", new Role());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveRole(@ModelAttribute("role") Role role) {
        roleService.save(role);
        ModelAndView modelAndView = new ModelAndView("/role/create");
        modelAndView.addObject("role", new Role());
        modelAndView.addObject("message", "New role created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        if (role.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/role/edit");
            modelAndView.addObject("role", role.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateRole(@ModelAttribute("role") Role role) {
        roleService.save(role);
        ModelAndView modelAndView = new ModelAndView("/role/edit");
        modelAndView.addObject("role", role);
        modelAndView.addObject("message", "Role updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        if (role.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/role/delete");
            modelAndView.addObject("role", role.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteRole(@ModelAttribute("role") Role role) {
        roleService.remove(role.getId());
        return "redirect:list";
    }
}
