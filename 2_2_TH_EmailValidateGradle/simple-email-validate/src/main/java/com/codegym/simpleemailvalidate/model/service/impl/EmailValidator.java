package com.codegym.simpleemailvalidate.model.service.impl;

import com.codegym.simpleemailvalidate.model.constant.VarConstant;
import com.codegym.simpleemailvalidate.model.service.IValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements IValidator {
    private Pattern pattern;
    private String email;

    public EmailValidator(String email) {
        this.pattern = Pattern.compile(VarConstant.EMAIL_SYNTAX);
        this.email = email;
    }

    @Override
    public boolean isCheck() {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
