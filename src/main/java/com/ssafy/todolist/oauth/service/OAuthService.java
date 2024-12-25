package com.ssafy.todolist.oauth.service;

import com.ssafy.todolist.oauth.dto.auth.AuthRequest;
import com.ssafy.todolist.oauth.dto.kakao.KakaoTokenResponse;
import com.ssafy.todolist.oauth.dto.member.MemberInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.token-uri}")
    private String tokenUri;

    private final RestTemplate restTemplate;

    public OAuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public KakaoTokenResponse getTokens(AuthRequest authRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestToKakao = new LinkedMultiValueMap<>();
        requestToKakao.add("grant_type", "authorization_code");
        requestToKakao.add("client_id", clientId);
        requestToKakao.add("redirect_uri", redirectUri);
        requestToKakao.add("code", authRequest.getCode());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestToKakao, headers);
        return restTemplate.postForObject(tokenUri, httpEntity, KakaoTokenResponse.class);
    }

    public MemberInfoResponse getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        return restTemplate.postForObject("https://kapi.kakao.com/v2/user/me", request, MemberInfoResponse.class);
    }

}
