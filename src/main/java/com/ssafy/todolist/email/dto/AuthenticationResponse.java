package com.ssafy.todolist.email.dto;

public class AuthenticationResponse {
    private final boolean isSuccess;

    public AuthenticationResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
