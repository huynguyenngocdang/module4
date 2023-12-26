package com.codegym.simpleemailvalidate.controller;

import com.codegym.simpleemailvalidate.model.service.IValidator;
import com.codegym.simpleemailvalidate.model.service.impl.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {
    @GetMapping(value = "/")
    public String home(){
        return "index.jsp";
    }
    @PostMapping(value = "/validate")
    public String user(@RequestParam("email") String email, ModelMap modelMap) {
        IValidator validator = new EmailValidator(email);
        if (!validator.isCheck()) {
            modelMap.addAttribute("message", "Email is invalid");
            return "index.jsp";
        }
        modelMap.addAttribute("email", email);
        return "success.jsp";
    }

}
