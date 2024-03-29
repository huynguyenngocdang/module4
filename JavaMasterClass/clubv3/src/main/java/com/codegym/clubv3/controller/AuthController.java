package com.codegym.clubv3.controller;

import com.codegym.clubv3.dto.RegistrationDto;
import com.codegym.clubv3.entity.UserEntity;
import com.codegym.clubv3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService)  {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("user", registrationDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,
//                           RedirectAttributes redirectAttributes,
                           Model model
    ) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null
                && existingUserEmail.getEmail() != null
                && !existingUserEmail.getEmail().isEmpty()) {
//                result.rejectValue("email", "There is already a user with this email/username");
                return "redirect:/register?fail";
        }

        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());

        if (existingUserUsername != null
                && existingUserUsername.getUsername() != null
                && !existingUserUsername.getUsername().isEmpty()) {
//            result.rejectValue("username", "There is already a user with this email/username");
            return "redirect:/register?fail";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
//        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/clubs?success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
