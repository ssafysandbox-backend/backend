package com.ssafy.todolist.oauth.dto.auth;

public class AuthFailResponse {

    private int status;
    private String code;

    public AuthFailResponse(int status, String code) {
        this.status = status;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
