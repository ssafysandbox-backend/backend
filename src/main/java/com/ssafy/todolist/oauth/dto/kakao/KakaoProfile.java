package com.ssafy.todolist.oauth.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoProfile {

    @JsonProperty("nickname")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
