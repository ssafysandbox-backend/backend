package com.ssafy.todolist.email.dto;

public class AuthenticationDTO {

    private final String authentication;
    private final String email;
    private final String authenticationTime;

    public AuthenticationDTO(String authentication, String email, String authenticationTime) {
        this.authentication = authentication;
        this.email = email;
        this.authenticationTime = authenticationTime;
    }

    public String getAuthentication() {
        return authentication;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthenticationTime() {
        return authenticationTime;
    }

}
