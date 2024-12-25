package com.ssafy.todolist.oauth.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.todolist.oauth.dto.kakao.KakaoAccount;

public class MemberInfoResponse {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "kakao_account")
    private KakaoAccount kakaoAccount;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KakaoAccount getKakaoAccount() {
        return kakaoAccount;
    }

    public void setKakaoAccount(KakaoAccount kakaoAccount) {
        this.kakaoAccount = kakaoAccount;
    }
}
