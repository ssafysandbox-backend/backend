package com.ssafy.todolist.email.dto;

public class EmailResponse {
    private final boolean isOk;

    public EmailResponse(boolean isOk) {
        this.isOk = isOk;
    }

    public boolean getIsOk() {
        return this.isOk;
    }

}
