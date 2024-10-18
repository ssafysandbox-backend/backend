package com.ssafy.todolist.util;

public enum Util {

    RESPONSE_SUCCESS("정상적으로 요청되었습니다."),
    RESPONSE_FAIL("정상적이지 않은 요청입니다.");

    private final String message;

    private Util(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
