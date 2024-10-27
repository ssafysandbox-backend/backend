package com.ssafy.todolist.email.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "email")
public class Email {

    @Id
    private String email;

    @Column(nullable = false)
    private String authenticationString;

    @Column(nullable = false)
    private String expirationTime;

    public Email() {}

    public Email(String email, String authenticationString, String expirationTime) {
        this.email = email;
        this.authenticationString = authenticationString;
        this.expirationTime = expirationTime;
    }

    public String getAuthenticationString() {
        return authenticationString;
    }

    public String getEmail() {
        return email;
    }

    public String getExpirationTime() {
        return expirationTime;
    }
}
