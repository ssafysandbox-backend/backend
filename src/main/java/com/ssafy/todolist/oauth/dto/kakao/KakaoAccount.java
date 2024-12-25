package com.ssafy.todolist.oauth.dto.kakao;

public class KakaoAccount {

    private KakaoProfile kakaoProfile;

    public KakaoProfile getProfile() {
        return kakaoProfile;
    }

    public void setProfile(KakaoProfile kakaoProfile) {
        this.kakaoProfile = kakaoProfile;
    }
}
