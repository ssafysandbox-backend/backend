package com.ssafy.sandbox.todo.dto;

public class RequestDTO {
    private String content;

    public RequestDTO() {
    }

    public RequestDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "content='" + content + '\'' +
                '}';
    }
}
