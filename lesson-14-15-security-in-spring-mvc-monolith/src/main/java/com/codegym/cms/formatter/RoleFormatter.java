package com.codegym.cms.formatter;

import com.codegym.cms.service.RoleService;
import com.codegym.cms.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class RoleFormatter implements Formatter<Role> {

    private RoleService roleService;

    @Autowired
    public RoleFormatter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        Optional<Role> roleOptional = roleService.findById(Long.parseLong(text));
        return roleOptional.orElse(null);
    }

    @Override
    public String print(Role object, Locale locale) {
        return "[" + object.getId() + ", "
                    + object.getName() + ", "
                    + object.getDesc() + "]";
    }
}
