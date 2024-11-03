package com.ssafy.sandbox.user.service;

import com.ssafy.sandbox.user.dto.KakaoUserInfoResponseDto;
import com.ssafy.sandbox.user.dto.LoginResponseDto;
import com.ssafy.sandbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public LoginResponseDto getToken(KakaoUserInfoResponseDto userInfo) {


        return LoginResponseDto
                .builder()
                .accessToken("")
                .refreshToken("")
                .build();
    }

    private boolean isUser(Long id) {
        return userRepository.existsById(id);
    }
}
