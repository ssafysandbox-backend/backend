package com.ssafy.todolist.oauth.controller;

import com.ssafy.todolist.oauth.dto.auth.AuthFailResponse;
import com.ssafy.todolist.oauth.dto.auth.AuthRequest;
import com.ssafy.todolist.oauth.dto.auth.AuthResponse;
import com.ssafy.todolist.oauth.dto.kakao.KakaoTokenResponse;
import com.ssafy.todolist.oauth.service.OAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth/authorization")
public class OAuthController {

    private final OAuthService oAuthService;
    private final int NULL_ACCESS_TOKEN_STATUS = 400;
    private final String NULL_ACCESS_TOKEN_CODE = "ERR_MISSING_ACCESS_TOKEN";
    private final int EXPIRED_ACCESS_TOKEN_STATUS = 401;
    private final String EXPIRED_ACCESS_TOKEN_CODE = "ERR_ACCESS_TOKEN_EXPIRED";
    private final int NOT_FOUND_STATUS = 404;
    private final String NOT_FOUND_CODE = "ERR_NOT_FOUND_MEMBER";

    public OAuthController(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> getAuthCode(@RequestBody AuthRequest authRequest) {
        if (authRequest.getCode() == null) {
            return ResponseEntity.badRequest()
                    .body(new AuthFailResponse(NULL_ACCESS_TOKEN_STATUS, NULL_ACCESS_TOKEN_CODE));
        }
        KakaoTokenResponse kakaoTokenResponse = oAuthService.getTokens(authRequest);
        AuthResponse authResponse = new AuthResponse(kakaoTokenResponse.getRefreshToken(), kakaoTokenResponse.getAccessToken());
        return ResponseEntity.ok()
                .body(authResponse);
    }

    @GetMapping("/member")
    public ResponseEntity<?> getUserInfo(@RequestHeader(value = "Authorization",  required = false) String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthFailResponse(NULL_ACCESS_TOKEN_STATUS, NULL_ACCESS_TOKEN_CODE));
        }

        return ResponseEntity.ok().body(
                oAuthService.getUserInfo(accessToken)
                        .getKakaoAccount()
                        .getProfile());
    }
}
