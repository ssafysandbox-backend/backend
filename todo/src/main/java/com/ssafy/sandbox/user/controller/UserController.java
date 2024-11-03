package com.ssafy.sandbox.user.controller;

import com.ssafy.sandbox.user.dto.KakaoUserInfoResponseDto;
import com.ssafy.sandbox.user.dto.LoginRequestDto;
import com.ssafy.sandbox.user.service.OauthService;
import com.ssafy.sandbox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth/authorization")
public class UserController {

    private final OauthService oauthService;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDto dto) {
        if (dto.getCode() == null) {
            return ResponseEntity.badRequest().build();
        }

        log.debug("code: {}", dto.getCode());
        String accessToken = oauthService.getAccessToken(dto.getCode());
        log.debug("access token: {}", accessToken);
        KakaoUserInfoResponseDto userInfo = oauthService.getUserInfo(accessToken);
//        userService

        return ResponseEntity.ok().build();
    }
}
