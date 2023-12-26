package com.codegym.simplemailvalidation.model;

public class Email {
    private int id;
    private String emailAddress;
    private String message;

    public Email(int id, String emailAddress, String message) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.message = message;
    }
}
